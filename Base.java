//Bryan Alberto Martínez Orellana
//Carnét 23542
//Ingeniería en Ciencias de la Computación
//Programación Orientada a Objetos
//Creación: 10/10/2023
//Última modificación: 13/10/2023

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Date;

public class Base extends Usuario implements Paquete {

    //Constructor de los usuario base
    public Base(String tipoUsuario, String nombre, String contrasena) {
        super(tipoUsuario, nombre, contrasena);
    }

    @Override
    //Método para selccionar una cantidad de maletas, tomando en cuenta el tipo de usuario
    public int cantidadMaletas() {
        Scanner sc = new Scanner(System.in);  
        //Dependiendo del tipo de usuario, se podrán tener diferentes cantidades en las maletas  
        if (tipoUsuario.equals("Base")) {
            cantMaletas = 1;
            System.out.println("Por su plan base, se le ha definido que solo puede llevar una maleta po boleto.");
        } else {
            System.out.println("¿Cuántas maletas le gustaría tener?");
            while (cantMaletas < 0 || cantMaletas > 3) {
                System.out.println("Seleccione la cantidad de maletas:");
                System.out.println("1. 0 maletas");
                System.out.println("2. 1 maleta");
                System.out.println("3. 2 maletas");
                System.out.println("4. 3 maletas");
    
                String seleccion = sc.nextLine();
    
                switch (seleccion) {
                    case "1":
                        cantMaletas = 0;
                        break;
                    case "2":
                        cantMaletas = 1;
                        break;
                    case "3":
                        cantMaletas = 2;
                        break;
                    case "4":
                        cantMaletas = 3;
                        break;
                    default:
                        System.out.println("Por favor ingresa una opción válida ;)");
                        break;
                }
            }
        }
        return cantMaletas;
    }    

    @Override
    //Método para definir toda la información de pago que se requiere para una reserva
    public void confirmacionPago() {
        Scanner sc = new Scanner(System.in);
        boolean entradaValida = false;
        String input;

        //Se solicita una tarjeta de 12 dígitos
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
        System.out.println("¿Cuál será la tarjeta que desea emplear para las transacciones?");
        while (!entradaValida) {
            input = sc.nextLine();
            // Verifica si la entrada tiene exactamente 12 dígitos
            if (input.matches("\\d{12}")) {
                numTarjeta = Long.parseLong(input);
                entradaValida = true;
            } else {
                System.out.println("Por favor, ingrese un número de tarjeta válido de 12 dígitos.");
            }
        }
        entradaValida = false;

        //Definimos la cantidad de pagos en los que se espera terminar de pagar
        //Solo se permiten de 1 a 24 cuotas
        int cantPagos = 0;
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
        if (tipoUsuario.equals("Premium")){
            System.out.println("Por ser premium, ya se ha definido que pagará en un pago ;)");
            cantPagos = 1;
        }else {
            System.out.println("¿A cuántos pagos terminará de cancelar el monto total?");
            while (!entradaValida) {
                try {
                    System.out.println("Ingrese un número entre 1 y 24:");
                    cantPagos = Integer.parseInt(sc.nextLine());
                    // Verifica si el número está en el rango especificado
                    if (cantPagos >= 1 && cantPagos <= 24) {
                        entradaValida = true;
                    } else {
                        System.out.println("Por favor, ingrese un número válido entre 1 y 24.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido.");
                }
            }
        }
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        //Se establece la clase de cada uno de los boletos
        if(tipoUsuario.equals("Premium")){
            System.out.println("Por ser premium, ya se han colocado sus boletos en clase premium.");
            claseVuelo ="Premium";
        } else{
            System.out.println("¿En qué clase deseas volar?");
            while (!claseVuelo.equals("Coach") && !claseVuelo.equals("Premium")) {
                System.out.println("Seleccione la clase de vuelo:");
                System.out.println("1. Clase Coach");
                System.out.println("2. Clase Premium");

                int opcion = sc.nextInt();
                sc.nextLine(); // Consumir el salto de línea después de nextInt

                switch (opcion) {
                    case 1:
                        claseVuelo = "Coach";
                        break;
                    case 2:
                        claseVuelo = "Premium";
                        break;
                    default:
                        System.out.println("Por favor, seleccione una opción válida.");
                        break;
                }
            }
        }
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        //Definimos la cantidad de maletas
        cantMaletas = cantidadMaletas();
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        //Seleccionamos los asientos
        Set<Integer> asientos = new HashSet<>();
        for(int i = 0; i < cantBoletos; i++) {
            
            int numero;
            do{
                numero = seleccionAsiento();
            } while(asientos.contains(numero));
            asientos.add(numero);
        }
        int[] numAsiento1 = asientos.stream().mapToInt(Integer::intValue).toArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < numAsiento1.length; i++){
            sb.append(numAsiento1[i]);
            if(i<numAsiento1.length -1){
                sb.append(";");
            }
        }
        System.out.println("Se le han asignado los asientos: " + asientos + ".");
        numAsiento = sb.toString();

        //Mostrar al usuario todo lo que ha seleccionado, se verificará si quiere continuar
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
        System.out.println("Has definido que te irás: " + fechaIda);
        System.out.println("Has seleccionado que quieres " + cantBoletos + " boletos");
        System.out.println("Regresaras en " + fechaVuelta + " días");
        System.out.println("NOTA: Si aparece 0 en días de vuelta, es porque su vuelo es solo de ida.");
        System.out.println("Seleccionó a la aerolínea " + Aerolinea + ".");
        System.out.println("Pagará en " +cantPagos + " pagos.");
        System.out.println("Viajará en clase: " +  claseVuelo + ".");
        System.out.println("Lleva " + cantMaletas + " maletas.");
        System.out.println("Tomó los asientos: " + numAsiento);
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        //Establecemos si el usuario confirma su compra o si ya no quiere
        int seleccionTipo = 0;
        String establecerSiNo = "";
        System.out.println("¿Desea confirmar su compra?");
        while (!(seleccionTipo >= 1 && seleccionTipo <= 2)) {
            System.out.println("1.  Si");
            System.out.println("2. No");
            System.out.print("Opción: ");

            switch (seleccionTipo = obtenerEnteroValido(sc)) {
                case 1:
                establecerSiNo = "Si";
                    break;

                case 2:
                establecerSiNo = "No";
                    break;

                default:
                    System.out.println("Por favor, ingresa una opción válida. Solo hay sí o no.\n");
                    break;
            }
        }
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        //Se confirma el pago si desea confirmar
        if(establecerSiNo.equals("Si")){
            System.out.println("LISTO!!! Su vuelo ha sido confirmado.\n");
        } else{ //Si no confirma, reseteamos toda la información importante
            System.out.println("Se ha borrado la información que ha seleccionado, cancelamos su pago :)");
            fechaIda = "";
            fechaVuelta = -1;
            cantBoletos = -1;
            Aerolinea = "";
            cantPagos = -1;
            claseVuelo = "";
            numAsiento = "";
            cantMaletas = -1;
        }
        manejoCSV.agregarDatosVuelo(nombre, fechaIda, fechaVuelta, cantBoletos, Aerolinea, numTarjeta, claseVuelo, numAsiento, cantMaletas, cantPagos);
    }

