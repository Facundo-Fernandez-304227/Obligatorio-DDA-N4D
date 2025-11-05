package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;

public class PropietarioDTO {
    public String nombreCompleto;
    // public String estadoPropietario;
    public double saldoActual;
    public List<VehiculoDTO> vehiculos;
    public List<BonificacionAsignadaDTO> bonificaciones;
    public List<TransitosDTO> transitos;

    public PropietarioDTO(UsuarioPropietario prop) {

        // NOMBRE
        this.nombreCompleto = prop.getNombreCompleto();

        // ESTADO
        // this.estadoPropietario = prop.getEstado();

        // SALDO
        this.saldoActual = prop.getSaldoActual();

        // VEHICULOS
        this.vehiculos = prop.getVehiculosPropietario().stream().map(VehiculoDTO::new).collect(Collectors.toList());

        // BONIFICACIONES
        this.bonificaciones = prop.getBonificacionAsignadas().stream()
                .map(BonificacionAsignadaDTO::new)
                .collect(Collectors.toList());

        // TRANSITOS
        this.transitos = prop.getVehiculosPropietario().stream()
                .flatMap(v -> v.getTransitos().stream())
                .map(TransitosDTO::new)
                .collect(Collectors.toList());
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
