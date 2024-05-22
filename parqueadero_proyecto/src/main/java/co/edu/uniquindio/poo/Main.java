package co.edu.uniquindio.poo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0); // Inicializar con valores por defecto
        Registro registro = new Registro();
        ReporteMonetario reporteMonetario = new ReporteMonetario(parqueadero);

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Configurar Parqueadero");
            System.out.println("2. Registrar Vehículo");
            System.out.println("3. Listar Vehículos");
            System.out.println("4. Calcular Costo");
            System.out.println("5. Generar Reporte Diario");
            System.out.println("6. Generar Reporte Mensual");
            System.out.println("7. Salir");
            
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    parqueadero.ParqueaderoInput();
                    break;
                case 2:
                    registrarVehiculo(parqueadero, registro, scanner);
                    break;
                case 3:
                    listarVehiculos(parqueadero);
                    break;
                case 4:
                    calcularCosto(parqueadero, registro, scanner);
                    break;
                case 5:
                    generarReporteDiario(reporteMonetario);
                    break;
                case 6:
                    generarReporteMensual(reporteMonetario, scanner);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void registrarVehiculo(Parqueadero parqueadero, Registro registro, Scanner scanner) {
        System.out.println("Ingrese el tipo de vehículo (1: Carro, 2: Moto): ");
        int tipoVehiculo = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese la placa del vehículo: ");
        String placa = scanner.nextLine();
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

    private static void listarVehiculos(Parqueadero parqueadero) {
        parqueadero.listarVehiculos();
    }

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

    private static void generarReporteDiario(ReporteMonetario reporteMonetario) {
        List<Double> ingresosDiarios = reporteMonetario.registrarDineroDiario();
        double totalDiario = ReporteMonetario.calcularDineroDiario(ingresosDiarios);
        System.out.println("Ingresos del día: " + ingresosDiarios);
        System.out.println("Total de ingresos del día: " + totalDiario);
    }

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
