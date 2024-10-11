package Hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GestorHabitacionesTest {

    @Test
    public void testReservar() {
        Habitacion habitacion = new Habitacion(1);
        habitacion.reservar(3, true);
        assertEquals("R", habitacion.getEstado());
    }

    @Test
public void testReservarHabitacionNoDisponible() {
    Habitacion habitacion = new Habitacion(1);
    habitacion.reservar(3, true);
    assertThrows(IllegalStateException.class, () -> {
        habitacion.reservar(2, false); // Esto debería lanzar una excepción
    });
}

    @Test
    public void testOcupar() {
        Habitacion habitacion = new Habitacion(1);
        habitacion.reservar(3, true);
        habitacion.ocupar();
        assertEquals("OA", habitacion.getEstado());
    }


    @Test
public void testOcuparHabitacionNoReservada() {
    Habitacion habitacion = new Habitacion(1);
    assertThrows(IllegalStateException.class, habitacion::ocupar); // Esto debería lanzar una excepción
}
}