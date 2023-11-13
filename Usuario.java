import java.util.Scanner;
import java.util.Set;

public class Usuario {
    //Atributos que comparten todos los tipos de usuarios
    protected String tipoUsuario;
    protected String fechaIda;
    protected int fechaVuelta;
    protected int cantBoletos;
    protected String Aerolinea;
    protected int numTarjeta;
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
        this.fechaIda = " ";
        this.fechaVuelta = -1;
        this.cantBoletos = -1;
        this.Aerolinea = " ";
        this.numTarjeta = -1;
        this.claseVuelo = " ";
        this.numAsiento = " ";
        this.cantMaletas = -1;
        this.cantPagos = -1;
        this.cupones = " ";
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

    public void setDatosVuelo(String fechaIda, int fechaVuelta, int cantBoletos, String aerolinea,
                              int numTarjeta, String claseVuelo, String numAsiento, int cantMaletas,
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

    public void setFechaIda(String fechaIda) {
        this.fechaIda = fechaIda;
    }

    public void setFechaVuelta(int fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public void setCantBoletos(int cantBoletos) {
        this.cantBoletos = cantBoletos;
    }

    public void setAerolinea(String aerolinea) {
        Aerolinea = aerolinea;
    }

    public void setNumTarjeta(int numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public void setClaseVuelo(String claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public void setNumAsiento(String numAsiento) {
        this.numAsiento = numAsiento;
    }

    public void setCantMaletas(int cantMaletas) {
        this.cantMaletas = cantMaletas;
    }

    public void setCantPagos(int cantPagos) {
        this.cantPagos = cantPagos;
    }

    public void setCupones(String cupones) {
        this.cupones = cupones;
    }

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
