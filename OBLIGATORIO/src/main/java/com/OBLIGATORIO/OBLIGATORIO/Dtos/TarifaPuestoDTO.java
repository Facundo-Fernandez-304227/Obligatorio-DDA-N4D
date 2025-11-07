package com.OBLIGATORIO.OBLIGATORIO.Dtos;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.TarifaPuesto;

public class TarifaPuestoDTO {
    
    public String categoria;
    public double monto;

    public TarifaPuestoDTO(TarifaPuesto tarifaPuesto){
        this.categoria = tarifaPuesto.getCategoriaVehiculoPuesto().getNombreCategoria();
        this.monto = tarifaPuesto.getMontoPuesto();
    }
}
