package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import com.OBLIGATORIO.OBLIGATORIO.Interfaces.IBonificacion;

public class BonificacionTrabajadores implements IBonificacion {

    private String nombreBonficacion = "Trabajadores";

    @Override
    public double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calcularBonficiacion'");
    }

    @Override
    public String getNombreBonificacion() {
        return nombreBonficacion;
    }

}
