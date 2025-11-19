package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Puesto {
    
    @Getter
    private String nombrePuesto;
    @Getter
    private String direccion;
    @Getter
    private List<TarifaPuesto> listaTarifaPuesto;

    public Puesto(String nombrePuesto, String direccion){
        this.nombrePuesto = nombrePuesto;
        this.direccion = direccion;
        this.listaTarifaPuesto = new ArrayList<>();
    }

    public void agregarTarifaPuesto(TarifaPuesto tarifaPuesto){
        listaTarifaPuesto.add(tarifaPuesto);
    }
/* 
    public double getMontoPuesto() {
        return listaTarifaPuesto.montoPuesto();
    }
    */

    public List<TarifaPuesto> getListaTarifaPuesto(){
        return listaTarifaPuesto;
    }

    
}
