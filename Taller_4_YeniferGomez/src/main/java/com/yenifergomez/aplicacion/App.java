package com.yenifergomez.aplicacion;

import com.yenifergomez.dominio.Guayabita;


import javax.swing.*;

public class App {

    public static void main(String[] args) {


        while (true) {

            Guayabita juego = new Guayabita();

            ImageIcon icon = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/aplicacion/guayaba.png");
            ImageIcon icon2 = new ImageIcon("/Users/jennifergomez/Documents/TÉCNICA EN SISTEMAS/DISEÑO SOFTWARE IV/INTELLIJ/Taller_4_YeniferGomez/src/main/java/com/yenifergomez/aplicacion/instruccion.png");
            String[] opciones = {"Ver instrucciones","Jugar"};

            int respuesta = JOptionPane.showOptionDialog(null, "Bienvenidx al juego de la Guayabita!"
                    + "\n\n¿Qué quieres hacer?\n", "Guayabita", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, opciones, opciones[1]);

            if (respuesta == 0){
                JOptionPane.showMessageDialog(null,
                        "\n- El jugador debe iniciar el juego con una apuesta inicial de mínimo $500"+
                                "\n - El pote inicial será de la suma total de lo que apostó cada jugador"+
                                "\n - Si alguno de los jugadores no desea lanzar el dado, el juego termina para ese jugador"+
                                "\n - Si alguno de los jugadores al lanzar el dado saca 1 o 6, pierde la posibilidad de apostar y de allí cede el turno al siguiente jugador"+
                                "\n - Si alguno de los jugadores al lanzar el dado saca un númnero del 2 al 5 tiene la posibilidad de apostar por el pote que hay en juego"+
                                "\n - Si el jugador elige que no quiere apostar cede su turno, pero si quiere hacerlo, el juego le permite ingresar el monto por el que" +
                                "desea apostar y luego tirar nuevamente el dado"+
                                "\n - El jugador puede apostar por la totalidad del pote o por una parte, teniendo en cuenta que el valor que apueste debe ser igual o inferior a lo que cuenta"+
                                "\n - Si el jugador en su siguiente lanzada saca un número mayor que en la anterior, se lleva el dinero total del pote (o lo que apostó)"+
                                "\n - Si el jugador en su siguiente lanzada saca un número igual o menor que en la anterior, entonces tendrá que entregar lo que apostó"+
                                "\n - Cuando a un jugador se le acabe el dinero para apostar será eliminado"+
                                "\n - El juego termina cuando no quede dinero en el pote y éste, deberá mostrar la cantidad de dinero con la que quedó cada jugador","Instrucciones y reglas",JOptionPane.INFORMATION_MESSAGE,icon2);



            }else if (respuesta == 1){

                juego.jugarGuayabita();

            }

        }



    }


}
