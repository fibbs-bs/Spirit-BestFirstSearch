package src;

import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Terrenos terrenos = new Terrenos();
        lectura(terrenos);
    }

    private static void lectura(Terrenos terrenos) {
        /**
         * Las conexiones no se pueden generar en la lectura ya que se lee desde fila 0 a fila n-1.
         * Esto genera que se pueda generar una conexión desde fila i a fila i-1 pero no de fila i-1 a i
         * ya que ya fue consultada por la lectura y rellenado de matriz. Es por esto que se rellenará
         * en otro método.
         * Las conexiones que si o si se generarán son las que poseen obstáculos.
         */
        try {
            Scanner scan = new Scanner(new File("Codigo/entradas/01.txt"));
            String[] medidas = scan.nextLine().split(",");
            int filas = Integer.parseInt(medidas[0]);
            int columnas = Integer.parseInt(medidas[1]);
            Terreno [][] superficie = new Terreno[filas][columnas];
            for (int i = 0; i < filas; i++) {
                int fila = Integer.parseInt(scan.nextLine());
                for (int j = 0; j < columnas; j++) {
                    String[] linea = scan.nextLine().split(",");
                    int columna = Integer.parseInt(linea[0]);
                    String tipo = linea[1];
                    Terreno t;
                    if (tipo.equals("L")){
                        t = new TerrenoLlano(fila, columna);
                    }
                    else{
                        t = new TerrenoAbrupto(fila, columna);
                    }
                    try {
                        String objetivo = linea[2];
                        t.setObjetivo();
                        superficie[fila][columna] = t;
                    } catch (Exception e) {
                        superficie[fila][columna] = t;
                    }
                    terrenos.add(t);
                }
            }
            while(scan.hasNextLine()){
                String linea = scan.nextLine();
                String coordenadasTerrenoA = linea.split(",")[1].replace("(","").replace(")","");
                String coordenadasTerrenoB = linea.split(",")[2].replace("(","").replace(")","");
                int[] aCoords = new int [] {Integer.parseInt(coordenadasTerrenoA.split(",")[0]),Integer.parseInt(coordenadasTerrenoA.split(",")[1])};
                int[] bCoords = new int [] {Integer.parseInt(coordenadasTerrenoB.split(",")[0]),Integer.parseInt(coordenadasTerrenoB.split(",")[1])};
                Terreno a = terrenos.find(aCoords);
                Terreno b = terrenos.find(bCoords);
                String[] orientaciones = Conexion.getOrientacion(aCoords,bCoords);
                a.getConexiones().add(new Conexion(a, b, orientaciones[0]));
                b.getConexiones().add(new Conexion(b, a, orientaciones[1]));
            } 
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }
    
}
