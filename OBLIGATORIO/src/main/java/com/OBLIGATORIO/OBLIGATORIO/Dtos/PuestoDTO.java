package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;

public class PuestoDTO {
    public String nombrePuesto;
    public String direccion;

    public PuestoDTO(Puesto p) {
        this.nombrePuesto = p.getNombrePuesto();
        this.direccion = p.getDireccion();
    }
}
