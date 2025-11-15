package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;

public class BonificacionDTO {

    public String nombre;

    public BonificacionDTO(Bonificacion bonificacion) {
        this.nombre = bonificacion.getNombre();
    }

}
