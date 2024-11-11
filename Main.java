import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String rutaArchivo = "D:\\Knn-Kmeans\\Kmeans\\src\\Mall_Customers.csv";

        Dataset dataset = new Dataset();
        try {
            dataset.cargarCSV(rutaArchivo);
            KMeans kmeans = new KMeans(dataset);
            kmeans.ejecutar();
            kmeans.imprimirClusters();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
