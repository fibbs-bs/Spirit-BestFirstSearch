package src;

import java.util.ArrayList;

public class Terrenos {
    private ArrayList<Terreno> terrenos;

    public Terrenos() {
        this.terrenos = new ArrayList<>();
    }
    
    public void add(Terreno t){
        terrenos.add(t);
    }

    public Terreno find(int[] coord){
        for (Terreno t: terrenos) {
            if (t.getFila()==coord[0] & t.getColumna()==coord[1]){
                return t;
            }
        }
        return null;
    }
}
