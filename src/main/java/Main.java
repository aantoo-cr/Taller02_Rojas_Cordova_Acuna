import java.util.Scanner;

public class Main {
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
}

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