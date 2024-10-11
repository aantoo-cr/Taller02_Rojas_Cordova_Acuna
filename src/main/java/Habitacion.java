
import java.util.Scanner;

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
        if (!estado.equals("OA") && !estado.equals("OS")) { // Para verificar que la habitacion esté ocupada
            throw new IllegalStateException("Esta habitación no está ocupada.");
        }
        int total = calcularMonto();
        estado = "D"; // Cambia el estado a Disponible
        return generarBoleta(total);
    }

    private String generarBoleta(int total) {
        return "Boleta:\nNoches: " + noches + "\nCon alimentación: " + (alimentacion ? "Sí" : "No") + "\nMonto total: $" + total;
    }

    public void reset() {
        this.estado = "D";
        this.noches = 0;
        this.alimentacion = false;
    }


//metodos para el menu con las 2 clases creadas


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
        for (int i = 0; i < habitaciones.length; i++) {
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

    public void confirmarReserva(Scanner scanner) {
        System.out.print("Ingrese el número de la habitación (1-10): ");
        int numeroHabitacion = scanner.nextInt();
        try {
            habitaciones[numeroHabitacion - 1].reservar();
            System.out.println("Reserva confirmada y habitación ocupada.");
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
            String boleta = habitaciones[numeroHabitacion - 1].generarBoleta();
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