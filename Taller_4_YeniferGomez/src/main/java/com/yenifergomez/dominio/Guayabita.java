package com.yenifergomez.dominio;

import javax.naming.InvalidNameException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Guayabita {
    private List<Jugador> jugadoresGuayabita;
    private int pote;
    private int apuestaMinima=500;
    private Random random;
    private ImageIcon[] listaIcons;
    private ImageIcon grupo;
    private ImageIcon guayaba;
    private ImageIcon persona;
    private ImageIcon icondinero;
    private ImageIcon ganador;
    private ImageIcon perdedor;
    private ImageIcon poteGana;
    private ImageIcon lanzar;


    public Guayabita() {
        jugadoresGuayabita = new ArrayList<>();
        pote = 0;
        random = new Random();
        perdedor = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/perdedor.png");
        ganador = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/ganador.png");
        icondinero = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/dinero.png");
        persona = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/persona.png");
        guayaba = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/guayaba.png");
        grupo = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller4/src/main/java/org/example/jugador.png");
        poteGana = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/pote.png");
        lanzar = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/dominio/lanzardado.png");

        listaIcons = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            String rutaDado = "imagenesdados/dado" + (i + 1) + ".png";
            listaIcons[i] = new ImageIcon(rutaDado);
        }
    }

    public void jugarGuayabita() {

        int numJugadores = 0;
        while (true) {
            try {
                String numJugadoresInput = (String) JOptionPane.showInputDialog(null, "Ingrese el número de jugadores:", "Configuración", JOptionPane.QUESTION_MESSAGE, guayaba, null, null);
                numJugadores = Integer.parseInt(numJugadoresInput);
                if (numJugadores >= 1) {
                    break; // Número de jugadores válido, salir del bucle
                } else {
                    JOptionPane.showMessageDialog(null, "El número de jugadores debe ser al menos 1.", "Error", JOptionPane.ERROR_MESSAGE, grupo);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor numérico para el número de jugadores.", "Error", JOptionPane.ERROR_MESSAGE, guayaba);
            }
        }

        int apuestaMinima = 0;
        while (true) {
            try {
                String apuestaMinimaInput = (String) JOptionPane.showInputDialog(null, "Ingrese la apuesta inicial mínima:", "Configuración", JOptionPane.QUESTION_MESSAGE, guayaba, null, null);
                apuestaMinima = Integer.parseInt(apuestaMinimaInput);
                if (apuestaMinima >= 500) {
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "La apuesta mínima debe ser al menos $500.", "Error", JOptionPane.ERROR_MESSAGE, guayaba);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un valor numérico para la apuesta mínima.", "Error", JOptionPane.ERROR_MESSAGE, guayaba);
            }
        }

        for (int i = 1; i <= numJugadores; i++) {

            String nombreJugador = null;
            boolean nombreValido = false;
            while (!nombreValido){
                nombreJugador = (String) JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador " + i + ":","Nombre de Jugadores",JOptionPane.QUESTION_MESSAGE,persona,null,null);
                if (nombreJugador != null && nombreJugador.matches("^[a-zA-Z]+$")) {
                    nombreValido = true;
                } else {
                    JOptionPane.showMessageDialog(null, "El nombre debe contener solo letras. Inténtalo de nuevo.", "Error", JOptionPane.ERROR_MESSAGE, guayaba);
                }
            }
            int dinero = 0;
            boolean dineroValido = false;

            while (!dineroValido) {
                try {
                    String dineroInput = (String) JOptionPane.showInputDialog(null, "Ingrese la cantidad de dinero inicial para " + nombreJugador + ":", "Dinero inicial", JOptionPane.QUESTION_MESSAGE, icondinero, null, null);
                    dinero = Integer.parseInt(dineroInput);

                    if (dinero >= apuestaMinima) {
                        dineroValido = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "La cantidad inicial debe ser mayor o igual a la apuesta mínima.", "Error", JOptionPane.ERROR_MESSAGE, guayaba);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingresa un valor numérico para la cantidad de dinero.", "Error", JOptionPane.ERROR_MESSAGE, guayaba);
                }
            }


            Jugador jugador = new Jugador(nombreJugador, dinero - apuestaMinima);
            jugadoresGuayabita.add(jugador);
            pote += apuestaMinima;
        }

        boolean juegoActivo = true;

        while (juegoActivo) {
            for (Jugador jugador : jugadoresGuayabita) {
                if (jugador.isEliminado()) {
                    continue;
                }


                int dado = random.nextInt(6) + 1;
                int prevDado = dado;

                int respuesta = JOptionPane.showConfirmDialog(null, jugador.getNombreJugador() + ", ¿deseas lanzar el dado?\n\nPote actual: $" + pote, "Turno de " + jugador.getNombreJugador(), JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,lanzar);

                if (respuesta==JOptionPane.YES_OPTION){

                    int quiereApostar = JOptionPane.showConfirmDialog(null, jugador.getNombreJugador() + ", el resultado del dado es:" + dado + ". \n\n¿Deseas hacer una apuesta?", "Apostar", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, listaIcons[dado-1]);

                    if (quiereApostar == JOptionPane.YES_OPTION) {
                        if (dado >= 2 && dado <= 5) {

                        int betCantidad = 0;

                        while (true) {


                            try {
                                String betCantidadresp = (String) JOptionPane.showInputDialog(null, jugador.getNombreJugador() + ", ¿cuánto deseas apostar?\n\n-El pote está en: $" + pote + "\n-Tu dinero total es de: " + jugador.getDineroJugador(), "Apuesta", JOptionPane.QUESTION_MESSAGE, icondinero, null, null);

                                betCantidad = Integer.parseInt(betCantidadresp);

                                if (betCantidad >= 100 && betCantidad <= pote && betCantidad <= jugador.getDineroJugador()) {
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Cantidad de apuesta no válida. Debes apostar entre 100 y " + pote + " y no puedes apostar más de lo que tienes.", "Error", JOptionPane.ERROR_MESSAGE, icondinero);

                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Se deben ingresar solo números", "Error", JOptionPane.ERROR_MESSAGE, icondinero);
                            }
                        }

                            int newDado = random.nextInt(6) + 1;
                            JOptionPane.showMessageDialog(null, "Nuevo resultado del dado: " + newDado + "\n\nPote actual: $" + pote + "\n\nTu dinero actual es de: $" + jugador.getDineroJugador(), "Resultado del dado", JOptionPane.INFORMATION_MESSAGE, listaIcons[dado - 1]);

                            if (newDado > prevDado) {
                                jugador.sumarDinero(betCantidad);
                                pote -= betCantidad;
                                JOptionPane.showMessageDialog(null, "¡Felicidades, " + jugador.getNombreJugador() + "! Has ganado el pote de $" + betCantidad + "\n\nPote actual: $" + pote, "Resultado del dado", JOptionPane.INFORMATION_MESSAGE, ganador);
                            } else {
                                pote += betCantidad;
                                jugador.restarDinero(betCantidad);
                                JOptionPane.showMessageDialog(null, "Lo siento, " + jugador.getNombreJugador() + ". Has perdido $" + betCantidad + "\n\nPote actual: $" + pote, "Resultado del dado", JOptionPane.ERROR_MESSAGE, perdedor);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, jugador.getNombreJugador() + " ha decidido no apostar. Turno del siguiente jugador.", "No apostar", JOptionPane.INFORMATION_MESSAGE,persona);
                }


                if (jugador.getDineroJugador() <= 0) {
                    JOptionPane.showMessageDialog(null,jugador.getNombreJugador() + " ha sido eliminado por perder todo su dinero","Jugador Eliminado",JOptionPane.INFORMATION_MESSAGE,perdedor);
                    jugador.eliminado();
                }
                if (pote <= 0 && jugador.getDineroJugador()>0) {
                    juegoActivo = false;
                    JOptionPane.showMessageDialog(null, "El juego ha terminado. El pote está vacío.", "Final del juego", JOptionPane.INFORMATION_MESSAGE, guayaba);
                    break;
                }

            }
        }

        int jugadoresActivos = jugadoresGuayabita.size();
        for (Jugador jugador : jugadoresGuayabita) {
            if (jugador.isEliminado()) {
                jugadoresActivos--;
            }
        }

        if (jugadoresActivos <= 0) {
            JOptionPane.showMessageDialog(null, "El pote ha ganado. Todos los jugadores han sido eliminados y el pote tiene $" + pote, "Fin del juego", JOptionPane.INFORMATION_MESSAGE,poteGana);
        } else {
            Jugador ganador = null;
            int maxDinero = 0;
            for (Jugador jugador : jugadoresGuayabita) {
                if (!jugador.isEliminado() && jugador.getDineroJugador() > maxDinero) {
                    ganador = jugador;
                    maxDinero = jugador.getDineroJugador();
                }
            }
            if (ganador != null) {
                JOptionPane.showMessageDialog(null, "¡Felicidades, " + ganador.getNombreJugador() + "! Eres el jugador más rico con $" + ganador.getDineroJugador(), "Ganador", JOptionPane.INFORMATION_MESSAGE, guayaba);
            }
        }

    }


}