    @Override
    //Opciones para modificar perfil
    public void modificarPerfil() {
        Scanner sc = new Scanner(System.in);
        //Menú para modificar características del perfil
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
        System.out.println("Hola querido usuario, qué desearías hacer con tu cuenta?");
        int seleccion = 0;
        while(!(seleccion >= 1 && seleccion <= 3)){
            
            System.out.println("¿Qué deseas hacer el día de hoy?");
            System.out.println("1. Subir a PREMIUM!!!");
            System.out.println("2. Aplicar cupón");
            System.out.println("3. Cambiar contraseña");
            seleccion = obtenerEnteroValido(sc);
            switch(seleccion){
                case 1:
                    //Se modifica el tipo del usuario
                    System.out.println("Listo!! Ahora es un usuario PREMIUM");
                    tipoUsuario = "Premium";
                    manejoCSV.agregarValoresStr(nombre, 0, "Premium");
                    System.out.println("En la próxima vez que ingrese aparecerá formalmente como un usuario PREMIUM ;)");
                    System.out.println("Sin embargo, de momento ya puede acceder a algunas de estas opciones.");
                    System.out.println("Regresando al menú principal.");
                    break;
                case 2:
                    //Se asigna un cupón al usuario (BÁSICO)
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    if(tipoUsuario.equals("Base")){
                        System.out.println("Por ser usuario base, solo se le aplicará un cupón del 10% en su siguiente compra.");
                        System.out.println("Para mayores beneficios, suba a premium.");
                        cupones = "10% en siguiente compra";
                    }else {
                        int seleccion1 = 0;
                        while(!(seleccion1 >= 1 && seleccion1 <= 3)){
                            System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                            System.out.println("¿Qué cupón desea aplicar?");
                            System.out.println("1. 50% en su siguiente vuelo");
                            System.out.println("2. 30% en su siguiente vuelo, más servicio de comida gourmet durante el vuelo.");
                            System.out.println("3. 20% de descuento en su siguiente viaje, más unos audífonos Sony WH-1000XM5 por asiento.");
                            seleccion1 = obtenerEnteroValido(sc);
                            switch (seleccion1){
                                case 1:
                                    cupones = "50% en siguiente compra";
                                    break;
                                case 2:
                                    cupones = "30% en siguiente compra | más servicio gourmet";
                                    break;
                                case 3:
                                    cupones = "20% en siguiente compra | más audífonos Sony WH-1000XM5 por asiento";
                                    break;
                                default:
                                    System.out.println("Por favor, seleccione una opción válida ;)");
                                    break;
                            }
                        }
                    }
                    manejoCSV.agregarValoresStr(nombre, 12, cupones);
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    break;
                case 3:
                    //Modificación de la contraseña
                    System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
                    System.out.println("¿Cuál quiere que sea su nueva contrasena?");
                    contrasena = sc.nextLine();
                    manejoCSV.agregarValoresStr(nombre, 2, contrasena);
                    System.out.println("Regresando al menú principal.");
                    break;
            }
        }
        
    }

