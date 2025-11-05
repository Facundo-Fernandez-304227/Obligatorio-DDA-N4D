package com.OBLIGATORIO.OBLIGATORIO.Modelo;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class Vehiculo {

    @Getter
    private String matriculaVehiculo;
    @Getter
    private String modeloVehiculo;
    @Getter
    private String colorVehiculo;
    @Getter
    private CategoriaVehiculo categoriaVehiculo;
    @Getter
    private UsuarioPropietario usuarioPropietario;
    @Getter
    private List<Transito> transitos;

    public Vehiculo(String matriculaVehiculo, String modeloVehiculo, String colorVehiculo,
            CategoriaVehiculo categoriaVehiculo, UsuarioPropietario usuprop) {
        this.matriculaVehiculo = matriculaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.colorVehiculo = colorVehiculo;
        this.categoriaVehiculo = categoriaVehiculo;
        this.usuarioPropietario = usuprop;
        this.transitos = new ArrayList<Transito>();
    }

    public void setUsuarioPropietario(UsuarioPropietario usuarioPropietario) {
        this.usuarioPropietario = usuarioPropietario;
    }

    public double getMontoTransitos() {
        if (transitos == null || transitos.isEmpty())
            return 0;

        double montoTotal = 0;

        for (Transito transito : transitos) {
            montoTotal += transito.getMontoTransito();
        }
        return montoTotal;
    }
}
