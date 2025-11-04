package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public abstract class Bonificacion {

    @Getter
    private String nombre;

    public Bonificacion(String nombre) {
        this.nombre = nombre;
    }

    public abstract double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto);


}
