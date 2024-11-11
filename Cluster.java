import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private Datos centroide;
    private List<Datos> puntos;

    public Cluster(Datos centroideInicial) {
        this.centroide = centroideInicial;
        this.puntos = new ArrayList<>();
    }


    public Datos obtenerCentroide() {
        return centroide;
    }


    // obtener puntos del cluster
    public List<Datos> obtenerPuntos() {
        return puntos;
    }

    public void agregarPunto(Datos punto) {
        puntos.add(punto);
    }

    // limpieza de cluster
    public void limpiarPuntos() {
        puntos.clear();
    }

    // actualizar cluster por media de los puntos
    public void actualizarCentroide() {
        if (puntos.isEmpty()) return;

        double sumaEdad = 0, sumaIngreso = 0, sumaPuntaje = 0;
        int cuentaMasculino = 0, cuentaFemenino = 0;

        for (Datos punto : puntos) {
            sumaEdad += punto.obtenerEdad();
            sumaIngreso += punto.obtenerIngresoAnual();
            sumaPuntaje += punto.obtenerPuntajeGasto();

            // clasificaciones
            if (punto.obtenerGenero().equalsIgnoreCase("Male")) {
                cuentaMasculino++;
            } else {
                cuentaFemenino++;
            }
        }

        // Calcular promedio
        int promedioEdad = (int) (sumaEdad / puntos.size());
        double promedioIngreso = sumaIngreso / puntos.size();
        double promedioPuntaje = sumaPuntaje / puntos.size();
        String generoPromedio = (cuentaMasculino > cuentaFemenino) ? "Male" : "Female";

        // Actualizar centroide
        this.centroide = new Datos(generoPromedio, promedioEdad, promedioIngreso, promedioPuntaje);
    }
}
