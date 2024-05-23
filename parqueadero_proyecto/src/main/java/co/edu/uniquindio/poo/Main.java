package co.edu.uniquindio.poo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero(3, 3, 100, 100, 200);// Iniciar con valores por defecto
        Registro registro = new Registro();
        ReporteMonetario reporteMonetario = new ReporteMonetario(parqueadero);

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Configurar Parqueadero");
            System.out.println("2. Registrar Vehículo");
            System.out.println("3. Retirar Vehículo");
            System.out.println("4. Listar Vehículos");
            System.out.println("5. Calcular Costo");
            System.out.println("6. Generar Reporte Diario");
            System.out.println("7. Generar Reporte Mensual");
            System.out.println("8. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    parqueadero.ParqueaderoInput(); // Configurar parqueadero

                    break;
                case 2:
                    registrarVehiculo(parqueadero, registro, scanner); // Registrar vehículo
                    break;
                case 3:
                    retirarVehiculo(parqueadero, registro, scanner); // Retirar vehículo
                    break;    
                case 4:
                    listarVehiculos(parqueadero); // Listar vehiculos
                    break;
                case 5:
                    calcularCosto(parqueadero, registro, scanner); // Calcular costo
                    break;
                case 6:
                    generarReporteDiario(reporteMonetario); // Generar reporte diario
                    break;
                case 7:
                    generarReporteMensual(reporteMonetario, scanner); // Generar reporte mensual
                    break;
                case 8:
                    System.out.println("Saliendo..."); // Salir
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    // Método para registrar un nuevo vehículo
    private static void registrarVehiculo(Parqueadero parqueadero, Registro registro, Scanner scanner) {
        System.out.println("Ingrese el tipo de vehículo (1: Carro, 2: Moto): ");
        int tipoVehiculo = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese la placa del vehículo: ");
        String placa = scanner.nextLine();

        // Verificar si ya existe un vehículo con la misma placa
        List<Vehiculo> vehiculosRegistrados = registro.getVehiculosRegistrados();
        for (Vehiculo v : vehiculosRegistrados) {
            if (v.getPlaca().equals(placa)) {
                System.out.println("Error: Ya existe un vehículo registrado con la placa " + placa);
                return;
            }
        }

        System.out.print("Ingrese el modelo del vehículo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese el nombre del propietario del vehículo: ");
        String propietario = scanner.nextLine();

        Vehiculo vehiculo;
        if (tipoVehiculo == 1) {
            vehiculo = new Carro(placa, modelo, propietario, LocalDateTime.now());
        } else if (tipoVehiculo == 2) {
            System.out.println("Ingrese el tipo de moto (1: Clásica, 2: Híbrida): ");
            int tipoMoto = Integer.parseInt(scanner.nextLine());
            TipoMoto tipo = tipoMoto == 1 ? TipoMoto.CLASICA : TipoMoto.HIBRIDA;
            System.out.print("Ingrese la velocidad máxima de la moto: ");
            int velocidadMaxima = Integer.parseInt(scanner.nextLine());
            vehiculo = new Moto(placa, modelo, propietario, LocalDateTime.now(), velocidadMaxima, tipo);
        } else {
            System.out.println("Tipo de vehículo no válido.");
            return;
        }

        try {
            parqueadero.agregar(vehiculo);
            registro.registrarVehiculo(vehiculo);
            System.out.println("Vehículo registrado correctamente.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
    
// Método para retirar un vehículo del parqueadero
private static void retirarVehiculo(Parqueadero parqueadero, Registro registro, Scanner scanner) {
    System.out.print("Ingrese la placa del vehículo a retirar: ");
    String placa = scanner.nextLine();

    Vehiculo vehiculo = null;
    int fila = -1;
    int columna = -1;

    // Buscar el vehículo en el parqueadero
    for (int i = 0; i < parqueadero.getFilas(); i++) {
        for (int j = 0; j < parqueadero.getColumnas(); j++) {
            Vehiculo v = parqueadero.getRegistroVehiculo().get(i).get(j);
            if (v != null && v.getPlaca().equals(placa)) {
                vehiculo = v;
                fila = i;
                columna = j;
                break;
            }
        }
        if (vehiculo != null) break;
    }

    if (vehiculo == null) {
        System.out.println("Error: No se encontró ningún vehículo con la placa " + placa);
        return;
    }

    // Actualizar la fecha de salida del vehículo
    vehiculo.setFechaSalida(LocalDateTime.now());

    // Calcular el costo del estacionamiento
    double costo = parqueadero.calcularCosto(vehiculo);

    // Remover el vehículo del parqueadero
    parqueadero.getRegistroVehiculo().get(fila).set(columna, null);

    System.out.println("Vehículo con placa " + placa + " retirado del parqueadero.");
    System.out.println("Costo del estacionamiento: $" + costo);
}


    // Método para listar los vehículos estacionados
    private static void listarVehiculos(Parqueadero parqueadero) {
        parqueadero.listarVehiculos();
    }

    // Método para calcular el costo de estacionamiento
    private static void calcularCosto(Parqueadero parqueadero, Registro registro, Scanner scanner) {
        System.out.print("Ingrese la placa del vehículo: ");
        String placa = scanner.nextLine();

        Vehiculo vehiculo = registro.getVehiculosRegistrados().stream()
                .filter(v -> v.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);

        if (vehiculo == null) {
            System.out.println("Vehículo no encontrado.");
            return;
        }

        vehiculo.setFechaSalida(LocalDateTime.now());

        double costo = parqueadero.calcularCosto(vehiculo);
        System.out.println("El costo del estacionamiento para el vehículo con placa " + placa + " es: " + costo);
    }

    // Método para generar un informe diario de ingresos
    private static void generarReporteDiario(ReporteMonetario reporteMonetario) {
        List<Double> ingresosDiarios = reporteMonetario.registrarDineroDiario();
        double totalDiario = ReporteMonetario.calcularDineroDiario(ingresosDiarios);
        System.out.println("Ingresos del día: " + ingresosDiarios);
        System.out.println("Total de ingresos del día: " + totalDiario);
    }

    // Método para generar un informe mensual de ingresos
    private static void generarReporteMensual(ReporteMonetario reporteMonetario, Scanner scanner) {
        System.out.print("Ingrese el mes (1-12): ");
        int mes = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el año: ");
        int ano = Integer.parseInt(scanner.nextLine());

        List<Double> ingresosMensuales = reporteMonetario.registrarDineroMensual(mes, ano);
        double totalMensual = ReporteMonetario.calcularDineroMensual(ingresosMensuales);
        System.out.println("Ingresos del mes: " + ingresosMensuales);
        System.out.println("Total de ingresos del mes: " + totalMensual);
    }
}
