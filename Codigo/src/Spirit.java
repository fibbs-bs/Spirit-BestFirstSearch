package src;

import java.util.*;

public class Spirit {

    private List<Movimiento> closed;
    private PriorityQueue<Movimiento> open;
    private long tiempo;
    private double distancia;
    private String orientacion;
    private Terrenos superficie;

    public Spirit(Terrenos s){
        closed = new ArrayList<>();
        open = new PriorityQueue<>();
        this.superficie = s;
        Random rand = new Random();
        this.orientacion = (new String[]{"N","S","E","O"})[rand.nextInt(4)];
    }

    public void bestFistSearch() throws Exception{
        long startTime = System.nanoTime();
        {
            Movimiento n = new Movimiento(null, superficie.getTerrenoInicio(), this.orientacion);
            open.add(n);
            while(!n.getTerreno().getObjetivo()){
                if (open.isEmpty()){
                    throw new Exception("Sin solución");
                }
                closed.add(n);
                System.out.println(n.getTerreno().toString()+" | "+n.getOrientacion());
                if (n.getTerreno().getObjetivo()){
                    /**
                     * Se puede hacer backtracking desde el último movimiento agregado a closed o desde el mismo n
                     */
                    closed.add(n);
                    n.getPath();
                    return;
                }
                else{
                    Terreno nodoActual = n.getTerreno();
                    for (Terreno hijo : nodoActual.getTerrenos().getTerrenosArray()) {
                        if (!nodoActual.getObstaculos().exists(hijo)){
                            Movimiento nuevo = new Movimiento(n, hijo, n.getOrientacion());
                            open.add(nuevo);
                        }
                    }
                }
                n = open.remove();
            }
        }
        long endTime = System.nanoTime();
        this.tiempo = (endTime - startTime);  //divide by 1000000 to get milliseconds.
    }

    public List<Movimiento> getClosed(){
        return closed;
    }

    public long getTiempo(){
        return this.tiempo;
    }
}
