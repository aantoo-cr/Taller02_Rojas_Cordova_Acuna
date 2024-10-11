package Hotel;

import java.util.Scanner;

public class Menu {

    private GestorHabitaciones gestorHabitaciones;

    public Menu() {
        this.gestorHabitaciones = new GestorHabitaciones();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú de Administración `Donde cabe 1, caben 2´");
            System.out.println("1. Consultar estado de las habitaciones");
            System.out.println("2. Hacer una reserva");
            System.out.println("3. Confirmar reserva y ocupar habitación");
            System.out.println("4. Dejar habitación disponible");
            System.out.println("5. Imprimir boleta");
            System.out.println("6. Dejar el hotel completo disponible");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarEstadoHabitaciones();
                    break;
                case 2:
                    reservarHabitacion(scanner);
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
        } while (opcion != 7);
    }

    private void reiniciarHotel(Scanner scanner) {
        System.out.print("Ingrese la clave para reiniciar el hotel: ");
        String clave = scanner.next();
        gestorHabitaciones.reiniciarHotel(clave);
        System.out.println("Hotel reiniciado con éxito.");
    }

    private void confirmarReserva(Scanner scanner) {
        System.out.print("Ingrese el número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
        try {
            gestorHabitaciones.ocuparHabitacion(numeroHabitacion);
            System.out.println("Habitación ocupada con éxito.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void reservarHabitacion(Scanner scanner) {
        System.out.print("Ingrese el número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
        System.out.print("Ingrese la cantidad de noches: ");
        int cantidadNoches = scanner.nextInt();
        System.out.print("¿Desea servicio de alimentación? (s/n): ");
        String respuesta = scanner.next();
        boolean servicioAlimentacion = respuesta.equalsIgnoreCase("s");
        try {
            gestorHabitaciones.reservarHabitacion(numeroHabitacion, cantidadNoches, servicioAlimentacion);
            System.out.println("Habitación reservada con éxito.");
        } catch (HabitacionNoDisponibleException e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultarEstadoHabitaciones() {
        gestorHabitaciones.mostrarEstadoHabitaciones();
    }

    private void dejarHabitacionDisponible(Scanner scanner) {
        System.out.print("Ingrese el número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
        gestorHabitaciones.liberarHabitacion(numeroHabitacion);
        System.out.println("Habitación liberada con éxito.");
    }

    private void imprimirBoleta(Scanner scanner) {
        System.out.print("Ingrese el número de habitación: ");
        int numeroHabitacion = scanner.nextInt();
        gestorHabitaciones.imprimirBoleta(numeroHabitacion);
    }
}