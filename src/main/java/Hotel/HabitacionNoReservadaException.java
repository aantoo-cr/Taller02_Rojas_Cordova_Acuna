package Hotel;

public class HabitacionNoReservadaException {
    public HabitacionNoReservadaException(String message) {
        super(message);
    }

    public HabitacionNoReservadaException() {
        super("La habitación no está disponible para reserva.");
    }

}
