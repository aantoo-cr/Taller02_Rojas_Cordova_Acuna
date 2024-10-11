public class HabitacionNoDisponibleException extends Throwable {

    public HabitacionNoDisponibleException(String message) {
        super(message);
    }

    public HabitacionNoDisponibleException() {
        super("La habitación no está disponible para reserva.");
    }

}
