package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas para la clase Parqueadero.
 */
public class Test_Parqueadero {
    private static final Logger LOG = Logger.getLogger(Test_Parqueadero.class.getName());

    /**
     * Prueba para obtener la diferencia de horas entre la fecha de entrada y salida de un vehículo.
     */
    @Test
    public void obtenerDiferenciaHorasTest() {
        LOG.info("Iniciando test obtenerDiferenciaHoras");
        Parqueadero parqueadero = new Parqueadero(2, 2, 1000, 800, 500);
        Vehiculo carro = new Carro("XYZ123", "Toyota", "Juan", LocalDateTime.of(2024, 1, 22, 7, 0));

        parqueadero.agregar(carro);
        carro.setFechaSalida(LocalDateTime.of(2024, 1, 22, 8, 0)); // Cambiada la fecha para un caso de prueba válido
        long horas = parqueadero.obtenerDiferenciaHoras(carro);
        assertEquals(1, horas);

        LOG.info("Finalizando test obtenerDiferenciaHoras");
    }

    /**
     * Prueba para calcular el costo de estacionamiento de varios tipos de vehículos.
     */
    @Test
    public void calcularCostoTest() {
        LOG.info("Iniciando test para calcular costo");

        Parqueadero parqueadero = new Parqueadero(2, 2, 1000, 800, 500);

        Vehiculo carro = new Carro("XYZ123", "Toyota", "Juan", LocalDateTime.of(2024, 1, 22, 7, 0));
        parqueadero.agregar(carro);
        carro.setFechaSalida(LocalDateTime.of(2024, 1, 22, 9, 0)); // 2 horas

        Vehiculo motoHibrida = new Moto("ABC456", "Honda", "Pedro", LocalDateTime.of(2024, 1, 22, 7, 0), 30, TipoMoto.HIBRIDA);
        parqueadero.agregar(motoHibrida);
        motoHibrida.setFechaSalida(LocalDateTime.of(2024, 1, 22, 9, 0)); // 2 horas

        Vehiculo motoClasica = new Moto("LMN789", "Suzuki", "María", LocalDateTime.of(2024, 1, 22, 7, 0), 30, TipoMoto.CLASICA);
        parqueadero.agregar(motoClasica);
        motoClasica.setFechaSalida(LocalDateTime.of(2024, 1, 22, 9, 0)); // 2 horas

        assertEquals(1000, parqueadero.calcularCosto(carro));
        assertEquals(2000, parqueadero.calcularCosto(motoHibrida));
        assertEquals(1600, parqueadero.calcularCosto(motoClasica));

        LOG.info("Finalizando test para calcular costo");
    }

    /**
     * Prueba para verificar si un vehículo está registrado en el parqueadero.
     */
    @Test
    public void verificarVehiculoExisteTest() {
        Parqueadero parqueadero = new Parqueadero(2, 2, 1000, 800, 500);
        Vehiculo carro = new Carro("DEF789", "Chevrolet", "Maria", LocalDateTime.of(2024, 5, 18, 10, 0));

        parqueadero.agregar(carro);
        assertTrue(parqueadero.verificarVehiculoExiste(carro));

        Vehiculo moto = new Moto("GHI789", "Suzuki", "Carlos", LocalDateTime.of(2024, 5, 18, 10, 0), 30, TipoMoto.CLASICA);
        parqueadero.agregar(moto);
        assertTrue(parqueadero.verificarVehiculoExiste(moto));
    }

    /**
     * Prueba para agregar un vehículo cuando no hay espacio disponible en el parqueadero.
     */
    @Test
    public void agregarVehiculoSinEspacioTest() {
        Parqueadero parqueadero = new Parqueadero(1, 1, 1000, 800, 500); // Parqueadero con solo 1 espacio
        Vehiculo carro1 = new Carro("DEF789", "Chevrolet", "Maria", LocalDateTime.of(2024, 5, 18, 10, 0));
        Vehiculo carro2 = new Carro("XYZ456", "Toyota", "Carlos", LocalDateTime.of(2024, 5, 18, 11, 0));

        parqueadero.agregar(carro1);

        Exception exception = assertThrows(IllegalStateException.class, () -> parqueadero.agregar(carro2));
        assertEquals("No hay espacio disponible en el parqueadero", exception.getMessage());
    }
}
