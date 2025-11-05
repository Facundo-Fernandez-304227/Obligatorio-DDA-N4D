package com.OBLIGATORIO.OBLIGATORIO.Modelo;
import lombok.Getter;

public abstract class Usuario {

    @Getter
    private String cedula;
    @Getter
    private String contrasenia;
    @Getter
    private String nombreCompleto;


    public Usuario(String cedula, String contrasenia, String nombreCompleto) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
    }


    public boolean contraseniaValida(String contrasenia2) {
        return this.contrasenia.equals(contrasenia2);
    }



}
