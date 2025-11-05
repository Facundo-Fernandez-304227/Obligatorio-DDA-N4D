package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Transito;

public class TransitosDTO {

    public String nombrePuesto;
    public String matricula;
    public String categoria;
    public double montoTarifa;
    public String bonificacion;
    public double montoBonificacion;
    public double montoPagado;
    public LocalDate fecha;
    public LocalTime horaTransito;


    public TransitosDTO(Transito transito) {
        this.nombrePuesto = transito.getPuesto().getNombrePuesto();
        this.matricula = transito.getVehiculo().getMatriculaVehiculo();
        this.categoria = transito.getVehiculo().getCategoriaVehiculo().getNombreCategoria();
        this.montoTarifa = transito.getMontoTransito();
        this.bonificacion = transito.getBonificacionAplicada() != null ? transito.getBonificacionAplicada().getNombre() : "Sin bonificación";
        this.montoBonificacion = -transito.getMontoBonificacion(); // se muestra negativo en la tabla
        this.montoPagado = transito.getMontoPagado();
        this.fecha = transito.getFechaTransito();
        this.horaTransito = transito.getHoraTransito();
    }

}
