package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.NotificacionDTO;
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

    public void agregarTransito(Transito t) throws VehiculoException {

        Vehiculo v = buscarVehiculoPorMatricula(t.getVehiculo().getMatriculaVehiculo());
        v.getTransitos().add(t);

        // pedir al tránsito su notificación
        Notificacion noti = t.generarNotificacion();

        UsuarioPropietario propietario = v.getUsuarioPropietario();
        propietario.agregarNotificacion(noti);

        //aviso SSE
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

}
