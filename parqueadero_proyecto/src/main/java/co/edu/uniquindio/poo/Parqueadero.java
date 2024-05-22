package co.edu.uniquindio.poo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Clase que representa un parqueadero.
public class Parqueadero {
    private int filas;
    private int columnas;
    private int tarifaMotoH;
    private int tarifaMotoC;
    private int tarifaCarro;
    private int mesActual;
    private int anoActual;
    private List<List<Vehiculo>> registroVehiculo;

    // Método para configurar el parqueadero mediante entrada de usuario.
    public void ParqueaderoInput() {
        LocalDate fechaActual = LocalDate.now();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de filas: ");
        this.filas = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el número de columnas: ");
        this.columnas = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese la tarifa por hora para una moto híbrida: ");
        this.tarifaMotoH = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese la tarifa por hora para una moto clásica: ");
        this.tarifaMotoC = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese la tarifa por hora para un carro: ");
        this.tarifaCarro = Integer.parseInt(scanner.nextLine());

        this.mesActual = fechaActual.getMonthValue();
        this.anoActual = fechaActual.getYear();

        registroVehiculo = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            List<Vehiculo> fila = new ArrayList<>(Collections.nCopies(columnas, null));
            registroVehiculo.add(fila);
        }

        int[][] matriz = crearMatriz(filas, columnas, scanner);
        imprimirMatriz(matriz);
    }

    // Constructor de la clase Parqueadero.
    public Parqueadero(int filas, int columnas, int tarifaMotoH, int tarifaMotoC, int tarifaCarro) {
        LocalDate fechaActual = LocalDate.now();

        this.filas = filas;
        this.columnas = columnas;
        this.tarifaMotoH = tarifaMotoH;
        this.tarifaMotoC = tarifaMotoC;
        this.tarifaCarro = tarifaCarro;
        this.mesActual = fechaActual.getMonthValue();
        this.anoActual = fechaActual.getYear();

        registroVehiculo = new ArrayList<>();
        for (int i = 0; i < filas; i++) {
            List<Vehiculo> fila = new ArrayList<>(Collections.nCopies(columnas, null));
            registroVehiculo.add(fila);
        }
    }
    
    // Método para crear una matriz de enteros.
    public static int[][] crearMatriz(int filas, int columnas, Scanner scanner) {
        int[][] matriz = new int[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print("Valor para la celda [" + i + "][" + j + "]: ");
                matriz[i][j] = scanner.nextInt();
            }
        }
        return matriz;
    }
    
    //Método para imprimir una matriz de enteros.
    public static void imprimirMatriz(int[][] matriz) {
        System.out.println("Matriz:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    // Método para obtener una lista de todos los vehículos.
    public List<Vehiculo> getVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (List<Vehiculo> fila : registroVehiculo) {
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null) {
                    vehiculos.add(vehiculo);
                }
            }
        }
        return vehiculos;
    }
    
    // Método para transformar las filas y columnas del parqueadero en una lista de colecciones de vehículos.
    public List<List<Collection<Vehiculo>>> ingresarFilasYColumnas() {
        List<List<Collection<Vehiculo>>> resultado = new ArrayList<>();
        for (List<Vehiculo> fila : registroVehiculo) {
            List<Collection<Vehiculo>> filaTransformada = new ArrayList<>();
            for (Vehiculo vehiculo : fila) {
                if (vehiculo != null) {
                    filaTransformada.add(Collections.singleton(vehiculo));
                } else {
                    filaTransformada.add(Collections.emptyList());
                }
            }
            resultado.add(filaTransformada);
        }
        return resultado;
    }
    
    // Método para verificar si un puesto en el parqueadero está ocupado.
    public boolean verificarPuestoOcupado(int fila, int columna) {
        return registroVehiculo.get(fila).get(columna) != null;
    }
    
    // Método para verificar si un vehículo ya existe en el parqueadero.
    public boolean verificarVehiculoExiste(Vehiculo vehiculo) {
        for (List<Vehiculo> fila : registroVehiculo) {
            for (Vehiculo v : fila) {
                if (v != null && v.getPlaca().equals(vehiculo.getPlaca())) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Método para agregar un vehículo al parqueadero.
    public void agregar(Vehiculo vehiculo) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (registroVehiculo.get(i).get(j) == null) {
                    registroVehiculo.get(i).set(j, vehiculo);
                    return;
                }
            }
        }
        throw new IllegalStateException("No hay espacio disponible en el parqueadero");
    }
    
    // Método para registrar un vehículo en una posición específica del parqueadero.
    public void registrarVehiculo(int fila, int columna, Vehiculo vehiculo) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas || verificarPuesto(fila, columna) || verificarVehiculoExiste(vehiculo)) {
            System.out.println("Posición inválida/Puesto ocupado/Vehículo ya existe.");
        } else {
            registroVehiculo.get(fila).set(columna, vehiculo);
        }
    }
    
    // Método para listar todos los vehículos en el parqueadero.
    public void listarVehiculos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Vehiculo vehiculo = registroVehiculo.get(i).get(j);
                if (vehiculo != null) {
                    System.out.println("Vehículo en [" + i + "][" + j + "]: " + vehiculo.getPlaca());
                } else {
                    System.out.println("Espacio en [" + i + "][" + j + "]: vacío");
                }
            }
        }
    }
    
    // Método para obtener la diferencia de horas entre la fecha de entrada y la fecha de salida de un vehículo.
    public long obtenerDiferenciaHoras(Vehiculo vehiculo) {
        Duration duracion = Duration.between(vehiculo.getFechaEntrada(), vehiculo.getFechaSalida());
        return duracion.toHours();
    }

    // Método para calcular el costo de estacionamiento para un vehículo.
    public double calcularCosto(Vehiculo vehiculo) {
        if (vehiculo instanceof Carro) {
            return obtenerDiferenciaHoras(vehiculo) * tarifaCarro;
        } else if (vehiculo instanceof Moto) {
            Moto moto = (Moto) vehiculo;
            if (moto.getTipo() == TipoMoto.CLASICA) {
                return obtenerDiferenciaHoras(moto) * tarifaMotoC;
            } else {
                return obtenerDiferenciaHoras(moto) * tarifaMotoH;
            }
        } else {
            throw new IllegalArgumentException("Tipo de vehículo no soportado");
        }
    }

    // Método para obtener el número de días en un mes específico.
    public static int diasEnMes(int mesActual, int anoActual) {
        return YearMonth.of(anoActual, mesActual).lengthOfMonth();
    }
    
    // Método para verificar si un puesto en el parqueadero está dentro del rango válido.
    public boolean verificarPuesto(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            throw new IndexOutOfBoundsException("Posición fuera de rango");
        }
        return registroVehiculo.get(fila).get(columna) != null;
    }



    /*................................ Getters y Setters  ................................... */
    public int getFilas() {
        return filas;
    }

    public int getMesActual() {
        return mesActual;
    }

    public int getAnoActual() {
        return anoActual;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getTarifaMotoH() {
        return tarifaMotoH;
    }

    public int getTarifaMotoC() {
        return tarifaMotoC;
    }

    public int getTarifaCarro() {
        return tarifaCarro;
    }

    public List<List<Vehiculo>> getRegistroVehiculo() {
        return registroVehiculo;
    }
}
