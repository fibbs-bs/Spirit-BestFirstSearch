package src;

import javax.swing.JButton;
import java.awt.*;
import javax.swing.border.MatteBorder;

public abstract class Terreno{

    private int fila;
    private int columna;
    private boolean objetivo;
    private boolean inicio;
    private Terrenos terrenos;
    protected Terrenos obstaculos;
    

    public Terreno(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.objetivo = false;
        this.inicio = false;
        terrenos = new Terrenos();
        obstaculos = new Terrenos();
    }

    public boolean getInicio(){
        return inicio;
    }

    public void setInicio(){
        this.inicio = true;
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

    public boolean getObjetivo(){
        return objetivo;
    }

    public Terrenos getTerrenos() {
        return terrenos;
    }

    public abstract String getInfo();

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

    public void pintarObstaculo() {
        //MatteBorder(int top, int left, int bottom, int right, Color matteColor)
        int [] bordes = new int[4];
        for (Terreno t : this.obstaculos.getTerrenosArray()) {
            String orientacion = this.getOrientacionRelativa(t)[0];    
            if (orientacion.equals("N")){
                bordes[0]=2;
            }
            else{
                if (orientacion.equals("S")){
                    bordes[2]=2;
                }
                else{
                    if (orientacion.equals("E")){
                        bordes[3]=2;
                    }
                    else{
                        bordes[1]=2;
                    }
                }
            }
        }
    }

    @Override
    public String toString(){
        return "("+fila+","+columna+")";
    }

    public abstract double getTiempo();
}