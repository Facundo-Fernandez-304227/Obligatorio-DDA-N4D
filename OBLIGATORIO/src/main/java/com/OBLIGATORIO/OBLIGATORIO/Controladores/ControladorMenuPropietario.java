package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.OBLIGATORIO.OBLIGATORIO.Observador.Observable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.OBLIGATORIO.OBLIGATORIO.ConexionNavegador;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.NotificacionDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.PropietarioDTO;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Observador.Observador;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/propietario")
@Scope("session")
public class ControladorMenuPropietario implements Observador {

    private final ConexionNavegador conexionNavegador;

    public ControladorMenuPropietario(@Autowired ConexionNavegador conexionNavegador) {
        this.conexionNavegador = conexionNavegador;

    }

    @Override
    public void actualizar(Observable observable, Object evento) {

        if (evento instanceof String ev) {

            if (ev.equals("ESTADO_CAMBIADO") || ev.equals("BONIFICACION_ASIGNADA") || ev.equals("SALDO_MINIMO")
                    || ev.equals("TRANSITO_REGISTRADO")) {

                // Crear "notificación" para refrescar tablero
                NotificacionDTO refresco = new NotificacionDTO();
                refresco.setMensaje("REFRESCAR_TABLERO");
                refresco.setFechaEnvio(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                conexionNavegador.enviarJSON(refresco);
                return;
            }
        }

        if (evento instanceof Notificacion noti) {
            NotificacionDTO dto = new NotificacionDTO(noti);
            conexionNavegador.enviarJSON(dto);
        }
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE() {

        conexionNavegador.conectarSSE();
        return conexionNavegador.getConexionSSE();

    }

    @GetMapping("/tablero")
    public List<Respuesta> cargarTablero(HttpSession sesion) {

        Object usuario = sesion.getAttribute("usuarioLogueado");

        if (usuario == null) {
            // Redirige al login
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "login.html"));
        }

        if (!(usuario instanceof UsuarioPropietario propietario)) {
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "login.html"));
        }

        // System.out.println(">>> Notificaciones en propietario: " +
        // propietario.getNotificaciones().size());

        Fachada.getInstancia().agregarObservador(this);
        Fachada.getInstancia().agregarObservador(propietario);

        // FALTA DESSUSCRIBIR CUANDO SE CIERRA SESION

        PropietarioDTO dto = new PropietarioDTO(propietario);

        return Respuesta.lista(new Respuesta("tablero", dto));

    }

    @GetMapping("/notificaciones")
    public List<NotificacionDTO> obtenerNotificaciones(
            @SessionAttribute("usuarioLogueado") UsuarioPropietario propietario) {

        List<NotificacionDTO> notiDTOs = new ArrayList<>();
        for (Notificacion n : propietario.getNotificaciones()) {
            NotificacionDTO dto = new NotificacionDTO();
            dto.setFechaEnvio(n.getFechaEnvioStr());
            dto.setMensaje(n.getMensaje());
            notiDTOs.add(dto);
        }

        return notiDTOs;
    }

    @PostMapping("/borrarNotificaciones")
    public List<Respuesta> borrarNotificaciones( @SessionAttribute("usuarioLogueado") UsuarioPropietario propietario) {

        if (propietario.getNotificaciones().isEmpty()) {
            return Respuesta.lista(new Respuesta("notificacionesBorradas", "No hay notificaciones para borrar."));
        }

        propietario.limpiarNotificaciones();

        return Respuesta.lista(new Respuesta("notificacionesBorradas", "Se borraron las notificaciones correctamente."));
    }

}
