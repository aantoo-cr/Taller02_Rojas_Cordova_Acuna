import org.junit.jupiter.api.*Test;

import static org.junit.Assert.assertEquals;


public class HabitacionTest {

    @Test
    public void testReservar() {
        Habitacion habitacion = new Habitacion();
        habitacion.reservar(3, true);
        assertEquals("R", habitacion.getEstado());
    }
}

    @Test(expected = IllegalStateException.class)
    public void testReservarHabitacionNoDisponible() {
        Habitacion habitacion = new Habitacion();
        habitacion.reservar(3, true);
        habitacion.reservar(2, false); // Esto debería lanzar una excepción
}

    @Test
    public void testOcupar() {
        Habitacion habitacion = new Habitacion();
        habitacion.reservar(3, true);
        habitacion.ocupar();
        assertEquals("OA", habitacion.getEstado());
}

    @Test(expected = IllegalStateException.class)
    public void testOcuparHabitacionNoReservada() {
        Habitacion habitacion = new Habitacion();
        habitacion.ocupar(); // Esto debería lanzar una excepción
}

