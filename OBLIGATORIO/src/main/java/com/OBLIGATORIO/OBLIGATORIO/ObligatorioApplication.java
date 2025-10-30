package com.OBLIGATORIO.OBLIGATORIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.OBLIGATORIO.OBLIGATORIO.Excepciones.UsuarioException;
import com.OBLIGATORIO.OBLIGATORIO.Excepciones.VehiculoException;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.CategoriaVehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.Vehiculo;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;

@SpringBootApplication
public class ObligatorioApplication {

	public static void main(String[] args) throws VehiculoException, UsuarioException {
		SpringApplication.run(ObligatorioApplication.class, args);
		precargaDeDatos();
	}

	public static void precargaDeDatos() throws VehiculoException {

		UsuarioAdministrador admin1 = new UsuarioAdministrador("12345678", "admin.123", "Usuario Administrador");
		Fachada.getInstancia().agregarAdministrador(admin1);

		UsuarioPropietario propietario1 = new UsuarioPropietario("23456789", "prop.123", "Usuario Propietario", 2000,500);
		Fachada.getInstancia().agregarPropietario(propietario1);

		CategoriaVehiculo categoriaAuto = new CategoriaVehiculo("Auto");
		CategoriaVehiculo categoriaCamioneta = new CategoriaVehiculo("Camioneta");

		Vehiculo vehiculo1 = new Vehiculo("ABC-1234", "Sedan-123", "Negro", categoriaAuto, propietario1);
		Vehiculo vehiculo2 = new Vehiculo("ABC-5678", "Fiorino-123", "Blanco", categoriaCamioneta, propietario1);
		Fachada.getInstancia().agregarVehiculo(vehiculo1);
		Fachada.getInstancia().agregarVehiculo(vehiculo2);
	}
}
