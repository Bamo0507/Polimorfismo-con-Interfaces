//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 29/10/2023
//Última modificación: 29/10/2023

//Librerías a utilizar
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class ManejoCSV {
    
    public ArrayList<Paquete> leerCSV(){
        ArrayList<Paquete> paquetes = new ArrayList<>();
        //Intentamos leer el archivo
        try(BufferedReader br = new BufferedReader(new FileReader("DatosUsuarios.csv"))){
            br.readLine();
            String linea;
            //Comenzamos a leer todas las líneas del CSV
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(",");
                //Recopilar información
                String tipo = datos[0].trim();
                String nombre = datos[1].trim();
                String contrasena = datos[2].trim();
                String fechaIda = datos[3].trim();
                int fechaVuelta = Integer.parseInt(datos[4].trim());
                String Vuelta = datos[5].trim();
                int cantBoletos = Integer.parseInt(datos[6].trim());
                String Aerolinea = datos[7].trim();
                int numTarjeta = Integer.parseInt(datos[8].trim());
                String Clase = datos[9].trim();
                String numAsiento = datos[10].trim();
                int cantMaletas = Integer.parseInt(datos[11].trim());
                int cantPagos = Integer.parseInt(datos[12].trim());
                String cupones = datos[13].trim();

                if(tipo.equals("Premium")){
                    Premium premium = new Premium(tipo, nombre, contrasena);
                    premium.setDatosVuelo(fechaIda, fechaVuelta, cantBoletos, Aerolinea, numTarjeta, Clase, numAsiento, cantMaletas, cantPagos, cupones);
                    paquetes.add(premium);
                } else{
                    Base base = new Base(tipo, nombre, contrasena);
                    base.setDatosVuelo(fechaIda, fechaVuelta, cantBoletos, Aerolinea, numTarjeta, Clase, numAsiento, cantMaletas, cantPagos, cupones);
                    paquetes.add(base);
                }
            }
        } catch (IOException e) {
            System.err.println("No se ha encontraodo el archivo que proporcionaste :(");
            e.printStackTrace();
        }
        return paquetes;
    }

    public static void agregarDatosVuelo(){
        
    }

    public static void agregarValoresInt(String nombre, int indice, int valor){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("DatosUsuarios.csv"));
            String linea; 
            StringBuilder sb = new StringBuilder();
            while((linea = reader.readLine()) != null){
                String[] datos = linea.split(",");
                if(datos.length > 0 && datos[1].equals(nombre)){
                    datos[indice] = String.valueOf(valor);
                }
                sb.append(String.join(",", datos)).append("\n");
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("DatosUsuarios.csv"));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void agregarValoresStr(String nombre, int indice, String valor){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("DatosUsuarios.csv"));
            String linea; 
            StringBuilder sb = new StringBuilder();
            while((linea = reader.readLine()) != null){
                String[] datos = linea.split(",");
                if(datos.length > 0 && datos[1].equals(nombre)){
                    datos[indice] = valor;
                }
                sb.append(String.join(",", datos)).append("\n");
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter("DatosUsuarios.csv"));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarCliente(String tipo, String nombre, String contrasena, String fechaIda, int fechaVuelta, String Aerolinea, int numTarjeta, String clase, String numAsiento, int cantMaletas, int cantPagos, String cupones){
        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append(",").append(nombre).append(",").append(contrasena).append(",").append(fechaIda).append(",").append(fechaVuelta).append(",").append(Aerolinea).append(",").append(numTarjeta).append(",").append(clase).append(",").append(numAsiento).append(",").append(cantMaletas).append(",").append(cantPagos).append(",").append(cupones);
        //Tratamos de escribir sobre el archivo
        try(BufferedWriter w = new BufferedWriter(new FileWriter("DatosUsuarios.csv", true))){
            w.write(sb.toString());
            w.newLine();
        } catch(IOException e) { //Mensaje a mostrar si no logramos escribir sobre el CSV
            System.out.println("No se ha logrado agregar el jugador.");
            e.printStackTrace();
        }
    }


        
}
