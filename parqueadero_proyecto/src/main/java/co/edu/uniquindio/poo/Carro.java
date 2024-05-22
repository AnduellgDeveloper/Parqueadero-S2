package co.edu.uniquindio.poo;
import java.time.LocalDateTime;

// Clase para representar un Carro
class Carro extends Vehiculo {
    public Carro(String placa, String modelo, String propietario, LocalDateTime fechaEntrada) {
        super(placa, modelo, propietario, fechaEntrada);
    }
//Constructor con fecha de salida
    public Carro(String placa, String modelo, String propietario, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        super(placa, modelo, propietario, fechaEntrada, fechaSalida);
    }
    
    
}