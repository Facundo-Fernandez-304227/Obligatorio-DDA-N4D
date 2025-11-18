package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.BonificacionDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.PropietarioDTO;
import com.OBLIGATORIO.OBLIGATORIO.Dtos.PuestoDTO;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

@RestController
@RequestMapping("/admin")
public class ControladorAsignarBonificacion {

    @GetMapping("/asignarBonificaciones")
    public List<Respuesta> iniciarVistaAsignarBonificaciones() {

        List<Bonificacion> bonificaciones = Fachada.getInstancia().getBonificaciones();

        List<Puesto> puestos = Fachada.getInstancia().getPuestos();

        List<BonificacionDTO> listaBonificacionesDTO = bonificaciones.stream()
                .map(BonificacionDTO::new)
                .toList();

        // 3) Convertir puestos a DTO
        List<PuestoDTO> listaPuestosDTO = puestos.stream()
                .map(PuestoDTO::new)
                .toList();

        // 4) Agrupar datos para enviar al frontend
        Map<String, Object> datos = new HashMap<>();
        datos.put("bonificaciones", listaBonificacionesDTO);
        datos.put("puestos", listaPuestosDTO);

        // 5) Respuesta al frontend
        return Respuesta.lista(new Respuesta("asignarBonificaciones", datos));

    }

    @GetMapping("/buscarPropietario")
    public List<Respuesta> buscarPropietario(@RequestParam String cedula) {

        UsuarioPropietario propietario = Fachada.getInstancia().buscarPropietarioPorCedula(cedula);

        if (propietario == null) {
            return Respuesta.lista(new Respuesta("error", "No existe un propietario con esa cédula."));
        }

        PropietarioDTO dto = new PropietarioDTO(propietario);
        
        return Respuesta.lista(new Respuesta("buscarPropietario", dto));
    }

    @PostMapping("/asignarBonificacion")
    public List<Respuesta> asignar(@RequestParam String cedula, @RequestParam String bonificacion, @RequestParam String puesto) {
        
        try {

            UsuarioPropietario propietario = Fachada.getInstancia().buscarPropietarioPorCedula(cedula);

            if (propietario == null) {
                return Respuesta.lista(new Respuesta("error", "No existe un propietario con esa cédula."));
            }

            Bonificacion bon = Fachada.getInstancia().buscarBonificacionPorNombre(bonificacion);

            if (bon == null) {
                return Respuesta.lista(new Respuesta("error", "La bonificación seleccionada no existe."));
            }

            Puesto p = Fachada.getInstancia().buscarPuestoPorNombre(puesto);

            if (p == null) {
                return Respuesta.lista(new Respuesta("error", "El puesto seleccionado no existe."));
            }

            Fachada.getInstancia().asignarBonificacion(propietario.getCedula(), bon, p);

            return Respuesta.lista(new Respuesta("asignarBonificacion", "Bonificación asignada"));

        } catch (UsuarioException e) {
            return Respuesta.lista(new Respuesta("error", e.getMessage()));
        }
    }

}
