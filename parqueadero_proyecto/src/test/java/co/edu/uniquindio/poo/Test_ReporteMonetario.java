package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

public class Test_ReporteMonetario {

    private static final Logger LOG = Logger.getLogger(Test_ReporteMonetario.class.getName());
    
    // Prueba simple para verificar si la prueba se ejecuta correctamente
    @Test
    public void shouldAnswerWithTrue() {
        LOG.info("Iniciado test shouldAnswerWithTrue");
        assertTrue(true);
        LOG.info("Finalizando test shouldAnswerWithTrue");
    }

    // Prueba para verificar el registro diario de ingresos con datos de vehículos
    @Test
    public void coleccionDiariaConDatos() {
        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0);
        LOG.info("Inicio prueba de coleccionDiariaConDatos");
        
        // Crear instancias de vehículos con datos de entrada y salida
        Vehiculo vehiculo1 = new Moto("123", "A", "Juan", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 80, TipoMoto.CLASICA);
        Vehiculo vehiculo2 = new Carro("345", "B", "Felipe", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));
        Vehiculo vehiculo3 = new Moto("567", "C", "Julian", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 100, TipoMoto.HIBRIDA);
        Vehiculo vehiculo4 = new Carro("789", "D", "Pepe", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));

        // Calcular costos de estacionamiento para cada vehículo
        double dinero1 = parqueadero.calcularCosto(vehiculo1);
        double dinero2 = parqueadero.calcularCosto(vehiculo2);
        double dinero3 = parqueadero.calcularCosto(vehiculo3);
        double dinero4 = parqueadero.calcularCosto(vehiculo4);

        // Lista esperada de ingresos diarios
        List<Double> vehiculosEsperados = List.of(dinero1, dinero2, dinero3, dinero4);
        Vehiculo[] vehiculos = {vehiculo1, vehiculo2, vehiculo3, vehiculo4};

        // Registrar ingresos diarios y comparar con la lista esperada
        List<Double> vehiculosLista = ReporteMonetario.registrarDineroDiario(vehiculos, parqueadero);

        assertEquals(vehiculosEsperados, vehiculosLista);

        LOG.info("Fin prueba de coleccionDiariaConDatos");
    }

    // Prueba para calcular el costo diario real y compararlo con el esperado
    @Test
    public void calcularCostoDiarioReal() {
        LOG.info("Inicio prueba de calcularCostoDiarioReal");

        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0);

        Vehiculo vehiculo1 = new Moto("111", "A", "Felipe", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 80, TipoMoto.HIBRIDA);
        Vehiculo vehiculo2 = new Carro("222", "B", "Duvan", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));
        Vehiculo vehiculo3 = new Moto("333", "C", "Ana", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 100, TipoMoto.CLASICA);
        Vehiculo vehiculo4 = new Carro("444", "D", "Andrea", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));

        // Registrar ingresos diarios para cada vehículo
        List<Vehiculo> vehiculos = List.of(vehiculo1, vehiculo2, vehiculo3, vehiculo4);
        List<Double> ingresosDiarios = ReporteMonetario.registrarDineroDiario(vehiculos.toArray(new Vehiculo[0]), parqueadero);

        // Calcular el costo esperado y real del día y comparar
        double costoEsperado = ingresosDiarios.stream().mapToDouble(Double::doubleValue).sum();
        double costoReal = ReporteMonetario.calcularDineroDiario(ingresosDiarios);

        assertEquals(costoEsperado, costoReal, 0);

        LOG.info("Fin prueba de calcularCostoDiarioReal");
    }

    // Prueba para verificar el registro mensual de ingresos con datos de vehículos
    @Test
    public void coleccionMensualConDatos() {
        LOG.info("Inicio prueba de coleccionMensualConDatos");

        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0);

        Vehiculo vehiculo1 = new Moto("111", "A", "Felipe", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 80, TipoMoto.HIBRIDA);
        Vehiculo vehiculo2 = new Carro("222", "B", "Duvan", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));
        Vehiculo vehiculo3 = new Moto("333", "C", "Ana", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 100, TipoMoto.CLASICA);
        Vehiculo vehiculo4 = new Carro("444", "D", "Andrea", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));

        // Registrar ingresos diarios para dos días diferentes
        List<Vehiculo> vehiculosDia1 = List.of(vehiculo1, vehiculo2);
        List<Vehiculo> vehiculosDia2 = List.of(vehiculo3, vehiculo4);

        List<Double> ingresosDia1 = ReporteMonetario.registrarDineroDiario(vehiculosDia1.toArray(new Vehiculo[0]), parqueadero);
        List<Double> ingresosDia2 = ReporteMonetario.registrarDineroDiario(vehiculosDia2.toArray(new Vehiculo[0]), parqueadero);

        // Lista de ingresos diarios para el mes
        List<List<Double>> ingresosDiarios = List.of(ingresosDia1, ingresosDia2);

        // Registrar ingresos mensuales y verificar el tamaño de la lista
        List<Double> ingresosMensuales = ReporteMonetario.registrarDineroMensual(ingresosDiarios);

        assertNotNull(ingresosMensuales);
        assertEquals(2, ingresosMensuales.size());

        LOG.info("Fin prueba de coleccionMensualConDatos");
    }

    // Prueba para calcular el costo mensual real y compararlo con el esperado
    @Test
    public void calcularCostoMensualReal() {
        LOG.info("Inicio prueba de calcularCostoMensualReal");

        // Crear una instancia de Parqueadero con parámetros iniciales en cero
        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0);

        
        Vehiculo vehiculo1 = new Moto("111", "A", "Felipe", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 80, TipoMoto.HIBRIDA);
        Vehiculo vehiculo2 = new Carro("222", "B", "Duvan", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));
        Vehiculo vehiculo3 = new Moto("333", "C", "Ana", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45), 100, TipoMoto.CLASICA);
        Vehiculo vehiculo4 = new Carro("444", "D", "Andrea", LocalDateTime.of(2024, 5, 14, 12, 45), LocalDateTime.of(2024, 5, 14, 17, 45));

        // Agrupar los vehículos por días
        List<Vehiculo> vehiculosDia1 = List.of(vehiculo1, vehiculo2);
        List<Vehiculo> vehiculosDia2 = List.of(vehiculo3, vehiculo4);

        // Calcular ingresos diarios para cada grupo de vehículos
        List<Double> ingresosDia1 = ReporteMonetario.registrarDineroDiario(vehiculosDia1.toArray(new Vehiculo[0]), parqueadero);
        List<Double> ingresosDia2 = ReporteMonetario.registrarDineroDiario(vehiculosDia2.toArray(new Vehiculo[0]), parqueadero);

        // Agrupar los ingresos diarios en una lista
        List<List<Double>> ingresosDiarios = List.of(ingresosDia1, ingresosDia2);

        // Calcular los ingresos mensuales a partir de los ingresos diarios
        List<Double> ingresosMensuales = ReporteMonetario.registrarDineroMensual(ingresosDiarios);

        // Calcular el costo esperado sumando todos los ingresos diarios
        double costoEsperado = ingresosMensuales.stream().mapToDouble(Double::doubleValue).sum();

        // Calcular el costo real utilizando el método a probar
        double costoReal = ReporteMonetario.calcularDineroMensual(ingresosMensuales);

        // Verificar que el costo esperado y el costo real son iguales
        assertEquals(costoEsperado, costoReal, 0);

        LOG.info("Fin prueba de calcularCostoMensualReal");
    }

    // Prueba para verificar la colección de ingresos diarios vacía
    @Test
    public void coleccionDiariaVacia() {
        LOG.info("Inicio prueba de coleccionDiariaVacia");
        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0);
        Vehiculo[] vehiculos = new Vehiculo[0];
        List<Double> vehiculosDiarios = ReporteMonetario.registrarDineroDiario(vehiculos, parqueadero);

        assertEquals(0, vehiculosDiarios.size());

        LOG.info("Fin prueba de coleccionDiariaVacia");
    }

    // Prueba para verificar la colección de ingresos mensuales vacía.
    @Test
    public void coleccionMensualVacia() {
        LOG.info("Inicio prueba de coleccionMensualVacia");

        List<List<Double>> ingresosDiariosVacios = List.of();
        List<Double> vehiculosMensuales = ReporteMonetario.registrarDineroMensual(ingresosDiariosVacios);

        assertEquals(0, vehiculosMensuales.size());

        LOG.info("Fin prueba de coleccionMensualVacia");
    }

}
