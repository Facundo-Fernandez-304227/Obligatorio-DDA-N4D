package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropietarioDTO {

    private String nombreCompleto;
    private String estado;
    private double saldoActual;
    private List<VehiculoDTO> vehiculos;
    private List<BonificacionAsignadaDTO> bonificaciones;
    private List<TransitosDTO> transitos;
    private List<NotificacionDTO> notificaciones;

    public PropietarioDTO(UsuarioPropietario prop) {

        // NOMBRE
        this.nombreCompleto = prop.getNombreCompleto();

        // ESTADO
        this.estado = prop.getEstado().getNombre();

        // SALDO
        this.saldoActual = prop.getSaldoActual();

        // VEHICULOS
        this.vehiculos = new ArrayList<>();
        for (Vehiculo v : prop.getVehiculosPropietario()) {
            this.vehiculos.add(new VehiculoDTO(v));
        }

        // BONIFICACIONES
        this.bonificaciones = new ArrayList<>();
                for (BonificacionAsignada b : prop.getBonificacionAsignadas()) {
            this.bonificaciones.add(new BonificacionAsignadaDTO(b));
        }

        // TRANSITOS
        this.transitos = new ArrayList<>();
              for (Vehiculo v : prop.getVehiculosPropietario()) {
            for (Transito t : v.getTransitos()) {
                   this.transitos.add(new TransitosDTO(t));
            }
        }
        // NOTIFICACIONES
        this.notificaciones = new ArrayList<>();
        for (Notificacion n : prop.getNotificaciones()) {
            this.notificaciones.add(new NotificacionDTO(n));
        }
    }
}
