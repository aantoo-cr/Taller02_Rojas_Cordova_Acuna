package Hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GestorHabitacionesTest {

    @Test
    public void testmostrarEstadoHabitaciones() {
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        gestorHabitaciones.mostrarEstadoHabitaciones();
    }

    @Test
    public void testreservarHabitacion() {
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        assertThrows(HabitacionNoDisponibleException.class, () -> gestorHabitaciones.reservarHabitacion(1, 3, true)); // Esto debería lanzar una excepción
    }

    @Test
    public void testocuparHabitacion() {
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        assertThrows(Exception.class, () -> gestorHabitaciones.ocuparHabitacion(1)); // Esto debería lanzar una excepción
    }

@Test
public void testimprimirBoleta() throws Exception, HabitacionNoDisponibleException {
    GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
    gestorHabitaciones.reservarHabitacion(1, 3, true); // Reservar la habitación antes de ocuparla
    gestorHabitaciones.ocuparHabitacion(1); // Ocupar la habitación antes de imprimir la boleta
    gestorHabitaciones.imprimirBoleta(1);
}

    @Test
    public void testreiniciarHotel() {
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        gestorHabitaciones.reiniciarHotel("resetAll");
    }

    @Test
    public void testbuscarHabitacion() {
        GestorHabitaciones gestorHabitaciones = new GestorHabitaciones();
        assertEquals(1, gestorHabitaciones.buscarHabitacion(1));
    }



}
