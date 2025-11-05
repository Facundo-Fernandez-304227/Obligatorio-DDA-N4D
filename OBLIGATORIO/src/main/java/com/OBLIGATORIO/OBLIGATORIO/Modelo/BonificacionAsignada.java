package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.LocalDate;

import lombok.Getter;

public class BonificacionAsignada {

    @Getter
    private UsuarioPropietario usuarioPropietario;
    @Getter
    private Bonificacion bonificacion;
    @Getter
    private Puesto puesto;
    @Getter
    private LocalDate fechaAsignada;

    public BonificacionAsignada(UsuarioPropietario usuarioPropietario, Bonificacion bonificacion, Puesto puesto,
            LocalDate fechaAsignada) {
        this.usuarioPropietario = usuarioPropietario;
        this.bonificacion = bonificacion;
        this.puesto = puesto;
        this.fechaAsignada = fechaAsignada;
    }

}
