package com.OBLIGATORIO.OBLIGATORIO.Dtos;

import java.time.LocalDateTime;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacionDTO {
        
    private String mensaje;
    private LocalDateTime fechaEnvio;

    public NotificacionDTO(){}

    public NotificacionDTO(Notificacion notificacion) {
        this.mensaje = notificacion.getMensaje();
      this.fechaEnvio = notificacion.getFechaEnvio();
    }
     
}
