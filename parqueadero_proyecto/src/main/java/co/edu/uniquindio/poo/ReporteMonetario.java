package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReporteMonetario {

    private Parqueadero parqueadero;

    // Constructor que inicializa el reporte monetario con el parqueadero proporcionado
    public ReporteMonetario(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    /*................................. Registros Monetarios ............................. */

    // Método para registrar los ingresos diarios en el parqueadero
    public List<Double> registrarDineroDiario() {
        List<Double> ingresosDelDia = new ArrayList<>();
        List<List<Vehiculo>> registroVehiculo = parqueadero.getRegistroVehiculo();

        for (List<Vehiculo> fila : registroVehiculo) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null && vehiculo.getFechaSalida() != null) {
                    ingresosDelDia.add(parqueadero.calcularCosto(vehiculo));
                }
            }
        }
        return Collections.unmodifiableList(ingresosDelDia);
    }

    // Función auxiliar para registrar ingresos diarios (utilizada en pruebas)
    public static List<Double> registrarDineroDiario(Vehiculo[] vehiculos, Parqueadero parqueadero) {
        List<Double> ingresosDiarios = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            ingresosDiarios.add(parqueadero.calcularCosto(vehiculo));
        }
        return ingresosDiarios;
    }

    // Método para registrar los ingresos mensuales en el parqueadero
    public static List<Double> registrarDineroMensual(List<List<Double>> ingresosDiarios) {
        List<Double> ingresosMensuales = new ArrayList<>();
        for (List<Double> ingresosDia : ingresosDiarios) {
            ingresosMensuales.add(calcularDineroDiario(ingresosDia));
        }
        return ingresosMensuales;
    }

    // Método para registrar los ingresos mensuales en el parqueadero
    public List<Double> registrarDineroMensual(int mesActual, int anoActual) {
        List<Double> ingresosDelMes = new ArrayList<>();

        for (int dia = 1; dia <= Parqueadero.diasEnMes(mesActual, anoActual); dia++) {
            LocalDateTime fecha = LocalDateTime.of(anoActual, mesActual, dia, 0, 0);
            List<Double> costosPorDia = registrarDineroDiario();
            double totalDiario = calcularDineroDiario(costosPorDia);
            ingresosDelMes.add(totalDiario);
            System.out.println(fecha);
        }
        return Collections.unmodifiableList(ingresosDelMes);
    }

    /*................................  Calculos Monetarios ............................. */

    // Método para calcular el total de ingresos diarios
    public static double calcularDineroDiario(List<Double> ingresosDelDia) {
        double total = 0.0;
        for (double ingreso : ingresosDelDia) {
            total += ingreso;
        }
        return total;
    }

    // Método para calcular el total de ingresos mensuales
    public static double calcularDineroMensual(List<Double> ingresosMensuales) {
        return ingresosMensuales.stream().mapToDouble(Double::doubleValue).sum();
    }
}