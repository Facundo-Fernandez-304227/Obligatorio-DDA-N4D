package com.OBLIGATORIO.OBLIGATORIO.Interfaces;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;

public interface IBonificacion {

    String getNombreBonificacion();
    
    double calcularBonficiacion(Vehiculo vehiculo, Puesto puesto, double monto);
}
