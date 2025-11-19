package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.NotificacionDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.TransitosDTO;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.BonificacionAsignada;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.TarifaPuesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Transito;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;

public class ServicioVehiculos {

    private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

    public void agregarVehiculo(Vehiculo vehiculo1) throws VehiculoException {

        if (vehiculo1 == null) {
            throw new VehiculoException("El vehiculo no puede ser nulo.");
        }

        for (Vehiculo v : vehiculos) {
            if (vehiculo1.getMatriculaVehiculo().equals(v.getMatriculaVehiculo())) {
                throw new VehiculoException("El vehiculo ya esta registrado.");
            }
        }

        // Agregamos el vehiculo al propietario.
        UsuarioPropietario propietario = Fachada.getInstancia()
                .buscarPropietarioPorCedula(vehiculo1.getUsuarioPropietario().getCedula());
        if (propietario != null) {
            propietario.agregarVehiculo(vehiculo1);
            vehiculo1.setUsuarioPropietario(propietario);
        }
        // Agregamos el vehiculo a la lista.
        vehiculos.add(vehiculo1);
    }

    public void agregarTransito(Transito t) throws VehiculoException {

        Vehiculo v = buscarVehiculoPorMatricula(t.getVehiculo().getMatriculaVehiculo());
        v.getTransitos().add(t);

        // pedir al tránsito su notificación
        Notificacion noti = t.generarNotificacion();

        UsuarioPropietario propietario = v.getUsuarioPropietario();
        propietario.agregarNotificacion(noti);

        // aviso SSE
        Fachada.getInstancia().avisar("TRANSITO_REGISTRADO");
    }

    public Vehiculo buscarVehiculoPorMatricula(String matricula) throws VehiculoException {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatriculaVehiculo().equals(matricula)) {
                return vehiculo;
            }
        }
        throw new VehiculoException("No se encontro vehiculo con esa matricula. -" + matricula);
    }

    public TransitosDTO emularTransito(String nombrePuesto, String matricula, String fechaHora)
            throws UsuarioException, VehiculoException {

        // 1) Buscar puesto
        Puesto puestoSel = Fachada.getInstancia().buscarPuestoPorNombre(nombrePuesto);
        if (puestoSel == null) {
            throw new UsuarioException("No se encontró el puesto seleccionado.");
        }

        // 2) Buscar vehículo
        Vehiculo vehiculo = buscarVehiculoPorMatricula(matricula);
        UsuarioPropietario propietario = vehiculo.getUsuarioPropietario();

        // 3) Validar estado
        if (!propietario.getEstado().puedeRealizarTransitos()) {
            throw new UsuarioException("El propietario está en estado '" +
                    propietario.getEstado().getNombre() +
                    "' y no puede realizar tránsitos.");
        }

        // 4) Buscar tarifa (versión junior)
        TarifaPuesto tarifa = null;
        for (TarifaPuesto t : puestoSel.getListaTarifaPuesto()) {
            if (t.getCategoriaVehiculoPuesto()
                    .equals(vehiculo.getCategoriaVehiculo())) {
                tarifa = t;
                break;
            }
        }

        if (tarifa == null) {
            throw new UsuarioException("No hay tarifa para la categoría del vehículo.");
        }

        // 5) Fecha/hora
        LocalDate fecha = LocalDate.parse(fechaHora.substring(0, 10));
        LocalTime hora = LocalTime.parse(fechaHora.substring(11));

        // 6) Buscar bonificación
        Bonificacion bonificacionAplicada = null;

        if (!propietario.getEstado().getNombre().equals("Penalizado")) {

            for (BonificacionAsignada b : propietario.getBonificacionAsignadas()) {
                if (b.getPuesto().equals(puestoSel)) {
                    bonificacionAplicada = b.getBonificacion();
                    break;
                }
            }
        }

        // 7) Crear tránsito
        Transito transito = new Transito(
                fecha,
                hora,
                tarifa.getMontoPuesto(),
                vehiculo,
                puestoSel,
                bonificacionAplicada);

        // 8) Validar saldo
        double costo = transito.getMontoPagado();
        if (propietario.getSaldoActual() < costo) {
            throw new UsuarioException("Saldo insuficiente.");
        }

        // 9) Cobrar
        propietario.setSaldoActual(propietario.getSaldoActual() - costo);

        // 10) Registrar tránsito
        agregarTransito(transito);

        // 11) Devolver DTO
        return new TransitosDTO(transito);
    }

}
