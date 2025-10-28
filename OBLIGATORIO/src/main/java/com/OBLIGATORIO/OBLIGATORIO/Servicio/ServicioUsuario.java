package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;

public class ServicioUsuario {
    
    private List<Usuario> usuarios = new ArrayList<Usuario>();



    public void agregarPropietario(UsuarioPropietario prop){
        usuarios.add(prop);
    }

    public void agregarAdministrador(UsuarioAdministrador admin){
        usuarios.add(admin);
    }

    public Usuario login(String cedula, String contrasenia) throws UsuarioException {
        for(Usuario u : usuarios){
            if(u.getCedula().equals(cedula) && u.contraseniaValida(contrasenia)){
                return u;
            }
        }
        throw new UsuarioException("Usuario y/o contraseña incorrectos.");
    }

}
