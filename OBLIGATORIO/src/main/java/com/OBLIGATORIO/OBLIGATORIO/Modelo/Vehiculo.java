package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class Vehiculo {
    
    @Getter
    private String matriculaVehiculo;
    @Getter
    private String modeloVehiculo;
    @Getter
    private String colorVehiculo;
    @Getter
    private CategoriaVehiculo categoriaVehiculo;


    public Vehiculo(String matriculaVehiculo, String modeloVehiculo, String colorVehiculo, CategoriaVehiculo categoriaVehiculo){
        this.matriculaVehiculo = matriculaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.colorVehiculo = colorVehiculo;
        this.categoriaVehiculo = categoriaVehiculo;
        
    }
}
