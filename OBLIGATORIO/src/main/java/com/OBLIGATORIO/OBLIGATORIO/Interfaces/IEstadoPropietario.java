package com.OBLIGATORIO.OBLIGATORIO.Interfaces;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;

public interface IEstadoPropietario {
    
    boolean puedeIngresarSistema() throws UsuarioException;

    boolean puedeRealizarTransitos() throws UsuarioException;

    boolean puedeRecibirNotificaciones() throws UsuarioException;

    boolean puedeAsignarBonificaciones() throws UsuarioException;
}
