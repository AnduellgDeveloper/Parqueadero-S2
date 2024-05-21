package co.edu.uniquindio.poo;

import java.time.LocalDateTime;


public class App {
    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero(0, 0, 0, 0, 0);

        // Crear una moto y un carro
        Moto motoClasica = new Moto("APC56C", "Biwis 125", "Duvan", LocalDateTime.now().minusHours(1), LocalDateTime.now(), 150, TipoMoto.CLASICA);
        Carro carro = new Carro("JIR21G", "Chevrolet Activ", "Felipe", LocalDateTime.now().minusHours(2), LocalDateTime.now());

        parqueadero.registrarVehiculo(0, 0, motoClasica);
        parqueadero.registrarVehiculo(1, 1, carro);

    }
}
