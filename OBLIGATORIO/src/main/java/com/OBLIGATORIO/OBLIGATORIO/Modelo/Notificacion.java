package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
public class Notificacion {
    
    @Getter
    private String mensaje;
    @Getter LocalDateTime fechaEnvio;

    private static final DateTimeFormatter FORMATO_FECHA = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    
    public Notificacion(LocalDateTime fechaEnvio, String mensaje) {
        this.fechaEnvio = fechaEnvio;
         this.mensaje = mensaje;

    }
    //GETTER PARA JSON Y DTO PARA QUE NO ROMPA CON LOCALDATETIME POR LA SERIALIZACION 
    //(SINO HAY QUE IMPORTAR JACKSON PARA JAVA 8  E IMPORTARLO EN EL SERIALIZADOR DEL CONEXION NAVEGADOR)
    public String getFechaEnvioStr() {
        return fechaEnvio.format(FORMATO_FECHA);
    } 


}
