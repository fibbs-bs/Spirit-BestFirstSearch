package src;

public class Terreno{

    private int fila;
    private int columna;
    private boolean objetivo;
    private Terrenos terrenos;
    private Terrenos obstaculos;

    public Terreno(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.objetivo = false;
        terrenos = new Terrenos();
        obstaculos = new Terrenos();
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

    public Terrenos getTerrenos() {
        return terrenos;
    }

    public Terrenos getObstaculos(){
        return obstaculos;
    }
}