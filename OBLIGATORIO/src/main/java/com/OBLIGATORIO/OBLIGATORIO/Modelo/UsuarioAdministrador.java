package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import com.OBLIGATORIO.OBLIGATORIO.Interfaces.Usuario;

import lombok.Getter;

public class UsuarioAdministrador implements Usuario {

    @Getter
    private String cedula;
    @Getter
    private String contrasenia;
    @Getter
    private String nombreCompleto;

    public UsuarioAdministrador(String cedula, String contrasenia, String nombreCompleto) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
    }

}
