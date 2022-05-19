package src;

import java.util.*;

/**
 * Esta clase se crea porque los terrenos no bastan para representar el movimiento de spirit ya que posee una orientación.
 * Además, puede moverse al mismo terreno pero desde distintas orientaciones, lo que Terreno por si solo no puede representar.
 */
public class Movimiento {
    
    private Terreno actual;
    private Terreno anterior;

    private String orientacion;

    private double h;//heuristica

    public Movimiento(Terreno a, Terreno b, String orientacionSpirit){
        this.actual = a;
        this.anterior = b;
        this.orientacion = a.getOrientacionRelativa(b)[0];
        /**
         * Calculo de cantidad de giros
         */
        {
            ArrayList<String> orientacion = new ArrayList<>(Arrays.asList(new String[]{"N,E,S,O"}));
            int [] angulo = new int[]{0,90,180,270};
            int posactual = angulo[orientacion.indexOf(orientacionSpirit)];
            int posmover = angulo[orientacion.indexOf(this.orientacion)];
            int giros = Math.abs(posmover-posactual)/90;

            this.h = (a.getTiempo()+b.getTiempo())+(4*giros);
        }
    }

    public Terreno getActual(){
        return this.actual;
    }

    public Terreno getAnterior(){
        return this.anterior;
    }

    public double getH(){
        return this.h;
    }
}
