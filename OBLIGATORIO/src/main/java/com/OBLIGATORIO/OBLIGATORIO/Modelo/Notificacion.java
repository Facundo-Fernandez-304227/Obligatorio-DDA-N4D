package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.LocalDateTime;

import lombok.Getter;
public class Notificacion {
    
    @Getter
    private String mensaje;
    @Getter LocalDateTime fechaEnvio;
    
    public Notificacion(LocalDateTime fechaEnvio, String mensaje) {
        this.fechaEnvio = fechaEnvio;
         this.mensaje = mensaje;

    }
     
}
