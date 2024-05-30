package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

// Pruebas para la clase Carro.

public class Test_Carro {

    // Prueba la creación de un carro con valores iniciales.
    
    @Test
    public void crearCarro() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        Carro carro = new Carro("JFX577", "Gixxer 250", "Andres", fechaEntrada);

        assertEquals("JFX577", carro.getPlaca());
        assertEquals("Gixxer 250", carro.getModelo());
        assertEquals("Andres", carro.getPropietario());
        assertEquals(fechaEntrada, carro.getFechaEntrada());
        assertNull(carro.getFechaSalida());
    }

    //Prueba la creación de un carro con fecha de entrada y salida.
    
    @Test
    public void crearCarroConFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        LocalDateTime fechaSalida = LocalDateTime.of(2024, 6, 20, 14, 0);
        Carro carro = new Carro("LMN456", "Mazda", "Andrea", fechaEntrada, fechaSalida);

        assertEquals("LMN456", carro.getPlaca());
        assertEquals("Mazda", carro.getModelo());
        assertEquals("Andrea", carro.getPropietario());
        assertEquals(fechaEntrada, carro.getFechaEntrada());
        assertEquals(fechaSalida, carro.getFechaSalida());
    }

    //Prueba la creación de un carro solo con fecha de entrada.
    
    @Test
    public void crearCarroConFechaEntrada() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        Carro carro = new Carro("PQR123", "Ford", "Luis", fechaEntrada);

        assertEquals("PQR123", carro.getPlaca());
        assertEquals("Ford", carro.getModelo());
        assertEquals("Luis", carro.getPropietario());
        assertEquals(fechaEntrada, carro.getFechaEntrada());
    }

    
    // Prueba el establecimiento de la fecha de salida de un carro.
     
    @Test
    public void establecerFechaSalidaCarro() {
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 6, 20, 9, 0);
        Carro carro = new Carro("JYT786", "Renault", "Faber", fechaEntrada);

        LocalDateTime fechaSalida = LocalDateTime.of(2024, 6, 20, 17, 0);
        carro.setFechaSalida(fechaSalida);

        assertEquals(fechaSalida, carro.getFechaSalida());
    }
}
