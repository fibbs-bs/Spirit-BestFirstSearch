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
            Terreno [][] superficie = 
            while(scan.hasNext()){
                String linea = scan.nextLine();
            }  
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

    }
    
}
