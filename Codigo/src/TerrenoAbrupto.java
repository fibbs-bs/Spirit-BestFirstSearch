package src;
public class TerrenoAbrupto extends Terreno{

    private double velocidad = 0.5;

    public TerrenoAbrupto(int fila, int columna) {
        super(fila, columna);
    }
    
    public double getVelocidad() {
        return velocidad;
    }
}
