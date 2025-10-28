package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import lombok.Getter;

public class UsuarioPropietario extends Usuario {
    
    @Getter
    private double saldoActual;
    @Getter
    private double saldoMinimoAlerta;


    public UsuarioPropietario(String cedula, String contrasenia, String nombreCompleto, double saldoActual, double saldoMinimoAlerta){
        super(cedula, contrasenia, nombreCompleto);
        this.saldoActual = saldoActual;
        this.saldoMinimoAlerta = saldoMinimoAlerta;
    }

}
