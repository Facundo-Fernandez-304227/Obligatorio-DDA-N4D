package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.NotificacionDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.PropietarioDTO;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.ServicioUsuario;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/propietario")
public class ControladorMenuPropietario {

    @GetMapping("/tablero")
    public List<Respuesta> cargarTablero(HttpSession sesion) {

        Object usuario = sesion.getAttribute("usuarioLogueado");

        if (usuario == null) {
            // Redirige al login
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "login.html"));
        }

        if (!(usuario instanceof UsuarioPropietario propietario)) {
            // Si el usuario no es propietario redrigie al login
            return Respuesta.lista(new Respuesta("usuarioNoAutenticado", "login.html"));
        }

        for (Vehiculo v : propietario.getVehiculosPropietario()) {
            System.out.println(" - " + v.getMatriculaVehiculo() + " / " + v.getModeloVehiculo());
        }

        // Creamos el DTO con los datos del propietario
        PropietarioDTO dto = new PropietarioDTO(propietario);

        // El "id" debe coincidir con la función en tu HTML → mostrar_tablero()
        return Respuesta.lista(new Respuesta("tablero", dto));

    }


@GetMapping("/notificaciones")
public List<NotificacionDTO> obtenerNotificaciones(
        @SessionAttribute("usuarioLogueado") UsuarioPropietario propietario) {

    List<NotificacionDTO> notiDTOs = new ArrayList<>();
    for (Notificacion n : propietario.getNotificaciones()) {
        NotificacionDTO dto = new NotificacionDTO();
        dto.setFechaEnvio(n.getFechaEnvio());
        dto.setMensaje(n.getMensaje());
        notiDTOs.add(dto);
    }

    return notiDTOs;
}

}
