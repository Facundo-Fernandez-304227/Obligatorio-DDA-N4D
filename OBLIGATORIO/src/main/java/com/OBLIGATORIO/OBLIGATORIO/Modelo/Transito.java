package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Getter;

public class Transito {

    @Getter
    private LocalDate fechaTransito;
    @Getter
    private LocalTime horaTransito;
    @Getter
    private double montoTransito;
    @Getter
    private Vehiculo vehiculo;
    @Getter
    private Puesto puesto;
    @Getter
    private Bonificacion bonificacionAplicada;

    public Transito(LocalDate fechaTransito, LocalTime horaTransito, double montoTransito, Vehiculo vehiculo,
            Puesto puesto, Bonificacion bonificacionAplicada) {
        this.fechaTransito = fechaTransito;
        this.horaTransito = horaTransito;
        this.montoTransito = montoTransito;
        this.vehiculo = vehiculo;
        this.puesto = puesto;
        this.bonificacionAplicada = bonificacionAplicada;
    }

    public double getMontoPagado() {
        if (bonificacionAplicada == null)
            return montoTransito;
        return bonificacionAplicada.calcularBonficiacion(vehiculo, puesto, montoTransito);
    }

    public double getMontoBonificacion() {
        return montoTransito - getMontoPagado();
    }

    public Notificacion generarNotificacion() {
        return new Notificacion(LocalDateTime.now(), "Pasaste por el puesto: " + puesto.getNombrePuesto() +  " con el vehículo " + vehiculo.getMatriculaVehiculo());
    }

}
