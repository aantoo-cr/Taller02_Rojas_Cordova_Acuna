package Hotel;

import java.util.ArrayList;

public class GestorHabitaciones {

    private ArrayList<Habitacion> habitaciones; // Lista de todas las habitaciones

    // Constructor: Inicializa las 10 habitaciones del hotel
    public GestorHabitaciones() {
        this.habitaciones = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            habitaciones.add(new Habitacion(i)); // Crea 10 habitaciones numeradas
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
        habitacion.setCantidadNoches(cantidadNoches);
        habitacion.setServicioAlimentacion(servicioAlimentacion);
        habitacion.setEstado("R"); // Cambiar estado a Reservada
    }

    // After
    public void ocuparHabitacion(int numeroHabitacion) throws Exception {
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);
        if (!habitacion.getEstado().equals("R")) { // Si la habitación no está reservada (R)
            throw new Exception("La habitación no está reservada.");
        }
        habitacion.setEstado(habitacion.getAlimentacion() ? "OA" : "OS"); // Cambiar estado a Ocupada
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
    public Habitacion buscarHabitacion(int indice) {
        if (indice < 1 || indice > habitaciones.size()) {
            throw new IllegalArgumentException("Índice de habitación no válido.");
        }
        return habitaciones.get(indice - 1); // Restar 1 porque los índices de la lista empiezan en 0
    }

}