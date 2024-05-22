package co.edu.uniquindio.poo;
import java.time.LocalDateTime;

// Clase para representar una Moto
class Moto extends Vehiculo {
    private int velocidadMaxima;
    private TipoMoto tipo;

    public Moto(String placa, String modelo, String propietario, LocalDateTime fechaEntrada, int velocidadMaxima, TipoMoto tipo) {
        super(placa, modelo, propietario, fechaEntrada);
        this.velocidadMaxima = velocidadMaxima;
        this.tipo = tipo;
    }
// Constructor de moto con fecha de salida
    public Moto(String placa, String modelo, String propietario, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, int velocidadMaxima, TipoMoto tipo) {
        super(placa, modelo, propietario, fechaEntrada, fechaSalida);
        this.velocidadMaxima = velocidadMaxima;
        this.tipo = tipo;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public TipoMoto getTipo() {
        return tipo;
    }

    
}
