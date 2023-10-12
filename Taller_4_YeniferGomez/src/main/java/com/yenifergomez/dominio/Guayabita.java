package com.yenifergomez.dominio;

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
        listaIcons = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            listaIcons[i] = new ImageIcon("dado" + (i + 1) + ".png");
        }
    }

    public void adicionarJugador(Jugador jugador) {
        jugadoresGuayabita.add(jugador);
    }

    public void jugarGuayabita() {
        int nroJugadores = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el número de jugadores:", "Guayabita", JOptionPane.QUESTION_MESSAGE,grupo,null,null));
        int apuestaMinima = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Ingrese la apuesta inicial mínima:","Guayabita",JOptionPane.QUESTION_MESSAGE,guayaba,null,null));

        for (int i = 1; i <= nroJugadores; i++) {
            String nombreJugador = (String) JOptionPane.showInputDialog(null,"Ingrese el nombre del jugador " + i + ":","Nombre de Jugadores",JOptionPane.QUESTION_MESSAGE,persona,null,null);
            int dinero = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Ingrese la cantidad de dinero inicial para " + nombreJugador + ":","Dinero inicial",JOptionPane.QUESTION_MESSAGE,icondinero,null,null));

            while (dinero < apuestaMinima) {
                JOptionPane.showMessageDialog(null, "La cantidad inicial debe ser al menos igual a la apuesta mínima.", "Error", JOptionPane.ERROR_MESSAGE,guayaba);
                dinero = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Ingrese la cantidad de dinero inicial para " + nombreJugador + ":","Dinero inicial",JOptionPane.QUESTION_MESSAGE,icondinero,null,null));
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

                int respuesta = JOptionPane.showConfirmDialog(null, jugador.getNombreJugador() + ", ¿deseas lanzar el dado?\n\nPote actual: $" + pote, "Turno de " + jugador.getNombreJugador(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, listaIcons[dado - 1]);

                if (respuesta == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Resultado del dado: " + dado, "Resultado del dado", JOptionPane.INFORMATION_MESSAGE, listaIcons[dado - 1]);
                } else {
                    jugador.eliminado();
                    continue;
                }

                int betCantidad = 0;
                if (dado >= 2 && dado <= 5) {
                    String betCantidadresp = (String) JOptionPane.showInputDialog(null,"¿Cuánto deseas apostar, " + jugador.getNombreJugador() + "? (0 para no apostar)","Apuesta",JOptionPane.QUESTION_MESSAGE,icondinero,null,null);

                    try {
                        betCantidad = Integer.parseInt(betCantidadresp);
                        if (betCantidad < 1 || betCantidad > jugador.getDineroJugador()) {
                            JOptionPane.showMessageDialog(null, "Cantidad de apuesta no válida. Debes apostar entre 1 y " + jugador.getDineroJugador(), "Error", JOptionPane.ERROR_MESSAGE, icondinero);
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Cantidad de apuesta no válida. Debes apostar entre 1 y " + jugador.getDineroJugador(), "Error", JOptionPane.ERROR_MESSAGE, icondinero);
                        continue;
                    }

                    jugador.restarDinero(betCantidad);
                    pote += betCantidad;

                    int newDado = random.nextInt(6) + 1;
                    JOptionPane.showMessageDialog(null, "Nuevo resultado del dado: " + newDado+"\n\nPote actual: $" + pote+"\n\nTu dinero actual es de: $" + jugador.getDineroJugador(), "Resultado del dado", JOptionPane.INFORMATION_MESSAGE,listaIcons[dado - 1] );

                    if (newDado > prevDado) {
                        int restarApuesta = pote - betCantidad;
                        jugador.sumarDinero(betCantidad);
                        pote -= restarApuesta;
                        JOptionPane.showMessageDialog(null, "¡Felicidades, " + jugador.getNombreJugador() + "! Has ganado el pote de $" + betCantidad+"\n\nPote actual: $" + pote, "Resultado del dado", JOptionPane.INFORMATION_MESSAGE, ganador);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lo siento, " + jugador.getNombreJugador() + ". Has perdido $" + betCantidad +"\n\nPote actual: $" + pote, "Resultado del dado", JOptionPane.ERROR_MESSAGE, perdedor);
                    }
                }

                if (jugador.getDineroJugador() <= 0) {
                    jugador.eliminado();
                }
                if (pote <= 0) {
                    juegoActivo = false;
                }
            }

            int jugadoresActivos = jugadoresGuayabita.size();
            for (Jugador jugador : jugadoresGuayabita) {
                if (jugador.isEliminado()) {
                    jugadoresActivos--;
                }
            }

            if (jugadoresActivos <= 1 || pote <= 0) {
                juegoActivo = false;
            }
        }

        Jugador ganador = null;
        int maxDinero = 0;
        for (Jugador jugador : jugadoresGuayabita) {
            if (!jugador.isEliminado() && jugador.getDineroJugador() > maxDinero) {
                ganador = jugador;
                maxDinero = jugador.getDineroJugador();
            }
        }

        if (ganador != null) {
            JOptionPane.showMessageDialog(null, "¡Felicidades, " + ganador.getNombreJugador() + "! Eres el jugador más rico con $" + ganador.getDineroJugador(), "Ganador", JOptionPane.INFORMATION_MESSAGE,grupo);
        } else {
            JOptionPane.showMessageDialog(null, "No hay un jugador ganador, todos han sido eliminados o el pote está vacío.", "Final del juego", JOptionPane.INFORMATION_MESSAGE, guayaba);
        }
    }

}

