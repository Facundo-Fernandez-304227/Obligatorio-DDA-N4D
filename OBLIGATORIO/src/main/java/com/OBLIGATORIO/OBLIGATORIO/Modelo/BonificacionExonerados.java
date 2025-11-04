package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import com.OBLIGATORIO.OBLIGATORIO.Interfaces.IBonificacion;

public class BonificacionExonerados implements IBonificacion {

    private String nombreBonificacion = "Exonerados";
    
    @Override
    public double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto) {
        return monto;
    }

    @Override
    public String getNombreBonificacion() {
        return nombreBonificacion;
    }
    
}
