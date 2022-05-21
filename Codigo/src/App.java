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
    public static void main(String[] args) throws Exception{
        generadorNxN();
    }



    private static void generadorNxN() {
        int n = 100;
        int cont = 0;
        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                try {
                    Terrenos superficie = new Terrenos(i,i);
                    actualizarCSV(superficie,best_first_search(superficie));
                    System.out.println((int)(((cont)/(double)((n-2)*10))*100)+"%");
                    //tomarCaptura(superficie);        
                    cont++;
                } catch (Exception e) {
                    continue;
                }
            }
        }
        
    }



    private static void actualizarCSV(Terrenos superficie, Spirit s) throws IOException {
        Scanner scan;
        String salida = "N,M,Area,Ejecucion(ms),Busqueda(h(t)),Movimientos,Giros\n";
        try {
            int i = 0;
            scan = new Scanner(new File("Codigo/salidas/Datos/reporte.csv"));
            while(scan.hasNextLine()){
                String linea = scan.nextLine();
                String [] partes = linea.split(",");
                if (i>0 && !partes[0].equals("N")){
                    salida += linea+"\n";
                }
                i++;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        salida += superficie.getN()+","+superficie.getM()+","+(superficie.getN()*superficie.getM())+","+s.getTiempo()+","+s.getTiempoRecorrido()+","+s.getMovimientos()+","+s.getGiros();
        FileWriter file = new FileWriter("Codigo/salidas/Datos/reporte.csv");
        PrintWriter escritura = new PrintWriter(file);
        escritura.println(salida);
        file.close();
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

    public static Spirit best_first_search(Terrenos superficie) throws Exception{
        Spirit robot = new Spirit(superficie);
        robot.bestFistSearch();
        return robot;
    }



}
