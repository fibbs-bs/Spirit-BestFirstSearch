package src;

import java.util.Scanner;

public class App {
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        System.out.print("Ingrese dimensión n: ");
        int n = Integer.parseInt(scan.nextLine());
        System.out.print("Ingrese dimensión m: ");
        int m = Integer.parseInt(scan.nextLine());
        System.out.println("Dimensión N:"+n+", Dimensión M:"+m);
        Terrenos superficie = new Terrenos(n,m);
        int[] movimientoI = new int[]{0,1,0,1};
        int[] movimientoJ = new int[]{1,0,-1,0};
        for (int i = 0; i < n; i++) {//filas
            for (int j = 0; j < m; j++) {//columnas
                Terreno terrenoIJ;
                if (!superficie.exists(i, j)){
                    //Si el terreno de coordenadas i,j no existe, se crea (llano o abrupto en un ratio de 0.7 para llano).
                    double rand = Math.random();
                    if (rand<=0.7){
                        //Terreno llano
                        terrenoIJ = new TerrenoLlano(i, j);
                        superficie.add(terrenoIJ);
                    }
                    else{
                        //Terreno abrupto
                        terrenoIJ = new TerrenoAbrupto(i, j);
                        superficie.add(terrenoIJ);
                    }
                }
                else{
                    //Si el terreno de coordenadas i,j ya existe, solo se obtiene desde la superficie.
                    terrenoIJ = superficie.find(i, j);
                }
                /**
                 * Al terreno recién creado (u obtenido desde la superficie) se le asignan máximo 4 hijos.
                 * Estos hijos son los nodos los cuales serán las opciones a abrir.
                 * Si el nodo hijo ya fue creado, no es necesario volver a crearlo.
                 * Se emplea la misma lógica anterior 
                 */
                for (int k = 0; k < movimientoI.length; k++) {
                    //Los cuatro hijos están definidos según una serie de coordenadas posibles.
                    int coordIHijo = i+movimientoI[k];
                    int coordJHijo = j+movimientoJ[k];
                    //Se debe checkear que el terreno hijo a crear o a obtener pueda existir dentro de la superficie.
                    if (superficie.checkTerreno(coordIHijo, coordJHijo)){
                        Terreno terrenoHijo;
                        //Se debe checkear que el terreno ya exista o no dentro de la superficie.
                        if (!superficie.exists(coordIHijo, coordJHijo)){
                            double randHijo = Math.random();
                            if (randHijo<=0.7){
                                terrenoHijo = new TerrenoLlano(coordIHijo, coordJHijo);
                            }
                            else{
                                terrenoHijo = new TerrenoAbrupto(coordIHijo, coordJHijo);
                            }
                        }
                        else{
                            terrenoHijo = superficie.find(i, j);
                        }
                        terrenoIJ.getTerrenos().add(terrenoHijo);
                        superficie.add(terrenoHijo);
                    }
                    else{
                        //Aquí no va nada ya que las coordenadas hijo se salen de la superficie.
                    }
                }
            }
        }
    }
    
}
