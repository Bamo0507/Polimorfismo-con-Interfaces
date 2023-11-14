//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 10/10/2023
//Última modificación: 13/10/2023

//Librerías a utilizar
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ManejoCSV {
    Premium premium;
    Base base;

    public ArrayList<Paquete> leerCSV() {
        ArrayList<Paquete> paquetes = new ArrayList<>();

        // Intentamos leer el archivo
        try {
            List<String> lineas = Files.readAllLines(Paths.get("DatosUsuarios.csv"));

            // Ignorar encabezados
            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);

                String[] datos = linea.split(",");

                // Resto del código para procesar la línea y crear instancias
                String tipo = datos[0].trim();
                String nombre = datos[1].trim();
                String contrasena = datos[2].trim();
                String fechaIda = datos[3].trim();
                int fechaVuelta = Integer.parseInt(datos[4].trim());
                int cantBoletos = Integer.parseInt(datos[5].trim());
                String Aerolinea = datos[6].trim();
                long numTarjeta = Long.parseLong(datos[7].trim());
                String Clase = datos[8].trim();
                String numAsiento = datos[9].trim();
                int cantMaletas = Integer.parseInt(datos[10].trim());
                int cantPagos = Integer.parseInt(datos[11].trim());
                String cupones = datos[12].trim();

                // Crear instancias dentro del bucle
                if (tipo.equals("Premium")) {
                    Premium premium = new Premium(tipo, nombre, contrasena);
                    premium.setDatosVuelo(fechaIda, fechaVuelta, cantBoletos, Aerolinea, numTarjeta, Clase, numAsiento, cantMaletas, cantPagos, cupones);
                    paquetes.add(premium);
                } else {
                    Base base = new Base(tipo, nombre, contrasena);
                    base.setDatosVuelo(fechaIda, fechaVuelta, cantBoletos, Aerolinea, numTarjeta, Clase, numAsiento, cantMaletas, cantPagos, cupones);
                    paquetes.add(base);
                }
            }
        } catch (IOException e) {
            System.err.println("No se ha encontrado el archivo que proporcionaste :(");
            e.printStackTrace();
        }

        return paquetes;
    }

    //Cada que se haga una reserva, se modifica la información del usuario dentro del CSV para tener sus datos actualizados al último vuelo
    public static void agregarDatosVuelo(String nombre, String fechaIda, int vuelta, int cantBoletos, String Aerolinea, long numTarjeta, String clase, String numAseinto, int cantMaletas, int cantPagos){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("DatosUsuarios.csv"));
            String linea; 
            StringBuilder sb = new StringBuilder();
            while((linea = reader.readLine()) != null){
                String[] datos = linea.split(",");
                if(datos.length > 0 && datos[1].equals(nombre)){
                    datos[3] = fechaIda;
                    datos[4] = String.valueOf(vuelta);
                    datos[5] = String.valueOf(cantBoletos);
                    datos[6] = Aerolinea;
                    datos[7] = String.valueOf(numTarjeta);
                    datos[8] = clase;
                    datos[9] = numAseinto;
                    datos[10] = String.valueOf(cantMaletas);
                    datos[11] = String.valueOf(cantPagos);

                }
                sb.append(String.join(",", datos)).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("DatosUsuarios.csv"))) {
                writer.write(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Modificar el valor de un entero en el CSV dado un indice
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

    //Modificar el valor de algún String que se encuentre en el CSV
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

    //Agregar neuvos clientes al CSV
    public void agregarCliente(String tipo, String nombre, String contrasena, String fechaIda, int fechaVuelta, int cantBoletos, String Aerolinea, long numTarjeta, String clase, String numAsiento, int cantMaletas, int cantPagos, String cupones){
        StringBuilder sb = new StringBuilder();
        sb.append(tipo).append(",").append(nombre).append(",").append(contrasena).append(",").append(fechaIda).append(",").append(fechaVuelta).append(",").append(cantBoletos).append(",").append(Aerolinea).append(",").append(numTarjeta).append(",").append(clase).append(",").append(numAsiento).append(",").append(cantMaletas).append(",").append(cantPagos).append(",").append(cupones);
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
