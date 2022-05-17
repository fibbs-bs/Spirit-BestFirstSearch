package src;

import java.util.ArrayList;

public class Terrenos {
    private ArrayList<Terreno> terrenos;
    int n;
    int m;

    public Terrenos() {
        this.terrenos = new ArrayList<>();
    }

    public Terrenos(int n, int m){
        this.n = n;
        this.m = m;
        this.terrenos = new ArrayList<>();
        //Se genera el terreno
        //Lógica de creación random de terrenos abruptos y llanos con obstáculos y esas cosas.
    }
    
    public ArrayList<Terreno> getTerrenos(){
        return terrenos;
    }

    public void add(Terreno t){
        if (!this.exists(t.getFila(), t.getColumna())){
            terrenos.add(t);
        }
    }

    public int getN(){
        return n;
    }

    public int getM(){
        return m;
    }

    public Terreno find(int i,int j){
        for (Terreno t: terrenos) {
            if (t.getFila()==i & t.getColumna()==j){
                return t;
            }
        }
        return null;
    }

    public boolean exists(int i, int j){
        for (Terreno t: terrenos) {
            if (t.getFila()==i & t.getColumna()==j){
                return true;
            }
        }
        return false;
    }
    /**
     * Comprueba si las coordenadas i y j están dentro del terreno
     * @param i coordenada I
     * @param j coordenada J
     * @return true or false
     */
    public boolean checkTerreno(int i, int j){
        if ((i>=0 && i<n) && (j>=0 && j<m)){
            return true;
        }
        return false;
    }
}
