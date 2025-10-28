package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class Bonificacion {
    
    @Getter
    private String nombreBonificacion;

    
    public Bonificacion(String nombreBonificacion) {
        this.nombreBonificacion = nombreBonificacion;
    }

    
}
