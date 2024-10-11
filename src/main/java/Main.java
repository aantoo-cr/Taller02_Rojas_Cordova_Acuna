package Hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
}

public class GestorHabitaciones {

    private ArrayList<Habitacion> habitaciones; // Lista de todas las habitaciones

    // Constructor: Inicializa las 10 habitaciones del hotel
    public GestorHabitaciones() {
        this.habitaciones = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            habitaciones.add(new Habitacion()); // Crea 10 habitaciones numeradas
        }
    }

    // Mostrar el estado de todas las habitaciones
    public void mostrarEstadoHabitaciones() {
        for (Habitacion habitacion : habitaciones) {
            System.out.println(habitacion.toString());
        }
    }

    // Reservar habitacion si es que esta disponible
    public void reservarHabitacion(int numeroHabitacion, int cantidadNoches, boolean servicioAlimentacion) throws HabitacionNoDisponibleException {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (!habitacion.getEstado().equals("D")) { // Si la habitación no está disponible (D)
            throw new HabitacionNoDisponibleException("La habitación no está disponible para reserva.");
        }
        habitacion.reservar(cantidadNoches, servicioAlimentacion);
    }

    // After
    public void ocuparHabitacion(int numeroHabitacion) throws Exception {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (!habitacion.getEstado().equals("R")) { // Si la habitación no está reservada (R)
            throw new Exception("La habitación no está reservada.");
        }
        habitacion.ocupar(); // Cambiar estado a Ocupada
    }

    // Imprimir boleta y liberar habitacion
    public void imprimirBoleta(int numeroHabitacion) {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        String boleta = habitacion.liberar();
        System.out.println(boleta);
    }

    // Reiniciar el estado de todas las habitaciones a disponible
    public void reiniciarHotel(String clave) {
        if ("resetAll".equals(clave)) {
            for (Habitacion habitacion : habitaciones) {
                habitacion.reset();
            }
        } else {
            System.out.println("Clave incorrecta. No se puede reiniciar el hotel.");
        }
    }

    // Buscar habitacion por numero
    private Habitacion buscarHabitacion(int indice) {
        if (indice < 1 || indice > habitaciones.size()) {
            throw new IllegalArgumentException("Índice de habitación no válido.");
        }
        return habitaciones.get(indice - 1); // Restar 1 porque los índices de la lista empiezan en 0
    }

    // Salir del programa
    public void salir() {
        System.out.println("Saliendo del programa...");
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            mostrarOpciones(); // opciones del menu
            opcion = scanner.nextInt();
            manejarOpcion(opcion, scanner); //opcion elegida por el usuario
        } while (opcion != 7);
    }

    // Imprime las opciones del menu
    private void mostrarOpciones() {
        System.out.println("Menú de Administración `Donde cabe 1, caben 2´");
        System.out.println("1. Consultar estado de las habitaciones");
        System.out.println("2. Hacer una reserva");
        System.out.println("3. Confirmar reserva y ocupar habitación");
        System.out.println("4. Dejar habitación disponible");
        System.out.println("5. Imprimir boleta");
        System.out.println("6. Dejar el hotel completo disponible");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // opcion del usuario
    private void manejarOpcion(int opcion, Scanner scanner) {
        switch (opcion) {
            case 1:
                consultarEstadoHabitaciones();
                break;
            case 2:
                hacerReserva(scanner);
                break;
            case 3:
                confirmarReserva(scanner);
                break;
            case 4:
                dejarHabitacionDisponible(scanner);
                break;
            case 5:
                imprimirBoleta(scanner);
                break;
            case 6:
                reiniciarHotel(scanner);
                break;
            case 7:
                System.out.println("Saliendo del programa.");
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }
    }

    public void consultarEstadoHabitaciones() {
        for (int i = 0; i < habitaciones.lenght; i++) {
            System.out.println(habitaciones[i].consultarEstadoHabitacion(i + 1));
        }
    }

    public void hacerReserva(Scanner scanner) {
        System.out.print("Ingrese el número de la habitación (1-10): ");
        int numeroHabitacion = scanner.nextInt();
        System.out.print("Ingrese la cantidad de noches: ");
        int noches = scanner.nextInt();
        System.out.print("¿Solicita alimentación? (true/false): ");
        boolean alimentacion = scanner.nextBoolean();

        try {
            habitaciones[numeroHabitacion - 1].reservar(noches, alimentacion);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void dejarHabitacionDisponible(Scanner scanner) {
        System.out.print("Ingrese el número de la habitación (1-10): ");
        int numeroHabitacion = scanner.nextInt();
        try {
            String boleta = habitaciones[numeroHabitacion - 1].liberar();
            System.out.println("Habitación dejada disponible. " + boleta);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void imprimirBoleta(Scanner scanner){
        System.out.println("Ingrese el numero de la habitacion (1-10): ");
        int numeroHabitacion = scanner.nextInt();
        try {
            String boleta = habitaciones.get(numeroHabitacion - 1).generarBoleta();
            System.out.println("Boleta impresa" + boleta);
        }   catch (IllegalStateException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void reiniciarHotel(Scanner scanner) {
        System.out.print("Ingrese la clave para reiniciar las habitaciones: ");
        String clave = scanner.next();
        if ("resetAll".equals(clave)) {
            for(Habitacion habitacion : habitaciones) {
                habitacion.reset();
            }
            System.out.print("Hotel reiniciado. Todas las habitaciones disponibles");
        } else {
            System.out.println("Clave incorrecta. No se pudo reiniciar el hotel. ");
        }
    }
}

}


class Habitacion {

    private String estado; // "OA"(OcupadaAlimentación),"OS"(OcupadaAlimentación),"R"(Reservada),"D"(Disponible)
    private int noches;
    private boolean alimentacion;


    public Habitacion() {
        this.estado = "D"; // Todas las habitaciones empiezan disponibles
    }

    public String getEstado() {
        return estado;
    }

    public void reservar(int noches, boolean alimentacion) {
        try {   //Manejo de excepcion con try-catch
            if (!estado.equals("D")) {
                throw new IllegalStateException("No se puede reservar esta habitación. No está disponible.");
            }
            this.noches = noches;
            this.alimentacion = alimentacion;
            this.estado = "R"; // Reserva la habitacion
            System.out.println("La habitación ha sido reservada exitosamente.");
        } catch (IllegalStateException e) {
            System.out.println("Error al intentar reservar: " + e.getMessage());
        }
    }

    public void ocupar() {
        if (!estado.equals("R")) {
            throw new IllegalStateException("No se puede ocupar esta habitación. No está reservada.");
        }
        this.estado = alimentacion ? "OA" : "OS"; // Cambia el estado a Ocupada
    }
    private int calcularMonto() {
        int tarifa = alimentacion ? 45000 : 30000;  //Si la alimentacion es TRUE precio = 45000, si es FALSE precio= 30000
        return tarifa * noches;
    }

    public String liberar() {
        if (!estado.equals("OA") && !estado.equals("OS")) {
            throw new IllegalStateException("Esta habitación no se encuentra ocupada.");
        }
        int total = calcularMonto();
        estado = "D"; // Cambia el estado a Disponible
        return generarBoleta();
    }

    private String generarBoleta() {
        return "Boleta:\nNoches: " + noches + "\nCon alimentación: " + (alimentacion ? "Sí" : "No") + "\nMonto total: $" + total;
    }

    public void reset() {
        this.estado = "D";
        this.noches = 0;
        this.alimentacion = false;
    }
}

