package src;

import java.util.*;

public class Spirit {

    private List<Movimiento> closed;
    private PriorityQueue<Movimiento> open;
    private double tiempo;
    private double tiempoRecorrido;
    private String orientacion;
    private Terrenos superficie;
    private int movimientos;
    private int giros;

    public Spirit(Terrenos s){
        this.giros = 0;
        this.movimientos = 0;
        this.closed = new ArrayList<>();
        this.open = new PriorityQueue<>();
        this.superficie = s;
        Random rand = new Random();
        this.orientacion = (new String[]{"N","S","E","O"})[rand.nextInt(4)];
    }

    public void bestFistSearch() throws Exception{
        Movimiento n; //inicial
        long startTime = System.nanoTime();
        {
            n = new Movimiento(null, superficie.getTerrenoInicio(), this.orientacion);
            //System.out.println("Inicial: "+n.getTerreno().toString()+" | dirección "+this.orientacion);
            open.add(n);
            while(!n.getTerreno().getObjetivo()){
                if (open.isEmpty()){
                    throw new Exception("Sin solución");
                }
                else{
                    closed.add(n);
                    Terreno nodoActual = n.getTerreno();
                    for (Terreno hijo : nodoActual.getTerrenos().getTerrenosArray()) {
                        //El nodo N que se escoja no puede haber estado antes en la lista closed
                        Movimiento nuevo = new Movimiento(n, hijo, n.getOrientacion());
                        if (!nodoActual.getObstaculos().exists(hijo)){
                            boolean exists = false;
                            for (Movimiento movimiento : closed) {
                                exists = movimiento.equals(nuevo);
                                if (exists) break;
                            }
                            if (!exists) open.add(nuevo);
                        }

                    }
                }
                n = open.remove();
                this.movimientos++;
                this.tiempoRecorrido+=n.getH();
                this.giros += n.getGiros();
                //System.out.println("Se movió a "+n.getTerreno().toString()+" | dirección "+n.getOrientacion()+" | h(n)="+n.getH());
            }
            //System.out.println("Objetivo encontrado "+n.getTerreno().toString());
        }
        long endTime = System.nanoTime();
        this.tiempo = (endTime - startTime)/1000000.0;  //dividir por 1000000 para obtener milisegundos.
        n.getPath();
    }

    public List<Movimiento> getClosed(){
        return closed;
    }

    public int getGiros(){
        return this.giros;
    }

    public int getMovimientos(){
        return this.movimientos;
    }

    public double getTiempoRecorrido(){
        return tiempoRecorrido;
    }

    public double getTiempo(){
        return this.tiempo;
    }
}
