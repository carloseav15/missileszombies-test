/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package missileszombiespruebas;*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Carlos
 */
public class MissilesZombiesPruebas {
 
    public static void main(String[] args) throws IOException {
       
       cargarMatriz(matriz);
    
    }
    
    static Area [][] matriz = new Area[0][0];
    
    public static void cargarMatriz(Area[][] matriz) throws FileNotFoundException, IOException{
                
        String temp;
        ArrayList<String> datosTxt = new ArrayList<String>();
        ArrayList<String> tamañoMatriz = new ArrayList<String>();
        
        FileReader fReader = new FileReader("sample.txt");
        BufferedReader buffer = new BufferedReader(fReader);
       
        while((temp = buffer.readLine())!=null){
            datosTxt.add(temp);
        }
        buffer.close();
        
        if(datosCorrectos(datosTxt) == true){
            StringTokenizer st = new StringTokenizer(datosTxt.get(0)," ");
        
        while (st.hasMoreTokens())
        tamañoMatriz.add(st.nextToken());
        
            int width = Integer.parseInt(tamañoMatriz.get(0));
            int height = Integer.parseInt(tamañoMatriz.get(1));
            
            if (width == height) System.out.println("Para el area del ejercicio solo se admite una forma rectangular - Largo(Filas): " + height + " = Ancho(Columnas): " + width + " - No se permite una forma cuadrada.");
                
            else{
                matriz = new Area [height][width];
            
                Area aux;
        
                for(int y = 0; y < matriz.length; y++){
                    for (int x = 0; x < matriz[y].length; x++){
                        aux = new Area();
                        aux.setX(x+1);
                        aux.setY(height-y);
                        aux.setNum_Zombies(0);
                        matriz[y][x] = aux;
                    }
                }    

                for(int i = 1; i < datosTxt.size(); i++){

                    StringTokenizer st2 = new StringTokenizer(datosTxt.get(i)," ");
                    ArrayList<String> datosZombies = new ArrayList<String>();

                    while (st2.hasMoreTokens()) datosZombies.add(st2.nextToken());

                    int xTemp = Integer.parseInt(datosZombies.get(0));
                    int yTemp = Integer.parseInt(datosZombies.get(1));
                    int num_zombiesTemp = Integer.parseInt(datosZombies.get(2));

                    matriz[height-yTemp][xTemp-1].setNum_Zombies(num_zombiesTemp);
                    }
                
                hallarBombas(matriz);
            } 
        }else{
            System.out.println("Algun o Algunos de los datos de entrada no son correctos\nVerifique los datos de entrada por favor");
        }           
    }
    
