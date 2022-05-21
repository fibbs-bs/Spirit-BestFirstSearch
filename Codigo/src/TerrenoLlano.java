package src;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class TerrenoLlano extends Terreno{

    private double velocidad = 1.2;

    public TerrenoLlano(int fila, int columna) {
        super(fila, columna);
    }

    public double getTiempo() {
        return 0.5/velocidad;
    }

    @Override
    public String getInfo() {
        if (this.getObjetivo()){
            return "Llano ("+this.getFila()+","+this.getColumna()+") OBJETIVO";
        }
        else{
            return "Llano ("+this.getFila()+","+this.getColumna()+")";
        }
    }

}
