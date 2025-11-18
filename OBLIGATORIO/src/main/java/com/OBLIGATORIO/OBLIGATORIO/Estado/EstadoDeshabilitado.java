package com.OBLIGATORIO.OBLIGATORIO.Estado;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;

public class EstadoDeshabilitado extends EstadoPropietario {

    @Override
    public boolean puedeIngresarSistema() throws UsuarioException {
        throw new UsuarioException("Usuario deshabilitado, no puede ingresar al sistema.");
    }

    @Override
    public boolean puedeRealizarTransitos() throws UsuarioException {
        throw new UsuarioException("Este usuario se encuentra deshabilitado y no puede realizar transitos.");
    }

    @Override
    public boolean puedeRecibirNotificaciones() throws UsuarioException {
        throw new UsuarioException("Este usuario se encuentra deshabilitado y no puede recibir notificaciones.");
    }

    @Override
    public boolean puedeAsignarBonificaciones() throws UsuarioException {
       throw new UsuarioException("Este usuario se encuentra deshabilitado y no puede recibir bonificaciones.");
    }

    @Override
    public String getNombre() {
        return "Deshabilitado";
    }
    
}
