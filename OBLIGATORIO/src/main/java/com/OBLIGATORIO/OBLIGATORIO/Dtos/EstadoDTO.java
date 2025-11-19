package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;

public class EstadoDTO {

    public String nombre;

    public EstadoDTO(EstadoPropietario estado) {
        this.nombre = estado.getNombre();
    }

}
