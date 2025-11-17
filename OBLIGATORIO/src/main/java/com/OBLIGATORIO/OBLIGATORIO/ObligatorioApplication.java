package com.OBLIGATORIO.OBLIGATORIO;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoDeshabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoHabilitado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPenalizado;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Estado.EstadoSuspendido;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.BonificacionException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.EstadoException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.PuestoException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Bonificacion;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.BonificacionExonerados;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.BonificacionFrecuente;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.BonificacionTrabajadores;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.CategoriaVehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Puesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.TarifaPuesto;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Transito;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;

@SpringBootApplication
public class ObligatorioApplication {

	public static void main(String[] args)
		throws VehiculoException, UsuarioException, PuestoException, BonificacionException, EstadoException {
		SpringApplication.run(ObligatorioApplication.class, args);
		precargaDeDatos();
	}

	public static void precargaDeDatos()
			throws VehiculoException, UsuarioException, PuestoException, BonificacionException, EstadoException {

		UsuarioAdministrador admin1 = new UsuarioAdministrador("12345678", "admin.123", "Usuario Administrador");
		Fachada.getInstancia().agregarAdministrador(admin1);

		UsuarioPropietario propietario1 = new UsuarioPropietario("23456789", "prop.123", "Usuario Propietario", 2000, 500);

		Fachada.getInstancia().agregarPropietario(propietario1);

<<<<<<< HEAD
		//esta bien esto??
		Fachada.getInstancia().agregarObservador(propietario1);
=======
>>>>>>> 5464db1f99acfd404cb3a060add250796c38481c

		CategoriaVehiculo categoriaAuto = new CategoriaVehiculo("Auto");
		CategoriaVehiculo categoriaCamioneta = new CategoriaVehiculo("Camioneta");

		Vehiculo vehiculo1 = new Vehiculo("ABC-1234", "Sedan-123", "Negro", categoriaAuto, propietario1);
		Vehiculo vehiculo2 = new Vehiculo("ABC-5678", "Fiorino-123", "Blanco", categoriaCamioneta, propietario1);
		Fachada.getInstancia().agregarVehiculo(vehiculo1);
		Fachada.getInstancia().agregarVehiculo(vehiculo2);

		Puesto puesto = new Puesto("Puesto - 111", "Ruta Interbalnearia");
		Puesto puesto2 = new Puesto("Puesto - 222", "Ruta 5");

		Fachada.getInstancia().agregarPuesto(puesto);
		Fachada.getInstancia().agregarPuesto(puesto2);

		TarifaPuesto tarifaPuesto = new TarifaPuesto(100, categoriaAuto, puesto);
		TarifaPuesto tarifaPuesto2 = new TarifaPuesto(150, categoriaCamioneta, puesto2);

		Fachada.getInstancia().agregarTarifaPuesto(tarifaPuesto);
		Fachada.getInstancia().agregarTarifaPuesto(tarifaPuesto2);

		Bonificacion bonificacion = new BonificacionFrecuente();
		Bonificacion bonificacion2 = new BonificacionTrabajadores();
		Bonificacion bonificacion3 = new BonificacionExonerados();

		Fachada.getInstancia().agregarBonificacion(bonificacion);
		Fachada.getInstancia().agregarBonificacion(bonificacion2);
		Fachada.getInstancia().agregarBonificacion(bonificacion3);

		// Fachada.getInstancia().asignarBonificacion("23456789", bonificacion, puesto);

		Transito transito1 = new Transito(LocalDate.of(2025, 9, 19), LocalTime.of(18, 45), 120.00, vehiculo1, puesto, bonificacion3);
		Fachada.getInstancia().agregarTransito(transito1);

		EstadoPropietario habilitado = new EstadoHabilitado();
		EstadoPropietario deshabilitado = new EstadoDeshabilitado();
		EstadoPropietario penalizado = new EstadoPenalizado();
		EstadoPropietario suspendido = new EstadoSuspendido();

		Fachada.getInstancia().agregarEstado(habilitado);
		Fachada.getInstancia().agregarEstado(deshabilitado);
		Fachada.getInstancia().agregarEstado(penalizado);
		Fachada.getInstancia().agregarEstado(suspendido);

	}
}
