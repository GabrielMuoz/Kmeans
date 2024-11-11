import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private List<Datos> datos;

    public Dataset() {
        this.datos = new ArrayList<>();
    }

    // Obtener lista de datos
    public List<Datos> obtenerDatos() {
        return datos;
    }

    // leer datos
    public void cargarCSV(String rutaArchivo) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");

                String genero = valores[1];
                int edad = Integer.parseInt(valores[2].trim());
                double ingresoAnual = Double.parseDouble(valores[3].trim());
                double puntajeGasto = Double.parseDouble(valores[4].trim());

                // crear objetos y llenao
                Datos dato = new Datos(genero, edad, ingresoAnual, puntajeGasto);
                datos.add(dato);
            }
        }
    }
}
