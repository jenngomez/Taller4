package com.yenifergomez.dominio;

import javax.swing.*;

public class Jugador {
    private String nombreJugador;
    private int dineroJugador;
    private boolean eliminado;

    public Jugador(String nombreJugador, int dineroJugador) {
        this.nombreJugador = nombreJugador;
        this.dineroJugador = dineroJugador;
        this.eliminado = false;
    }

    public Jugador() {

    }

    public void eliminado() {
        eliminado = true;
    }
    public void sumarDinero(int cantidad) {
        dineroJugador += cantidad;
    }

    public void restarDinero(int cantidad) {
        dineroJugador -= cantidad;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public int getDineroJugador() {
        return dineroJugador;
    }


    public boolean isEliminado() {
        return eliminado;
    }
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public void setDineroJugador(int dineroJugador) {
        this.dineroJugador = dineroJugador;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }




}

