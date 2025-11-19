package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.PuestoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.TarifaPuesto;

public class ServicioPuesto {
    
    private List<Puesto> puestos = new ArrayList<>();

    public void agregarPuesto(Puesto puesto) {
        puestos.add(puesto);
    }

    public List<Puesto> getPuestos() {
        return puestos;
    }

    public Puesto buscarPuestoPorNombre(String nombrePuesto) {
        for (Puesto puesto : puestos) {
            if (puesto.getNombrePuesto().equals(nombrePuesto)) {
                return puesto;
            }
        }
        return null;
    }

    public List<TarifaPuesto> getTarifasPorPuesto(Puesto puesto) {
        return puesto.getListaTarifaPuesto();
    }

    public void agregarTarifaPuesto(TarifaPuesto tarifaPuesto) throws PuestoException {
        if (tarifaPuesto.getPuesto() == null || tarifaPuesto == null) {
            throw new PuestoException("El puesto no puede ser vacio.");
        }

        Puesto puesto = tarifaPuesto.getPuesto();
        puesto.agregarTarifaPuesto(tarifaPuesto);
    }

    public List<TarifaPuesto> getTarifas(String nombrePuesto) {
        for (Puesto puesto : puestos) {
            if(puesto.getNombrePuesto().equals(nombrePuesto)){
                puesto.getListaTarifaPuesto();
            }
            
        }
        return null;
    }
}
