package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;

public class PropietarioDTO {
    public String nombreCompleto;
    //public String estadoPropietario;
    public double saldoActual;
    public List<VehiculoDTO> vehiculos;

    public PropietarioDTO(UsuarioPropietario prop){
        this.nombreCompleto = prop.getNombreCompleto();
        //this.estadoPropietario = prop.getEstado();
        this.saldoActual = prop.getSaldoActual();
        //Convertir los vehiculos en vechiculosDTO
        this.vehiculos = prop.getVehiculosPropietario().stream().map(VehiculoDTO::new).collect(Collectors.toList());
    }

    public String getNombrePropietario() {
        return nombreCompleto;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    



}
