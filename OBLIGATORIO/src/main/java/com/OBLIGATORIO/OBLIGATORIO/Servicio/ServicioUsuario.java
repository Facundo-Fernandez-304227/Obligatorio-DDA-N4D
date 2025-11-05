package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;

public class ServicioUsuario {

    private List<Usuario> usuarios = new ArrayList<Usuario>();

    public void agregarPropietario(UsuarioPropietario prop) {
        usuarios.add(prop);
    }

    public void agregarAdministrador(UsuarioAdministrador admin) {
        usuarios.add(admin);
    }

    public Usuario login(String cedula, String contrasenia) throws UsuarioException {
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(cedula) && u.contraseniaValida(contrasenia)) {
                return u;
            }
        }
        throw new UsuarioException("Usuario y/o contraseña incorrectos.");
    }

    public UsuarioPropietario buscarPropietarioPorCedula(String cedula) {
        for (Usuario usuario : usuarios) {
            if (usuario instanceof UsuarioPropietario && usuario.getCedula().equals(cedula)) {
                return (UsuarioPropietario) usuario;
            }
        }
        return null;
    }

    public void asignarBonificacion(String cedulaPropietario, Bonificacion bonificacion, Puesto puesto) throws UsuarioException {
        
        if (cedulaPropietario == null || bonificacion == null || puesto == null) {
            throw new UsuarioException("Datos inválidos para asignar la bonificación.");

        }

        UsuarioPropietario propietario = null;

        for (Usuario u : usuarios) {
            if (u instanceof UsuarioPropietario && u.getCedula().equals(cedulaPropietario)) {
                propietario = (UsuarioPropietario) u;
                break;
            }
        }

        if (propietario == null) {
            throw new UsuarioException("No se encontró el propietario con cédula " + cedulaPropietario);
        }
        
        propietario.asignarBonificacion(bonificacion, puesto);
        
    }

}
