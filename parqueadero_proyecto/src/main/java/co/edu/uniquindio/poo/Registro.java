package co.edu.uniquindio.poo;
import java.util.ArrayList;
import java.util.List;

// Clase para el registro de vehículos en el parqueadero
public class Registro  {
    private final List<Vehiculo> vehiculosRegistrados;

    // Constructor de la clase Registro
    public Registro() {
        vehiculosRegistrados = new ArrayList<>();
    }

    // Método para registrar un vehículo en el parqueadero
    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculosRegistrados.add(vehiculo);
    }

    // Método para obtener la lista de vehículos registrados
    public List<Vehiculo> getVehiculosRegistrados() {
        return vehiculosRegistrados;
    }
}
