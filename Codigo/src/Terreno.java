package src;
import java.util.ArrayList;

public class Terreno{

    private int fila;
    private int columna;
    private boolean objetivo;
    private ArrayList<Conexion> conexiones;

    public Terreno(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.objetivo = false;
        conexiones = new ArrayList<Conexion>();
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public boolean isObjetivo() {
        return objetivo;
    }

    public void setObjetivo(){
        this.objetivo = true;
    }

    public ArrayList<Conexion> getConexiones() {
        return conexiones;
    }
}