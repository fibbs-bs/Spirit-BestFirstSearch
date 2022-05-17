package src;
import javax.swing.JButton;
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
    public void pintar() {
        this.grafico = new JButton(""){{
            setBackground(new Color(185,57,52));
        }};
    }
}
