package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;

public class ServicioVehiculos {
    private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();

    public void agregarVehiculo(Vehiculo vehiculo1) throws VehiculoException{
        
        if (vehiculo1 == null) {
            throw new VehiculoException("El vehiculo no puede ser nulo.");
        }

        for (Vehiculo v : vehiculos) {
            if (vehiculo1.getMatriculaVehiculo().equals(v.getMatriculaVehiculo())) {
                throw new VehiculoException("El vehiculo ya esta registrado.");
            }
        }

        //Agregamos el vehiculo al propietario.
        vehiculo1.getUsuarioPropietario().agregarVehiculo(vehiculo1);
        //Agregamos el vehiculo a la lista.
        vehiculos.add(vehiculo1);
    }

}
