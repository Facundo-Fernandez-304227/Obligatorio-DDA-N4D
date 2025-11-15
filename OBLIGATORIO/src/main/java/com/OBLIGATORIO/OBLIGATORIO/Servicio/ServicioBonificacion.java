package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.BonificacionException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;

public class ServicioBonificacion {
    
    private List<Bonificacion> bonificaciones = new ArrayList<>();

    public void agregarBonificacion(Bonificacion bonificacion) throws BonificacionException {
        for (Bonificacion b : bonificaciones) {
            if(b.getNombre().equals(bonificacion.getNombre())){
                throw new BonificacionException("Ya existe esa bonificacion.");
            }
        }
        bonificaciones.add(bonificacion);
    }

    public List<Bonificacion> getBonificaciones() {
        return bonificaciones;
    }

    public Bonificacion buscarBonificacionPorNombre(String nombre) {
        for (Bonificacion b : bonificaciones) {
            if (b.getNombre().equals(nombre)) {
                return b;
            }
        }
        return null;
    }
}
