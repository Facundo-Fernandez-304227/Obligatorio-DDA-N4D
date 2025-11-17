package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.time.LocalDateTime;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacionDTO {
        
    private String mensaje;
    private String fechaEnvio;

  //SE MODIFICA EL ATRIBUTO FECHA ENVIO A STR Y SE OBTIENE DE NOTIFICACION EL LOCAL DATE TIME FORMATEADO EN STRING PARA QUE NO ROMPA SERIALIZADOR DE JSON EN CONEXION NAVEGADOR.

    public NotificacionDTO(){}

    public NotificacionDTO(Notificacion notificacion) {
        this.mensaje = notificacion.getMensaje();
      this.fechaEnvio = notificacion.getFechaEnvioStr();
    }
     
}
