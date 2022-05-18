package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.*;

public class App {
    public static void main(String[] args) throws IOException{
        FileWriter file = new FileWriter("out.txt");
        PrintWriter escritura = new PrintWriter(file);
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese dimensión n: ");
        int n = Integer.parseInt(scan.nextLine());
        System.out.print("Ingrese dimensión m: ");
        int m = Integer.parseInt(scan.nextLine());
        Terrenos superficie = new Terrenos(n,m);
        crearArchivo(superficie);
        tomarCaptura(superficie);
    }


    public static void tomarCaptura(Terrenos terreno){                                        
        JFrame panel = terreno.getFrame();
        /**
         * this gist outline the process to grab the screenshot of a particular
         * JFrame in Swing from which the method is invoked
         * 
         * "this" is the particualr frame here
         * */
        BufferedImage screenshotImage = new BufferedImage(
                panel.getBounds().width, panel.getBounds().height,
                BufferedImage.TYPE_INT_RGB);
                panel.paint(screenshotImage.getGraphics()
        );
        try {
            DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yy_HHmm");
            String nowFormat = dtf2.format(LocalDateTime.now());
            ImageIO.write(screenshotImage, "png", new File("Codigo/salidas/Imagenes/"+terreno.getN()+"x"+terreno.getM()+"_"+nowFormat+".png" ));
        } catch (IOException ex) {
            System.err.println("ImageIssues");
        }
    }

    public static void crearArchivo(Terrenos terreno) throws IOException{
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd-MM-yy_HHmm");
        String nowFormat = dtf2.format(LocalDateTime.now());
        FileWriter file = new FileWriter("Codigo/salidas/Texto/"+terreno.getN()+"x"+terreno.getM()+"_"+nowFormat+".txt");
        PrintWriter escritura = new PrintWriter(file);
        escritura.println(terreno.toString());
        file.close();
    }
}
