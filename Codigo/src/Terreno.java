package src;

import javax.swing.JButton;

public abstract class Terreno{

    private int fila;
    private int columna;
    private boolean objetivo;
    private Terrenos terrenos;
    private Terrenos obstaculos;
    protected JButton grafico;

    public Terreno(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.objetivo = false;
        terrenos = new Terrenos();
        obstaculos = new Terrenos();
    }

    public JButton getGrafico(){
        return grafico;
    }

    public abstract void pintar();

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
    /**
     * @return retorna la orientación del otro objeto con respecto de este
     */
    public String[] getOrientacionRelativa(Terreno b){
        String [] retorno = new String[2];
        if (this.fila == b.getFila()){ //misma fila, por lo tanto puede ser Este u Oeste
            if (this.columna < b.getColumna()){
                retorno[0] = "E";//orientación de conexión desde A
                retorno[1] = "O";//orientación de conexión desde B
            }
            else{
                retorno[0] = "O";//orientación de conexión desde A
                retorno[1] = "E";//orientación de conexión desde B
            }
        }
        else{ //misma columna
            if (this.fila < b.getFila()){
                retorno[0] = "S";//orientación de conexión desde A
                retorno[1] = "N";//orientación de conexión desde B
            }
            else{
                retorno[0] = "N";//orientación de conexión desde A
                retorno[1] = "S";//orientación de conexión desde B
            }
        }
        return retorno;
    }
}