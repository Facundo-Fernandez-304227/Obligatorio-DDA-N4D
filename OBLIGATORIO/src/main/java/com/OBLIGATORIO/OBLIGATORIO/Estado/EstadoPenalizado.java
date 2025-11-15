package com.OBLIGATORIO.OBLIGATORIO.Estado;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;

public class EstadoPenalizado extends EstadoPropietario {

    @Override
    public boolean puedeIngresarSistema() throws UsuarioException {
        return true;
    }

    @Override
    public boolean puedeRealizarTransitos() throws UsuarioException {
       return true;
    }

    @Override
    public boolean puedeRecibirNotificaciones() throws UsuarioException {
        throw new UsuarioException("Este usuario se encuentra penalizado y no puede recibir notifiaciones.");
    }

    @Override
    public boolean puedeAsignarBonificaciones() throws UsuarioException {
        throw new UsuarioException("Este usuario se encuentra penalizado y no se aplican bonificaciones.");
    }

    @Override
    public String getNombre() {
        return "Penalizado";
    }
    
}
