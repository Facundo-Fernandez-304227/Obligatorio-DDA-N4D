package com.OBLIGATORIO.OBLIGATORIO.Modelo;

public class BonificacionExonerados extends Bonificacion {

    public BonificacionExonerados() {
        super("Exonerado");
    }

    @Override
    public double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto) {
       return 0;
    }

}
