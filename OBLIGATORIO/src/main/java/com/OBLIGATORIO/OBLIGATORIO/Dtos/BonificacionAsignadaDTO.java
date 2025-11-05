package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.time.LocalDate;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.BonificacionAsignada;

public class BonificacionAsignadaDTO {

    public String nombreBonificacion;
    public String nombrePuesto;
    public LocalDate fechaAsignada;

    public BonificacionAsignadaDTO(BonificacionAsignada bonificacion) {
        this.nombreBonificacion = bonificacion.getBonificacion().getNombre();
        this.nombrePuesto = bonificacion.getPuesto().getNombrePuesto();
        this.fechaAsignada = bonificacion.getFechaAsignada();
    }

}
