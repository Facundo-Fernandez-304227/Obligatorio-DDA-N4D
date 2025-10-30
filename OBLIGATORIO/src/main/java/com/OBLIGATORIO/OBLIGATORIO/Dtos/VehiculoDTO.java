package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;

public class VehiculoDTO {
    public String matricula;
    public String modelo;
    public String color;
    public String categoria;

    public VehiculoDTO(Vehiculo vehiculo) {
        this.matricula = vehiculo.getMatriculaVehiculo();
        this.modelo = vehiculo.getModeloVehiculo();
        this.color = vehiculo.getColorVehiculo();
        this.categoria = vehiculo.getCategoriaVehiculo().getNombreCategoria();
    }
}
