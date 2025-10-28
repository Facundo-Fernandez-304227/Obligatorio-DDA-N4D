package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class Puesto {
    
    @Getter
    private String nombrePuesto;
    @Getter
    private String direccion;

    public Puesto(String nombrePuesto, String direccion){
        this.nombrePuesto = nombrePuesto;
        this.direccion = direccion;
    }

    
}
