package src;

import javax.swing.JButton;
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
    public void pintar() {
        this.grafico = new JButton(""){{
            setBackground(new Color(249,202,162));
        }};
    }

}
