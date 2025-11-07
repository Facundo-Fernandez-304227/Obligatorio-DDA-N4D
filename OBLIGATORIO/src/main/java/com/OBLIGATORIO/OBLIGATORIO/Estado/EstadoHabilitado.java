package com.OBLIGATORIO.OBLIGATORIO.Estado;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;

public class EstadoHabilitado extends EstadoPropietario {

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
        return true;
    }

    @Override
    public boolean puedeAsignarBonificaciones() throws UsuarioException {
        return true;
    }

    @Override
    public String getNombre() {
        return "Habilitado";
    }

}
