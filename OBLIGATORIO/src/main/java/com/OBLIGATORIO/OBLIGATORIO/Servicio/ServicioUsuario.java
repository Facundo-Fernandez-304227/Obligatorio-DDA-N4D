package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoDeshabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoHabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPenalizado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoSuspendido;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Interfaces.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Notificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
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
            if (u.getCedula().equals(cedula) && u.getContrasenia().equals(contrasenia)) {
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

    public void asignarBonificacion(String cedulaPropietario, Bonificacion bonificacion, Puesto puesto)
            throws UsuarioException {

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

    public void actualizarEstadoPropietario(String cedula, String estadoNuevo) throws UsuarioException {

        UsuarioPropietario propietario = buscarPropietarioPorCedula(cedula);

        if (propietario == null) {
            throw new UsuarioException("No existe un propietario con esa cédula.");
        }

        EstadoPropietario nuevoEstado;

        if (estadoNuevo.equals("Habilitado")) {
            nuevoEstado = new EstadoHabilitado();
        } else if (estadoNuevo.equals("Deshabilitado")) {
            nuevoEstado = new EstadoDeshabilitado();
        } else if (estadoNuevo.equals("Suspendido")) {
            nuevoEstado = new EstadoSuspendido();
        } else if (estadoNuevo.equals("Penalizado")) {
            nuevoEstado = new EstadoPenalizado();
        } else {
            throw new UsuarioException("Estado inválido.");
        }

        propietario.cambiarEstado(nuevoEstado);
    }

}
