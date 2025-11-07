package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class TarifaPuesto {
    
    @Getter
    private double montoPuesto;

    @Getter
    private CategoriaVehiculo categoriaVehiculoPuesto;

    @Getter
    private Puesto puesto;


    public TarifaPuesto(double montoPuesto, CategoriaVehiculo categoriaVehiculo, Puesto puesto){
        this.montoPuesto = montoPuesto;
        this.categoriaVehiculoPuesto = categoriaVehiculo;
        this.puesto = puesto;
        
    }
}