    @Override
    //Método para hacer una reservación en Base
    public void reservaViaje() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaPartida = null;
        boolean fechaValida = false;

        //Solicitamos la fecha de ida del usuario
        while (!fechaValida) {
            System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
            System.out.print("Por favor, ingrese la fecha en la que desea partir(dd/MM/yyyy): ");
            String fechaPartidaStr = sc.nextLine();

            try {
                fechaPartida = dateFormat.parse(fechaPartidaStr);
                fechaValida = true;
            } catch (ParseException e) {
                System.out.println("Fecha de nacimiento no válida. Inténtelo de nuevo.");
            }
        }
        System.out.println("Muchas gracias!!");
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");
        System.out.println();
        SimpleDateFormat formatoFechaIda = new SimpleDateFormat("dd/MM/yyyy");
        fechaIda = formatoFechaIda.format(fechaPartida);

        //Definimos si tendrá fecha de vuelta
        System.out.println("¿El viaje será también de vuelta?");
        int seleccionTipo = 0;
        String establecerSiNo = "";
        while (!(seleccionTipo >= 1 && seleccionTipo <= 2)) {
            System.out.println("1.  Si");
            System.out.println("2. No");
            System.out.print("Opción: ");

            switch (seleccionTipo = obtenerEnteroValido(sc)) {
                case 1:
                establecerSiNo = "Si";
                    break;

                case 2:
                establecerSiNo = "No";
                    break;

                default:
                    System.out.println("Por favor, ingresa una opción válida. Solo hay sí o no.\n");
                    break;
            }
        }
        System.out.println();
        //Dependiendo de si tendrá fecha de vuelta o no se le solicita o coloca un dato
        if(establecerSiNo.equals("Si")){
            System.out.println("¿Dentro de cuántos días volverá?");
            fechaVuelta = obtenerEnteroValido(sc);
        } else{
            fechaVuelta = 0;
        }
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        //Establecemos una cantidad de boletos
        int numero = -1;
        System.out.println("¿Cuántos boletos necesitará?");
        System.out.println("Solamente puede seleccionar de 1-20 boletos.");
        while(!(numero >= 1 && numero <= 20)){
            numero = obtenerEnteroValido(sc);
        }

        cantBoletos = numero;
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~  ~~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~  ~~~~~~~~~~~~");

        int seleccion = 0;
        while(!(seleccion >= 1 && seleccion <= 5)){
            System.out.println("En qué aerolíena le gustaría viajar?");
            System.out.println("1. Copa Airlines");
            System.out.println("2. United Airlines");
            System.out.println("3. Delta");
            System.out.println("4. American Airlines");
            System.out.println("5. UNITED");
            seleccion = obtenerEnteroValido(sc);
            switch(seleccion){
                case 1:
                    Aerolinea = "Copa Airlines";
                    break;
                case 2:
                    Aerolinea = "United Airlines";
                    break;
                case 3:
                    Aerolinea = "Delta";
                    break;
                case 4: 
                    Aerolinea = "American Airlines";
                    break;
                case 5:
                    Aerolinea = "UNITED";
                    break;
                default:
                    System.out.println("Por favor, selecciona una opción válida.");
                    break;
            }
        }  

    }

    @Override
    public int seleccionAsiento() {
        //Establecemos los números de asientos
        Random rand = new Random();
        int numero = -1;
        Scanner sc = new Scanner(System.in);
        if(tipoUsuario.equals("Base")){
            for(int i=0; i<= cantBoletos; i++){
                numero = rand.nextInt(20) + 1;
                
            }
        } else {
            System.out.println("Recuerda que debes ir seleccionando números de asientos distintos.");
            System.out.println();
            System.out.println("Selecione la cantidad de asientos que concuerden con su cantidad de boletos.");
            System.out.println("Seleccionó que quería: " + cantBoletos + " boletos.");
            System.out.println("Seleccione un número del 1-20");
            while (!(numero >= 1 && numero <= 20)) {
                numero = obtenerEnteroValido(sc);
        }
        }
        return numero;
    }

    
    
}
