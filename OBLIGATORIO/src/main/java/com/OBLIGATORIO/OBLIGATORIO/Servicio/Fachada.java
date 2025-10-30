package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;

public class Fachada {

    private static Fachada instancia;
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private ServicioVehiculos servicioVehiculos = new ServicioVehiculos();

    private Fachada() {
    }

    public static Fachada getInstancia() {
        if (instancia == null) {
            instancia = new Fachada(); 
        }
        return instancia;
    }

    public void agregarPropietario(UsuarioPropietario prop) {
        servicioUsuario.agregarPropietario(prop);
    }

    public void agregarAdministrador(UsuarioAdministrador admin1) {
        servicioUsuario.agregarAdministrador(admin1);
    }

    public Usuario login(String cedula, String contrasenia) throws UsuarioException {
        return servicioUsuario.login(cedula,contrasenia);
    }

    public void agregarVehiculo(Vehiculo vehiculo1) throws VehiculoException {
        servicioVehiculos.agregarVehiculo(vehiculo1);
    }

}
