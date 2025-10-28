package com.OBLIGATORIO.OBLIGATORIO.Controladores;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;
import com.OBLIGATORIO.OBLIGATORIO.Utils.Respuesta;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/acceso")
public class ControladorLogin {

    @PostMapping("/login")
    public List<Respuesta> login(HttpSession sesionHttp, @RequestParam String cedula, @RequestParam String contrasenia)
            throws UsuarioException {
        Usuario usuarioLogueado = Fachada.getInstancia().login(cedula, contrasenia);
        // ESTO GUARDIA LA SESION DEL USUARIO
        sesionHttp.setAttribute("usuarioLogueado", usuarioLogueado);
        
        if (usuarioLogueado instanceof UsuarioAdministrador) {
            // Redirigir a la vista del administrador
            return Respuesta.lista(new Respuesta("loginExitoso", "menuAdministrador.html"));
        } else if (usuarioLogueado instanceof UsuarioPropietario) {
            // Redirigir a la vista del propietario
            return Respuesta.lista(new Respuesta("loginExitoso", "menuPropietario.html"));
        } else {
            // Si por alguna razón no es ninguno.
            throw new UsuarioException("Tipo de usuario no reconocido. Acceso denegado.");
        }
        
    }
}
