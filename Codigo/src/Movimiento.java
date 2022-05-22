package src;

import java.awt.*;
import java.util.*;

import javax.swing.JButton;

/**
 * Esta clase se crea porque los terrenos no bastan para representar el movimiento de spirit ya que posee una orientación.
 * Además, puede moverse al mismo terreno pero desde distintas orientaciones, lo que Terreno por si solo no puede representar.
 */
public class Movimiento implements Comparable<Movimiento>{
    
    private Terreno terreno; //Terreno al que el movimiento es realizado
    private Movimiento movimientoAnterior;
    private long timestamp;
    private String orientacion;
    private double h;//heuristica
    private int giros;

    public Movimiento(Movimiento anterior, Terreno actual, String orientacionSpirit){
        this.timestamp = System.nanoTime();
        this.terreno = actual;
        this.movimientoAnterior = anterior;
        if (movimientoAnterior==null){
            this.orientacion = orientacionSpirit;
            this.h = 0;
        }
        else{
            Terreno terrenoAnterior = movimientoAnterior.getTerreno();
            this.orientacion = terrenoAnterior.getOrientacionRelativa(actual)[0];
            /**
             * Calculo de cantidad de giros
             */
            {
                ArrayList<String> orientacion = new ArrayList<>(Arrays.asList(new String[]{"N","E","S","O"}));
                int [] angulo = new int[]{0,90,180,270};
                int posactual = angulo[orientacion.indexOf(orientacionSpirit)];
                int posmover = angulo[orientacion.indexOf(this.orientacion)];
                this.giros = Math.abs(posmover-posactual)/90;
    
                this.h = (terrenoAnterior.getTiempo()+terreno.getTiempo())+(4*giros);
            }
        }
    }
    
    public boolean equals(Movimiento m){
        if (/*this.orientacion.equals(m.getOrientacion()) &&*/ this.terreno.equals(m.getTerreno())){
            return true;
        }
        return false;
    }

    public int getGiros(){
        return this.giros;
    }

    @Override
    public int compareTo(Movimiento m) {
        if (this.h < m.getH()){
            return -1;
        }
        else if (this.h > m.getH()){
            return 1;
        }
        else{
            /**
             * En el caso de que sean iguales las heurísticas, se debe seleccionar al que se haya ingresado antes.
             */
            return (int)(this.timestamp - m.getTimestamp());
        }
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public Movimiento getMovimientoAnterior() {
        return movimientoAnterior;
    }

    public void setMovimientoAnterior(Movimiento movimientoAnterior) {
        this.movimientoAnterior = movimientoAnterior;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void getPath(){
        System.out.println(terreno.toString()+" | "+this.orientacion);
        if (!this.terreno.getObjetivo() && !this.terreno.getInicio()){
            this.terreno.getGrafico().setForeground(Color.BLUE);
            this.terreno.getGrafico().setText("\u2B1B");
        }
        
        if (this.movimientoAnterior!=null){
            this.movimientoAnterior.getPath();
        }
    }    

    public void closedIn(){
        if (!this.terreno.getObjetivo() && !this.terreno.getInicio()){
            this.terreno.getGrafico().setForeground(Color.BLACK);
            this.terreno.getGrafico().setText("\u2B1B");
        }
    }

    public void openIn(){
        if (!this.terreno.getObjetivo() && !this.terreno.getInicio()){
            this.terreno.getGrafico().setForeground(Color.WHITE);
            this.terreno.getGrafico().setText("\u2B1B");
        }
    }

}
