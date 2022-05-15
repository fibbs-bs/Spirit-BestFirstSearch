package src;
public class Conexion {
    private Terreno actual;
    private Terreno siguiente;
    private String orientacion;
    private boolean obstaculo;

    public Conexion(Terreno actual,Terreno siguiente, String orientacion){
        this.actual = actual;
        this.siguiente = siguiente;
        this.orientacion = orientacion;
        this.obstaculo = false;
    }

    public void setObstaculo(boolean obstaculo){
        this.obstaculo = obstaculo;
    }

    public Terreno getActual() {
        return actual;
    }

    public Terreno getSiguiente() {
        return siguiente;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public boolean isObstaculo(){
        return obstaculo;
    }
}
