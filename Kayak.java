import java.util.ArrayList;
import java.util.Scanner;

public class Kayak {
    private static ManejoCSV manejoCSV = new ManejoCSV();
    private static ArrayList<Paquete> paquetes = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static boolean systemON = true;
    private static boolean systemON2 = true;
    private static String seleccion;
    private static boolean valid = false;
    private static int selecciontipo = 0;

    public static void main(String[] args){

        paquetes = manejoCSV.leerCSV();

        //Mensaje de bienvenida
        System.out.println("***********************************************************");
        System.out.println("*  __        _______ _     ____ ___  __  __ _____ _ _ _   *");
        System.out.println("*  \\ \\      / / ____| |   / ___/ _ \\|  \\/  | ____| | | |  *");
        System.out.println("*   \\ \\ /\\ / /|  _| | |  | |  | | | | |\\/| |  _| | | | |  *");
        System.out.println("*    \\ V  V / | |___| |__| |__| |_| | |  | | |___|_|_|_|  *");
        System.out.println("*     \\_/\\_/  |_____|_____\\____\\___/|_|  |_|_____(_|_|_)  *");
        System.out.println("***********************************************************");
        System.out.println("\nBuenos días querido usuario ;)\n" + "¿Qué deseas hacer el día de hoy?\n");

        while(systemON){
            System.out.println();
            System.out.println("1. Iniciar Sesión\n" + "2. Crear Cuenta\n" + "3. Salir");
            switch(seleccion = sc.nextLine()){
                case "1":
                    valid = false;
                    System.out.println();
                    System.out.println("¿Cuál es su nombre de usuario?");;
                    String nombre = sc.nextLine();
                    for(Paquete paquete : paquetes){
                        if(paquete instanceof Usuario){
                            Usuario usuario = (Usuario) paquete;
                            if(usuario.getNombre().equals(nombre)){
                                String contrasena = sc.nextLine();
                                if(usuario.getContrasena().equals(contrasena)){
                                    systemON2 = true;
                                    while(systemON2){
                                        valid = true;
                                        System.out.println("Estás dentro del sistema!!\n");
                                        System.out.println("¿Qué deseas hacer el día de hoy?\n");
                                        System.out.println("1. Hacer una reserva de viaje\n" + "2. Modificar perfil\n" + "3. Salir a la página de inicio\n");
                                        seleccion = sc.nextLine();
                                        switch (seleccion){
                                            case "1":
                                                if(usuario.getTipoUsuario().equals("Premium")){
                                                    Premium cliente = (Premium) paquete;
                                                    cliente.reservaViaje();
                                                    cliente.confirmacionPago();
                                                } else{
                                                    Base cliente = (Base) paquete;
                                                    cliente.reservaViaje();
                                                    cliente.confirmacionPago();
                                                }
                                                break;
                                            
                                            case "2":
                                                if(usuario.getTipoUsuario().equals("Premium")){
                                                    Premium cliente = (Premium) paquete;
                                                    cliente.modificarPerfil();
                                                } else{
                                                    Base cliente = (Base) paquete;
                                                    cliente.modificarPerfil();
                                                }
                                                break;
                                            
                                            case "3":
                                                System.out.println("Que tengas un buen día :)");
                                                systemON2 = false;
                                                break;
                                        }
                                    }





                                }
                            }
                        }
                    }
                    if (valid == false){
                        System.out.println("Oh no! No hemos podido encontrar un usuario con tu nombre o contraseña :(");
                    }
                    break;
            }
        }

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

    
}
