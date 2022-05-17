package src;
public class TerrenoLlano extends Terreno{

    private double velocidad = 1.2;

    public TerrenoLlano(int fila, int columna) {
        super(fila, columna);
    }

    public double getTiempo() {
        return 0.5/velocidad;
    }

}
