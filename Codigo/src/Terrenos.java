package src;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.JFrame;

public class Terrenos {

    private JFrame frame;
    private ArrayList<Terreno> terrenos;
    private int n;
    private int m;
    private int[] movimientoI = new int[]{0,1,0,-1};
    private int[] movimientoJ = new int[]{1,0,-1,0};

    public Terrenos() {
        this.terrenos = new ArrayList<>();
    }

    public Terrenos(int n, int m){
        this.n = n;
        this.m = m;
        this.terrenos = new ArrayList<>();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new GridLayout(n,m,1,1));
        for (int i = 0; i < n; i++) {//filas
            for (int j = 0; j < m; j++) {//columnas
                Terreno terrenoIJ;
                if (!this.exists(i, j)){
                    //Si el terreno de coordenadas i,j no existe, se crea (llano o abrupto en un ratio de 0.7 para llano).
                    double rand = Math.random();
                    if (rand<=0.7){
                        //Terreno llano
                        terrenoIJ = new TerrenoLlano(i, j);
                        this.add(terrenoIJ);
                    }
                    else{
                        //Terreno abrupto
                        terrenoIJ = new TerrenoAbrupto(i, j);
                        this.add(terrenoIJ);
                    }
                }
                else{
                    //Si el terreno de coordenadas i,j ya existe, solo se obtiene desde la superficie.
                    terrenoIJ = this.find(i, j);
                }
                frame.add(terrenoIJ.getGrafico());
                /**
                 * Al terreno recién creado (u obtenido desde la superficie) se le asignan máximo 4 hijos.
                 * Estos hijos son los nodos los cuales serán las opciones a abrir.
                 * Si el nodo hijo ya fue creado, no es necesario volver a crearlo.
                 * Se emplea la misma lógica anterior 
                 */
                for (int k = 0; k < movimientoI.length; k++) {
                    //Los cuatro hijos están definidos según una serie de coordenadas posibles.
                    int coordIHijo = i+movimientoI[k];
                    int coordJHijo = j+movimientoJ[k];
                    //Se debe checkear que el terreno hijo a crear o a obtener pueda existir dentro de la superficie.
                    if (this.checkTerreno(coordIHijo, coordJHijo)){

                        Terreno terrenoHijo;
                        //Se debe checkear que el terreno ya exista o no dentro de la superficie.
                        if (!this.exists(coordIHijo, coordJHijo)){
                            double randHijo = Math.random();
                            if (randHijo<=0.7){
                                terrenoHijo = new TerrenoLlano(coordIHijo, coordJHijo);
                            }
                            else{
                                terrenoHijo = new TerrenoAbrupto(coordIHijo, coordJHijo);
                            }
                            
                            this.add(terrenoHijo);
                        }
                        else{
                            terrenoHijo = this.find(coordIHijo, coordJHijo);
                        }
                        
                        if (Math.random()<=0.1){
                            if(!terrenoIJ.getObstaculos().exists(coordIHijo, coordJHijo)){
                                terrenoIJ.getObstaculos().add(terrenoHijo);
                                //Código de bordes
                                terrenoIJ.pintarObstaculo();
                            }
                            terrenoHijo.getObstaculos().add(terrenoIJ);
                            terrenoHijo.pintarObstaculo();
                        }

                        terrenoIJ.getTerrenos().add(terrenoHijo);
                    }
                    else{
                        //Aquí no va nada ya que las coordenadas hijo se salen de la superficie.
                    }
                }

            }
            
            
        }
        /**
         * Creación de objetivo
         * Para que sea coherente, el código solo posiciona al objetivo en el cuartel inferior derecho
         */
        int filaObjetivo = (int)(n/2) + (int)(Math.random() * ((n - (int)(n/2))));
        int columnaObjetivo = (int)(m/2) + (int)(Math.random() * ((m - (int)(m/2))));
        this.find(filaObjetivo, columnaObjetivo).setObjetivo();
        //
        frame.setVisible(true);
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

    public JFrame getFrame(){
        return frame;
    }
}

