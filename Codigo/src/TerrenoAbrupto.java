package src;

import javax.swing.JButton;
import javax.swing.border.MatteBorder;

import java.awt.*;

public class TerrenoAbrupto extends Terreno{

    private double velocidad = 0.5;

    public TerrenoAbrupto(int fila, int columna) {
        super(fila, columna);
        this.grafico = new JButton(""){{
            setBackground(new Color(185,57,52));
            setBorder(new MatteBorder(0,0,0,0,Color.WHITE));
        }};
    }
    
    public double getTiempo() {
        return 0.5/velocidad;
    }


}
