package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OBLIGATORIO.OBLIGATORIO.Dtos.PropietarioDTO;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
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
}
