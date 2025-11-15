package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.EstadoException;

public class ServicioEstado {
    
    private List<EstadoPropietario> estados = new ArrayList<EstadoPropietario>();


    public void agregarEstado(EstadoPropietario estado) throws EstadoException{
        for (EstadoPropietario ep : estados) {
            if(ep.getNombre().equals(estado.getNombre())){
                throw new EstadoException("Ya existe un estado con ese nombre.");
            }
        }
        estados.add(estado);
    }

    public EstadoPropietario buscarEstadoPorNombre(String nombre){
        for (EstadoPropietario ep : estados) {
            if(ep.getNombre().equals(nombre)){
                return ep;
            }
        }
        return null;
    }

    public List<EstadoPropietario> getEstados() {
        return estados;
    }

}
