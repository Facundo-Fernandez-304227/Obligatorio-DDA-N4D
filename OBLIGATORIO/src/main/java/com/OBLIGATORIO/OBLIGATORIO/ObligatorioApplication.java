package com.OBLIGATORIO.OBLIGATORIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioAdministrador;
import com.OBLIGATORIO.OBLIGATORIO.Modelo.UsuarioPropietario;
import com.OBLIGATORIO.OBLIGATORIO.Servicio.Fachada;

@SpringBootApplication
public class ObligatorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObligatorioApplication.class, args);
		precargaDeDatos();
	}


	public static void precargaDeDatos(){

		UsuarioAdministrador admin1 = new UsuarioAdministrador("12345678", "admin.123", "Usuario Administrador");
		Fachada.getInstancia().agregarAdministrador(admin1);

		UsuarioPropietario propietario1 = new UsuarioPropietario("23456789", "prop.123", "Usuario Propietario", 2000, 500);
		Fachada.getInstancia().agregarPropietario(propietario1);

		
	}
}
