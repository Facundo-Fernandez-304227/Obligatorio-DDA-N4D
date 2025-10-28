package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class CategoriaVehiculo {
    
    @Getter
    private String nombreCategoria;


    public CategoriaVehiculo(String nombreCategoria){
        this.nombreCategoria = nombreCategoria;
    }
    
}
