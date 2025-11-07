package com.OBLIGATORIO.OBLIGATORIO.Estado;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;

public abstract class EstadoPropietario {

    protected UsuarioPropietario propietario;

    public abstract boolean puedeIngresarSistema() throws UsuarioException;

    public abstract boolean puedeRealizarTransitos() throws UsuarioException;

    public abstract boolean puedeRecibirNotificaciones() throws UsuarioException;

    public abstract boolean puedeAsignarBonificaciones() throws UsuarioException;

    public void setPropietario(UsuarioPropietario propietario) {
        this.propietario = propietario;
    }

    public abstract String getNombre();
}