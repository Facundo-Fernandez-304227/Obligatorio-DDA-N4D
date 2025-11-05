package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Transito;
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
        return servicioUsuario.login(cedula, contrasenia);
    }

    public void agregarVehiculo(Vehiculo vehiculo1) throws VehiculoException {
        servicioVehiculos.agregarVehiculo(vehiculo1);
    }

    public UsuarioPropietario buscarPropietarioPorCedula(String cedula) {
        return servicioUsuario.buscarPropietarioPorCedula(cedula);
    }

    public void agregarTransito(Transito transito1) throws VehiculoException {
        servicioVehiculos.agregarTransito(transito1);
    }

    public void asignarBonificacion(String cedulaPropietario, Bonificacion bonificacion, Puesto puesto) throws UsuarioException {
        servicioUsuario.asignarBonificacion(cedulaPropietario, bonificacion, puesto);
    }
}
