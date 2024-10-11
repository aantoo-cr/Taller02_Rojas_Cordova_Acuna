import java.util.Scanner;

public void mostrarMenu() {
    Scanner scanner = new Scanner(System.in);
    int option;
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
    } while (opcion != 7);
}