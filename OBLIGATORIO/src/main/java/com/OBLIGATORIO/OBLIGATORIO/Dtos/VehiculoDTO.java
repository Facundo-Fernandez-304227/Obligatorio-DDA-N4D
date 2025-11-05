package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;

public class VehiculoDTO {
    public String matricula;
    public String modelo;
    public int transitos;
    public double montoTotal;

    public VehiculoDTO(Vehiculo vehiculo) {
        this.matricula = vehiculo.getMatriculaVehiculo();
        this.modelo = vehiculo.getModeloVehiculo();
        this.transitos = vehiculo.getTransitos().size();
        this.montoTotal = vehiculo.getMontoTransitos();
    }
}
