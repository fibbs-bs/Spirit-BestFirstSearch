package src;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class TerrenoAbrupto extends Terreno{

    private double velocidad = 0.5;

    public TerrenoAbrupto(int fila, int columna) {
        super(fila, columna);
        
    }
    
    public double getTiempo() {
        return 0.5/velocidad;
    }

    @Override
    public String getInfo() {
        if (this.getObjetivo()){
            return "Abrupto ("+this.getFila()+","+this.getColumna()+") OBJETIVO";
        }
        else{
            return "Abrupto ("+this.getFila()+","+this.getColumna()+")";
        }
    }


}
