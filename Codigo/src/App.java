package src;

import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        lectura();
    }

    private static void lectura() {
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
                }
            } 
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }
    
}
