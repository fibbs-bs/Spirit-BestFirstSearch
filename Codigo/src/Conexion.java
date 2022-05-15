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

    public static String[] getOrientacion(int[] aCoords, int[] bCoords) {
        String [] retorno = new String[2];
        if (aCoords[0] == bCoords[0]){ //misma fila, por lo tanto puede ser Este u Oeste
            if (aCoords[1] < bCoords[1]){
                retorno[0] = "E";//orientación de conexión desde A
                retorno[1] = "O";//orientación de conexión desde B
            }
            else{
                retorno[0] = "O";//orientación de conexión desde A
                retorno[1] = "E";//orientación de conexión desde B
            }
        }
        else{ //misma columna
            if (aCoords[0] < bCoords[0]){
                retorno[0] = "N";//orientación de conexión desde A
                retorno[1] = "S";//orientación de conexión desde B
            }
            else{
                retorno[0] = "N";//orientación de conexión desde A
                retorno[1] = "S";//orientación de conexión desde B
            }
        }
        return null;
    }
}
