package com.OBLIGATORIO.OBLIGATORIO.Estado;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;

public class EstadoSuspendido extends EstadoPropietario {

    @Override
    public boolean puedeIngresarSistema() throws UsuarioException {
        return true;
    }

    @Override
    public boolean puedeRealizarTransitos() throws UsuarioException {
        throw new UsuarioException("Este usuario se encuentra suspendido y no puede realizar transitos.");
    }

    @Override
    public boolean puedeRecibirNotificaciones() throws UsuarioException {
        return true;
    }

    @Override
    public boolean puedeAsignarBonificaciones() throws UsuarioException {
        return true;
    }

    @Override
    public String getNombre() {
       return "Suspendido";
    }
    
}
