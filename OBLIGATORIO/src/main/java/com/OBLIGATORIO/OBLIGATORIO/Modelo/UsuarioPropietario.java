package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.util.ArrayList;
import java.util.List;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;

import lombok.Getter;

public class UsuarioPropietario extends Usuario {
    
    @Getter
    private double saldoActual;
    @Getter
    private double saldoMinimoAlerta;
    @Getter
    private List<Vehiculo> vehiculosPropietario;

    public UsuarioPropietario(String cedula, String contrasenia, String nombreCompleto, double saldoActual, double saldoMinimoAlerta){
        super(cedula, contrasenia, nombreCompleto);
        this.saldoActual = saldoActual;
        this.saldoMinimoAlerta = saldoMinimoAlerta;
        this.vehiculosPropietario = new ArrayList<Vehiculo>();
    }

    public void agregarVehiculo(Vehiculo vehiculo1) throws VehiculoException {
        for (Vehiculo v : vehiculosPropietario) {
            if(vehiculo1.getMatriculaVehiculo().equals(v.getMatriculaVehiculo())){
                throw new VehiculoException("El propietario ya tiene ese vehiculo.");
            }
        }
        vehiculosPropietario.add(vehiculo1);
        //Le setteamos el usuario al vehiculo.
        vehiculo1.setUsuarioPropietario(this);
    }



}
