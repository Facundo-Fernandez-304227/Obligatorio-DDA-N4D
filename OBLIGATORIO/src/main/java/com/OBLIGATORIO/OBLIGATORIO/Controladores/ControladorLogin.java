package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Interfaces.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/acceso")
public class ControladorLogin {

    @PostMapping("/login")
    public List<Respuesta> login(HttpSession sesionHttp,
            @RequestParam String cedula,
            @RequestParam String contrasenia) throws UsuarioException {

        Usuario usuario = Fachada.getInstancia().login(cedula, contrasenia);

        sesionHttp.setAttribute("usuarioLogueado", usuario);

        if (usuario instanceof UsuarioAdministrador) {
            return Respuesta.lista(new Respuesta("loginExitoso", "emularTransito.html"));
        }

        if (usuario instanceof UsuarioPropietario propietario) {

            if (!propietario.getEstado().puedeIngresarSistema()) {
                return Respuesta.lista(new Respuesta("error", "Usuario deshabilitado, no puede ingresar al sistema."));
            }

            return Respuesta.lista(new Respuesta("loginExitoso", "menuPropietario.html"));
        }

        return Respuesta.lista(new Respuesta("error", "Acceso denegado."));
    }

}