    public static void hallarBombas(Area[][] matriz) throws FileNotFoundException, IOException{
        
        ArrayList<String> bombas = new ArrayList<String>();
        int zombiesEliminados = 0;
        int areasAfectadas = 0;
        int eliminadosTemp = 0;
        int areasTemp = 0;
        int xTemp = 0;
        int yTemp = 0;
        
        for(int v = 0; v <= 2; v++){
            
            for(int x=0; x < matriz.length; x++) {
                    
                for(int y=0; y < matriz[x].length; y++) {
                    
                    if(matriz[x][y].getNum_Zombies() > 0){
                        zombiesEliminados = zombiesEliminados + matriz[x][y].getNum_Zombies();
                        areasAfectadas++;
                    }

                    for(int z=1; z <= 2; z++){   
                        if(z == 1 && x-z >= 0 && y-z >= 0)
                            if(matriz[x-z][y-z].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x-z][y-z].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if(z == 1 && x-z >= 0 && y+z < matriz[x].length)
                            if(matriz[x-z][y+z].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x-z][y+z].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if (z == 1 && x+z < matriz.length && y-z >= 0)
                            if(matriz[x+z][y-z].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x+z][y-z].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if (z == 1 && x+z < matriz.length && y+z < matriz[x].length)
                            if(matriz[x+z][y+z].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x+z][y+z].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if (x-z >= 0)
                            if(matriz[x-z][y].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x-z][y].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if (x+z < matriz.length)
                            if(matriz[x+z][y].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x+z][y].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if (y-z >= 0)
                            if(matriz[x][y-z].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x][y-z].getNum_Zombies();
                                areasAfectadas++;
                            }

                        if (y+z < matriz[x].length)
                            if(matriz[x][y+z].getNum_Zombies() > 0){
                                zombiesEliminados = zombiesEliminados + matriz[x][y+z].getNum_Zombies();
                                areasAfectadas++;
                            }
                    }
                    
                      if(zombiesEliminados > eliminadosTemp && areasAfectadas >= areasTemp){
                        xTemp = x;
                        yTemp = y;
                        eliminadosTemp = zombiesEliminados;
                        } 
                     
                    zombiesEliminados = 0;
                    areasAfectadas = 0;
                }
                
            }
            if(eliminadosTemp == 0) System.out.println(0+" "+0+" "+0);
            else System.out.println(matriz[xTemp][yTemp].getX()+" "+matriz[xTemp][yTemp].getY()+" "+eliminadosTemp);
            
            limpiar_Areas(xTemp, yTemp, matriz);
            
            eliminadosTemp = 0;
            areasTemp = 0;
            xTemp = 0;
            yTemp = 0;
        }
        
    }
    
    public static void limpiar_Areas(int xTemp, int yTemp, Area[][] matriz){
            
            if(matriz[xTemp][yTemp].getNum_Zombies() != 0) matriz[xTemp][yTemp].setNum_Zombies(0);
            
            for(int z=1; z <= 2; z++){     
                        if(z == 1 && xTemp-z >= 0 && yTemp-z >= 0)
                            if(matriz[xTemp-z][yTemp-z].getNum_Zombies() > 0){
                                matriz[xTemp-z][yTemp-z].setNum_Zombies(0);
                            }

                        if(z == 1 && xTemp-z >= 0 && yTemp+z < matriz[xTemp].length)
                            if(matriz[xTemp-z][yTemp+z].getNum_Zombies() > 0){
                                matriz[xTemp-z][yTemp+z].setNum_Zombies(0);
                            }

                        if (z == 1 && xTemp+z < matriz.length && yTemp-z >= 0)
                            if(matriz[xTemp+z][yTemp-z].getNum_Zombies() > 0){
                                matriz[xTemp+z][yTemp-z].setNum_Zombies(0);
                            }

                        if (z == 1 && xTemp+z < matriz.length && yTemp+z < matriz[xTemp].length)
                            if(matriz[xTemp+z][yTemp+z].getNum_Zombies() > 0){
                                matriz[xTemp+z][yTemp+z].setNum_Zombies(0);
                            }

                        if (xTemp-z >= 0)
                            if(matriz[xTemp-z][yTemp].getNum_Zombies() > 0){
                                matriz[xTemp-z][yTemp].setNum_Zombies(0);
                            }

                        if (xTemp+z < matriz.length)
                            if(matriz[xTemp+z][yTemp].getNum_Zombies() > 0){
                                matriz[xTemp+z][yTemp].setNum_Zombies(0);
                            }

                        if (yTemp-z >= 0)
                            if(matriz[xTemp][yTemp-z].getNum_Zombies() > 0){
                                matriz[xTemp][yTemp-z].setNum_Zombies(0);
                            }

                        if (yTemp+z < matriz[xTemp].length)
                            if(matriz[xTemp][yTemp+z].getNum_Zombies() > 0){
                                matriz[xTemp][yTemp+z].setNum_Zombies(0);
                            }
                }
    }
    
    public static boolean datosCorrectos(ArrayList<String> datosTxt){
        
        boolean bandera = true;
        int width = 0;
        int height = 0;
        
        if(datosTxt.size() > 1){
            
            for(int i=0; i<datosTxt.size(); i++){
                
                ArrayList<String> temp = new ArrayList<String>();
                StringTokenizer st = new StringTokenizer(datosTxt.get(i)," ");
            
                while (st.hasMoreTokens()) temp.add(st.nextToken());
                
                for(int j=0; j<temp.size(); j++){
                    if(!esNumero(temp.get(j))) return false;
                }
                
                if(i==0){
                    width = Integer.parseInt(temp.get(0));
                    height = Integer.parseInt(temp.get(1));
                }
            
                if(i>0 && temp.size()<3) return false;
                
                    if(i>0 && temp.size()==3){
                        if(Integer.parseInt(temp.get(2)) <= 0) return false;
                    }
                    
                    if(i>0){
                        int varX = Integer.parseInt(temp.get(0));
                        int varY = Integer.parseInt(temp.get(1));
                        
                        if(varX > width || varY > height) return false;
                    }
            }
        }
        return true;
    }
    
    public static boolean esNumero(String temp){
    try {
        Integer.parseInt(temp);
        return true;
    } catch (NumberFormatException nfe){
        return false;
    }
    }
}

class Area {
    
    private int x;
        private int y;
        private int num_Zombies;

    public Area(int x, int y, int num_Zombies) {
        this.x = x;
        this.y = y;
        this.num_Zombies = num_Zombies;
    }

    public Area() {
    }

    public Area(int x, int y) {
        this.x = x;
        this.y = y;
    }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getNum_Zombies() {
            return num_Zombies;
        }

        public void setNum_Zombies(int num_Zombies) {
            this.num_Zombies = num_Zombies;
        }
}

    

