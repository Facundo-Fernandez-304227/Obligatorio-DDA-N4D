package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.EstadoDTO;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoDeshabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoHabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPenalizado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoSuspendido;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

@RestController
@RequestMapping("/admin")
public class ControladorCambioEstado {

    @PostMapping("/actualizarEstado")
    public List<Respuesta> actualizarEstado(@RequestParam String cedula, @RequestParam String estadoNuevo) {

        UsuarioPropietario propietario = Fachada.getInstancia().buscarPropietarioPorCedula(cedula);

        if (propietario == null) {
            return Respuesta.lista(new Respuesta("error", "No existe un propietario con esa cédula."));
        }

        EstadoPropietario nuevoEstado;

        switch (estadoNuevo) {
            case "Habilitado":
                nuevoEstado = new EstadoHabilitado();
                break;
            case "Deshabilitado":
                nuevoEstado = new EstadoDeshabilitado();
                break;
            case "Suspendido":
                nuevoEstado = new EstadoSuspendido();
                break;
            case "Penalizado":
                nuevoEstado = new EstadoPenalizado();
                break;
            default:
                return Respuesta.lista(new Respuesta("error", "Estado inválido."));
        }

        // Asignar correctamente el estado usando tu patrón State
        propietario.setEstado(nuevoEstado);

        return Respuesta.lista(new Respuesta("actualizarEstado", "Estado actualizado correctamente."));
    }

    @GetMapping("/buscarPropietarioEstado")
    public List<Respuesta> buscarPropietarioEstado(@RequestParam String cedula) {

        UsuarioPropietario propietario = Fachada.getInstancia().buscarPropietarioPorCedula(cedula);

        if (propietario == null) {
            return Respuesta.lista(
                    new Respuesta("error", "No existe un propietario con esa cédula."));
        }

        Map<String, Object> datos = new HashMap<>();
        datos.put("nombre", propietario.getNombreCompleto());
        datos.put("estado", propietario.getEstado().getNombre());

        return Respuesta.lista(new Respuesta("buscarPropietarioEstado", datos));
    }

    @GetMapping("/cambiarEstado")
    public List<Respuesta> iniciarVista() {

        // Obtener los estados desde fachada
        List<EstadoPropietario> lista = Fachada.getInstancia().getEstados();

        // Convertirlos a DTO
        List<EstadoDTO> estadosDTO = new ArrayList<>();
        for (EstadoPropietario e : lista) {
            estadosDTO.add(new EstadoDTO(e));
        }

        // Enviar respuesta
        Map<String, Object> datos = new HashMap<>();
        datos.put("estados", estadosDTO);

        return Respuesta.lista(
            new Respuesta("cambiarEstado", datos)
        );
    }

}
