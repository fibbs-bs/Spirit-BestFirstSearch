package src;

import java.util.*;

public class Spirit {

    private List<Movimiento> closed;
    private PriorityQueue<Movimiento> open;
    private long tiempo;
    private double tiempoRecorrido;
    private String orientacion;
    private Terrenos superficie;
    private int movimientos;

    public Spirit(Terrenos s){
        movimientos = 0;
        closed = new ArrayList<>();
        open = new PriorityQueue<>();
        this.superficie = s;
        Random rand = new Random();
        this.orientacion = (new String[]{"N","S","E","O"})[rand.nextInt(4)];
    }

    public void bestFistSearch() throws Exception{
        Movimiento n; //inicial
        long startTime = System.nanoTime();
        {
            n = new Movimiento(null, superficie.getTerrenoInicio(), this.orientacion);
            System.out.println("Inicial: "+n.getTerreno().toString()+" | dirección "+this.orientacion);
            open.add(n);
            while(!n.getTerreno().getObjetivo()){
                if (open.isEmpty()){
                    throw new Exception("Sin solución");
                }
                else{
                    closed.add(n);
                    Terreno nodoActual = n.getTerreno();
                    for (Terreno hijo : nodoActual.getTerrenos().getTerrenosArray()) {
                        if (!nodoActual.getObstaculos().exists(hijo)){
                            Movimiento nuevo = new Movimiento(n, hijo, n.getOrientacion());
                            open.add(nuevo);
                        }
                    }
                }
                n = open.remove();
                this.movimientos++;
                this.tiempoRecorrido+=n.getH();
                System.out.println("Se movió a "+n.getTerreno().toString()+" | dirección "+n.getOrientacion()+" | h(n)="+n.getH());
            }
            System.out.println("Objetivo encontrado "+n.getTerreno().toString());
        }
        long endTime = System.nanoTime();
        this.tiempo = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
        n.getPath();
    }

    public List<Movimiento> getClosed(){
        return closed;
    }

    public int getMovimientos(){
        return this.movimientos;
    }

    public double tiempoRecorrido(){
        return tiempoRecorrido;
    }

    public long getTiempo(){
        return this.tiempo;
    }
}
