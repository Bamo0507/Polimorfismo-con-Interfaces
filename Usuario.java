//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 10/10/2023
//Última modificación: 13/10/2023

import java.util.Scanner;
import java.util.Set;

public class Usuario {
    //Atributos que comparten todos los tipos de usuarios
    protected String tipoUsuario;
    protected String fechaIda;
    protected int fechaVuelta;
    protected int cantBoletos;
    protected String Aerolinea;
    protected long numTarjeta;
    protected String claseVuelo;
    protected String numAsiento;
    protected int cantMaletas;
    protected String nombre;
    protected String contrasena;
    protected int cantPagos;
    protected String cupones;
    protected ManejoCSV manejoCSV = new ManejoCSV();

    //Constructor para los usuarios
    public Usuario(String tipoUsuario, String nombre, String contrasena) {
        this.tipoUsuario = tipoUsuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.fechaIda = "VACIO";
        this.fechaVuelta = -1;
        this.cantBoletos = -1;
        this.Aerolinea = "VACIO";
        this.numTarjeta = -1;
        this.claseVuelo = "VACIO";
        this.numAsiento = "VACIO";
        this.cantMaletas = -1;
        this.cantPagos = -1;
        this.cupones = "VACIO";
    }    

    //Método para asegurarse que se ingrese un entero en los campos necesarios
    public static int obtenerEnteroValido(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;
        System.out.println("------------------------");
        do {
            try {
                System.out.print("Por favor, ingresa un número entero: ");
                String entrada = scanner.nextLine();
                numero = Integer.parseInt(entrada);
                entradaValida = true;
                System.out.println("");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes ingresar un número entero.");
            }
        } while (!entradaValida);

        return numero;
    }

    //Definimos los valores de todos los usuarios después de que se complta el proceso de reservar un vuelo
    public void setDatosVuelo(String fechaIda, int fechaVuelta, int cantBoletos, String aerolinea,
                              long numTarjeta, String claseVuelo, String numAsiento, int cantMaletas,
                              int cantPagos, String cupones) {
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
        this.cantBoletos = cantBoletos;
        this.Aerolinea = aerolinea;
        this.numTarjeta = numTarjeta;
        this.claseVuelo = claseVuelo;
        this.numAsiento = numAsiento;
        this.cantMaletas = cantMaletas;
        this.cantPagos = cantPagos;
        this.cupones = cupones;
    }

    //Getters a utilizar en el código
    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }  
  


}
