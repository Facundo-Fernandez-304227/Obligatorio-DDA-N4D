package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class TarifaPuesto {
    
    @Getter
    private double montoPuesto;

    @Getter
    private CategoriaVehiculo categoriaVehiculoPuesto;


    public TarifaPuesto(double montoPuesto, CategoriaVehiculo categoriaVehiculo){
        this.montoPuesto = montoPuesto;
        this.categoriaVehiculoPuesto = categoriaVehiculo;
        
    }
}
