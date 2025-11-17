package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;
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

    public void agregarTransito(Transito transito1) throws VehiculoException {
        if (transito1 == null) {
            throw new VehiculoException("El tránsito no puede ser nulo.");
        }

        Vehiculo vehiculo = transito1.getVehiculo();
        if (vehiculo == null || vehiculo.getMatriculaVehiculo() == null) {
            throw new VehiculoException("El tránsito no tiene un vehículo asociado válido.");
        }

        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo v : vehiculos) {
            if (v.getMatriculaVehiculo().equalsIgnoreCase(vehiculo.getMatriculaVehiculo())) {
                vehiculoEncontrado = v;
                break;
            }
        }

        if (vehiculoEncontrado == null) {
            throw new VehiculoException("No se encontró el vehículo con matrícula: " + vehiculo.getMatriculaVehiculo());
        }

        // REVISAR ESTO NO SE SI VA ACA.

        vehiculoEncontrado.getTransitos().add(transito1);
        Notificacion notificacion = new Notificacion(
                LocalDateTime.now(),
                "Pasaste por el puesto: " + transito1.getPuesto().getNombrePuesto() + "con el vehiculo "
                        + vehiculo.getMatriculaVehiculo());

        // REVISAR SI DESPOUES CON SSEE SE HACE ASI
        Fachada.getInstancia().avisar(notificacion);
    }

    public Vehiculo buscarVehiculoPorMatricula(String matricula) throws VehiculoException {
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMatriculaVehiculo().equals(matricula)) {
                return vehiculo;
            }
        }
        throw new VehiculoException("No se encontro vehiculo con esa matricula. -" + matricula);
    }

}
