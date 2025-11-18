package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoHabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Interfaces.Usuario;
import com.OBLIGATORIO.OBLIGATORIO.Observador.Observable;
import com.OBLIGATORIO.OBLIGATORIO.Observador.Observador;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;

import lombok.Getter;

public class UsuarioPropietario implements Usuario, Observador {

    @Getter
    private String cedula;
    @Getter
    private String contrasenia;
    @Getter
    private String nombreCompleto;
    @Getter
    private double saldoActual;
    @Getter
    private double saldoMinimoAlerta;
    @Getter
    private List<Vehiculo> vehiculosPropietario;
    @Getter
    private EstadoPropietario estado;
    @Getter
    private List<BonificacionAsignada> bonificacionAsignadas;
    @Getter
    List<Notificacion> notificaciones;

    public UsuarioPropietario(String cedula, String contrasenia, String nombreCompleto, double saldoActual,
            double saldoMinimoAlerta) {
        this.cedula = cedula;
        this.contrasenia = contrasenia;
        this.nombreCompleto = nombreCompleto;
        this.saldoActual = saldoActual;
        this.saldoMinimoAlerta = saldoMinimoAlerta;
        this.vehiculosPropietario = new ArrayList<Vehiculo>();
        this.estado = new EstadoHabilitado();
        this.bonificacionAsignadas = new ArrayList<BonificacionAsignada>();
        this.notificaciones = new ArrayList<Notificacion>();
    }

    public void agregarVehiculo(Vehiculo vehiculo1) throws VehiculoException {
        for (Vehiculo v : vehiculosPropietario) {
            if (vehiculo1.getMatriculaVehiculo().equals(v.getMatriculaVehiculo())) {
                throw new VehiculoException("El propietario ya tiene ese vehiculo.");
            }
        }
        vehiculosPropietario.add(vehiculo1);
        vehiculo1.setUsuarioPropietario(this);
    }

    public void asignarBonificacion(Bonificacion bon, Puesto puesto) throws UsuarioException {

        for (BonificacionAsignada b : bonificacionAsignadas) {
            if (b.getBonificacion().getNombre().equalsIgnoreCase(bon.getNombre())) {
                throw new UsuarioException("El propietario ya tiene esa bonificación.");
            }
        }

        BonificacionAsignada nuevaBonificacion = new BonificacionAsignada(this, bon, puesto, LocalDate.now());

        bonificacionAsignadas.add(nuevaBonificacion);

        Notificacion noti = new Notificacion(LocalDateTime.now(), "Se te asignó la bonificación \"" + bon.getNombre()
                + "\" en el puesto \"" + puesto.getNombrePuesto() + "\".");
        this.agregarNotificacion(noti);

        Fachada.getInstancia().avisar("BONIFICACION_ASIGNADA");
    }

    @Override
    public void actualizar(Observable observable, Object evento) {
        if (evento instanceof Notificacion noti) {
            notificaciones.add(noti);
            for (Notificacion n : notificaciones) {
                System.out.println(n.getFechaEnvio() + " - " + n.getMensaje());
            }
        }
    }

    public void agregarNotificacion(Notificacion noti) {
        notificaciones.add(noti);
    }

    public void setSaldoActual(double nuevoSaldo) {

        this.saldoActual = nuevoSaldo;

        if (this.saldoActual <= this.saldoMinimoAlerta) {

            Notificacion noti = new Notificacion(LocalDateTime.now(),
                    "Tu saldo ha llegado al mínimo permitido (" + saldoActual + ").");

            this.agregarNotificacion(noti);

            Fachada.getInstancia().avisar("SALDO_MINIMO");
        }
    }

    public void cambiarEstado(EstadoPropietario nuevoEstado) {

        this.estado = nuevoEstado;
        nuevoEstado.setPropietario(this);

        Notificacion noti = new Notificacion(LocalDateTime.now(),
                "Tu estado ha cambiado a: " + nuevoEstado.getNombre());

        this.agregarNotificacion(noti);

        // avisar SSE que debe refrescar tablero
        Fachada.getInstancia().avisar("ESTADO_CAMBIADO");
    }

    public void limpiarNotificaciones() {
        if (notificaciones != null) {
            notificaciones.clear();
        }
    }

}
