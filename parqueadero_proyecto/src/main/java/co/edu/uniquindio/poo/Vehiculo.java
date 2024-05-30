package co.edu.uniquindio.poo;

import java.time.LocalDateTime;


public class Vehiculo {
    private String placa;
    private String modelo;
    private String propietario;
    private LocalDateTime fechaEntrada;
    private LocalDateTime fechaSalida;

    /*................................ Constructores ............................. */
    public Vehiculo(String placa, String modelo, String propietario, LocalDateTime fechaEntrada) {
        this.placa = placa;
        this.modelo = modelo;
        this.propietario = propietario;
        this.fechaEntrada = fechaEntrada;
    }

    // Constructor con fecha de salida
    public Vehiculo(String placa, String modelo, String propietario, LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
        this(placa, modelo, propietario, fechaEntrada);
        this.fechaSalida = fechaSalida;
    }

    // Setter fecha salida 
    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /*................................ Getters ............................. */
    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPropietario() {
        return propietario;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

}