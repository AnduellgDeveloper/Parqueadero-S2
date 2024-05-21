package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase Vehiculo.
 */
public class Test_Vehiculo {

    /**
     * Prueba la creación de un vehículo con valores iniciales.
     */
    @Test
    public void crearVehiculo() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        Vehiculo vehiculo = new Vehiculo("XYZ123", "Toyota", "Juan", fechaEntrada);

        assertEquals("XYZ123", vehiculo.getPlaca());
        assertEquals("Toyota", vehiculo.getModelo());
        assertEquals("Juan", vehiculo.getPropietario());
        assertEquals(fechaEntrada, vehiculo.getFechaEntrada());
    }

    /**
     * Prueba la creación de un vehículo con fecha de entrada y salida.
     */
    @Test
    public void crearVehiculoConFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        LocalDateTime fechaSalida = LocalDateTime.of(2024, 6, 20, 15, 0);
        Vehiculo vehiculo = new Vehiculo("ABC456", "Honda", "Ana", fechaEntrada, fechaSalida);

        assertEquals("ABC456", vehiculo.getPlaca());
        assertEquals("Honda", vehiculo.getModelo());
        assertEquals("Ana", vehiculo.getPropietario());
        assertEquals(fechaEntrada, vehiculo.getFechaEntrada());
        assertEquals(fechaSalida, vehiculo.getFechaSalida());
    }

    /**
     * Prueba el establecimiento de la fecha de salida de un vehículo.
     */
    @Test
    public void establecerFechaSalidaVehiculo() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        Vehiculo vehiculo = new Vehiculo("DEF789", "Chevrolet", "Maria", fechaEntrada);

        LocalDateTime fechaSalida = LocalDateTime.of(2024, 6, 20, 16, 0);
        vehiculo.setFechaSalida(fechaSalida);

        assertEquals(fechaSalida, vehiculo.getFechaSalida());
    }
}
