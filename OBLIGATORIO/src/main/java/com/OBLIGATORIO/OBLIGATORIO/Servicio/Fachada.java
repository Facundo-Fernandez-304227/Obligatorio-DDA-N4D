package com.OBLIGATORIO.OBLIGATORIO.Servicio;

import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.BonificacionException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.EstadoException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.PuestoException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Interfaces.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.TarifaPuesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Transito;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Observador.Observable;

public class Fachada extends Observable {

    // ENUM DE CAMBIOS ACA?

    private static Fachada instancia;
    private ServicioUsuario servicioUsuario = new ServicioUsuario();
    private ServicioVehiculos servicioVehiculos = new ServicioVehiculos();
    private ServicioPuesto servicioPuesto = new ServicioPuesto();
    private ServicioBonificacion servicioBonificacion = new ServicioBonificacion();
    private ServicioEstado servicioEstado = new ServicioEstado();

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

    public void asignarBonificacion(String cedulaPropietario, Bonificacion bonificacion, Puesto puesto)
            throws UsuarioException {
        servicioUsuario.asignarBonificacion(cedulaPropietario, bonificacion, puesto);
    }

    public void agregarPuesto(Puesto puesto) {
        servicioPuesto.agregarPuesto(puesto);
    }

    public Vehiculo buscarVehiculoPorMatricula(String matricula) throws VehiculoException {
        return servicioVehiculos.buscarVehiculoPorMatricula(matricula);
    }

    public List<Puesto> getPuestos() {
        return servicioPuesto.getPuestos();
    }

    public Puesto buscarPuestoPorNombre(String nombrePuesto) {
        return servicioPuesto.buscarPuestoPorNombre(nombrePuesto);
    }

    public void agregarTarifaPuesto(TarifaPuesto tarifaPuesto) throws PuestoException {
        servicioPuesto.agregarTarifaPuesto(tarifaPuesto);
    }

    public void avisar(Object evento) {
        super.avisar(evento);
    }

    public void agregarBonificacion(Bonificacion bonificacion) throws BonificacionException {
        servicioBonificacion.agregarBonificacion(bonificacion);
    }

    public List<Bonificacion> getBonificaciones() {
        return servicioBonificacion.getBonificaciones();
    }

    public Bonificacion buscarBonificacionPorNombre(String nombre) {
        return servicioBonificacion.buscarBonificacionPorNombre(nombre);
    }

    public void agregarEstado(EstadoPropietario estado) throws EstadoException {
        servicioEstado.agregarEstado(estado);
    }

    public List<EstadoPropietario> getEstados() {
        return servicioEstado.getEstados();
    }

}
