//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 10/10/2023
//Última modificación: 13/10/2023

import java.util.ArrayList;
import java.util.Scanner;

public class Kayak {
    //Variables a utilizar
    private static ManejoCSV manejoCSV = new ManejoCSV();
    private static ArrayList<Paquete> paquetes = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static boolean systemON = true;
    private static boolean systemON2 = true;
    private static String seleccion;
    private static boolean valid = false;
    private static int selecciontipo = 0;

    public static void main(String[] args){
        //Cargamos los usuarios que se tienen hasta el momento
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
            //Menú principal
            System.out.println("1. Iniciar Sesión\n" + "2. Crear Cuenta\n" + "3. Salir");
            switch(seleccion = sc.nextLine()){
                //Inicio de sesión
                case "1":
                    paquetes = manejoCSV.leerCSV();
                    valid = false;
                    //Se solicita el nombre y contraseña del usuario
                    System.out.println();
                    System.out.println("¿Cuál es su nombre de usuario?");
                    String nombre = sc.nextLine();
                    System.out.println("\n¿Cuál es su contraseña?");
                    String contrasena = sc.nextLine();
                    
                    for(Paquete paquete : paquetes){
                        if(paquete instanceof Usuario){
                            Usuario usuario = (Usuario) paquete;
                            if(usuario.getNombre().equals(nombre)){
                                if(usuario.getContrasena().equals(contrasena)){
                                    systemON2 = true;
                                    while(systemON2){
                                        //Si se encuentra el usuario y la contraseña se muestra el segundo menú
                                        valid = true;
                                        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                                        System.out.println("Estás dentro del sistema!!\n");
                                        System.out.println("¿Qué deseas hacer el día de hoy?\n");
                                        System.out.println("1. Hacer una reserva de viaje\n" + "2. Modificar perfil\n" + "3. Salir a la página de inicio\n");
                                        seleccion = sc.nextLine();
                                        switch (seleccion){
                                            case "1":
                                                //Acorde al tipo de usuario se manda a llamar al tipo correspondiente
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
                                                //Acorde al tipo se desplegará un menú distinto
                                                if(usuario.getTipoUsuario().equals("Premium")){
                                                    Premium cliente = (Premium) paquete;
                                                    cliente.modificarPerfil();
                                                } else{
                                                    Base cliente = (Base) paquete;
                                                    cliente.modificarPerfil();
                                                    System.out.println("Regresando al menú principal :)");
                                                    System.out.println("Se estará actualizando sus datos.");
                                                    systemON2 = false;
                                                }
                                                break;
                                            
                                            case "3":
                                                //Regresamos al menú principal
                                                System.out.println("Que tengas un buen día :)");
                                                systemON2 = false;
                                                break;

                                            default:
                                                //Mensaje a mostrar en caso de errores
                                                System.out.println("Por favor, ingresa una opción válida.");
                                                break;
                                        }
                                    }





                                }
                            }
                        }
                    }
                    //Mensaje a mostrar si no se encuentra el usuario o la contraseña
                    if (valid == false){
                        System.out.println("Oh no! No hemos podido encontrar un usuario con tu nombre o contraseña :(");
                    }
                    break;

                case "2":
                    //Se crea una cuenta
                    String tipoUsuario = "";
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    //Se solicita el nombre de usuario
                    System.out.println("Empecemos con tu proceso!!!");
                    System.out.println("Empieza seleccionando un nombre de usuario por favor: ");
                    String nombreUsuario = sc.nextLine();
                    System.out.println("Muy bien!!");
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    //Se solicita la contraseña a definir
                    System.out.println("Ahora coloca la que quieras que sea tu contraseña :)");
                    String contraUsuario = sc.nextLine();
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    selecciontipo = 0;
                    //Obtenemos el tipo de usuario que desea tener
                    while(!(selecciontipo >= 1 && selecciontipo <= 2)){
                        System.out.println("¿Qué tipo de usuario desea tener?");
                        System.out.println("1. Premium\n" + "2. Base\n");
                        selecciontipo = obtenerEnteroValido(sc);
                        switch(selecciontipo){
                            case 1:
                                tipoUsuario = "Premium";
                                paquetes.add(new Premium(tipoUsuario, nombreUsuario, contraUsuario));
                                manejoCSV.agregarCliente(tipoUsuario, nombreUsuario, contraUsuario, "VACIO", -1, -1, "VACIO", -1, "VACIO", "VACIO", -1, -1, "VACIO");
                                break;
                            case 2:
                                tipoUsuario = "Base";
                                paquetes.add(new Base(tipoUsuario, nombreUsuario, contraUsuario));
                                manejoCSV.agregarCliente(tipoUsuario, nombreUsuario, contraUsuario, "VACIO", -1, -1, "VACIO", -1, "VACIO", "VACIO", -1, -1, "VACIO");
                                break;
                            default:
                                System.out.println("Por favor, ingresa una opción válida");

                        }
                    }
                    System.out.println("Listo, ha completado la creación de su cuenta ;)\n");
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    break;
                
                //Se sale de todo el programa
                case "3":
                System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    System.out.println("Que tenga un buen día!!!");
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    systemON = false;
                    break;
                
                default:
                    System.out.println("Por favor, ingresa una opción válida");
                    
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
