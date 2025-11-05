package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.LocalDate;

public class BonificacionFrecuente extends Bonificacion {

    public BonificacionFrecuente() {
        super("Frecuente");
    }

    @Override
    public double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto) {
        long cantidadTransitosHoy = vehiculo.getTransitos().stream()
                .filter(t -> t.getPuesto().equals(puesto)
                        && t.getFechaTransito().isEqual(LocalDate.now()))
                .count();

        // Si ya hubo un tránsito hoy, aplica 50% de descuento
        if (cantidadTransitosHoy >= 1) {
            return monto * 0.5;
        } else {
            return monto; // primer tránsito del día paga completo
        }
    }

}
