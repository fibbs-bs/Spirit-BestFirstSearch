package src;

public class Spirit {
    private Terrenos closed;
    private Terrenos open;
    private int i;
    private int j;
    private String orientacion;
    private Terreno terrenoInicial;

    public Spirit(Terreno t){
        this.terrenoInicial = t;
        this.i = t.getFila();
        this.j = t.getColumna();
        this.closed = new Terrenos();
        this.open = new Terrenos();
        String [] orientaciones = new String[]{"N","O","S","E"};
        this.orientacion = orientaciones[(int) (Math.random() * 3)];
    }

    public Terrenos getClosed(){
        return closed;
    }

    public Terrenos getOpen(){
        return open;
    }
}
