package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class BonificacionTrabajadores extends Bonificacion {

    public BonificacionTrabajadores() {
        super("Trabajadores");

    }

    @Override
    public double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto) {
        DayOfWeek dia = LocalDate.now().getDayOfWeek();
        boolean esDiaLaboral = dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY;

        if (esDiaLaboral) {
            return monto * 0.2; // paga solo el 20%
        } else {
            return monto; // paga completo
        }
    }

}
