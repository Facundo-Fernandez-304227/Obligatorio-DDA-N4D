package com.OBLIGATORIO.OBLIGATORIO.Observador;

import java.util.ArrayList;

public class Observable {
    private ArrayList<Observador> observadores = new ArrayList<>();

    public void agregarObservador(Observador observador) {
        
        if (!observadores.contains(observador)) {
            observadores.add(observador);
        }

    }

    public void quitarObservador(Observador observador) {
        observadores.remove(observador);
    }

    public void avisar(Object evento) {
        
        ArrayList<Observador> copiaObservador = new ArrayList<>(observadores);
        for (Observador observador : copiaObservador) {
            System.out.println("Avisando a observador: " + observador.getClass().getSimpleName() + "En observador");
            observador.actualizar(this, evento);
        }
    }

}