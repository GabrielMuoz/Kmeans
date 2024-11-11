import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans {
    private List<Cluster> clusters;
    private Dataset dataset;
    private int numClusters;
    private final int MAX_ITERACIONES = 100;

    public KMeans(Dataset dataset) {
        this.dataset = dataset;
        this.clusters = new ArrayList<>();
        inicializarClusters();
    }

    // Inicializar los clusters
    private void inicializarClusters() {
        Random random = new Random();

        // Generar un numero aleatorio
        do {
            numClusters = random.nextInt(11) + 1;
        } while (numClusters % 2 == 0);

        // Crear clusters con centroides aleatorios
        for (int i = 0; i < numClusters; i++) {
            Datos centroideAleatorio = generarCentroideAleatorio();
            clusters.add(new Cluster(centroideAleatorio));
        }
    }

    // Genera un centroide aleatorio
    private Datos generarCentroideAleatorio() {
        Random random = new Random();

        String genero = random.nextBoolean() ? "Male" : "Female";
        int edad = 18 + random.nextInt(63);
        double ingresoAnual = 10 + (random.nextDouble() * 140);
        double puntajeGasto = random.nextDouble() * 100;

        return new Datos(genero, edad, ingresoAnual, puntajeGasto);
    }

    // metodo ejecutar
    public void ejecutar() {
        boolean centroidesCambiaron;
        int iteraciones = 0;

        do {
            System.out.println("Ronda " + (iteraciones + 1) + ":");
            asignarPuntosAClusters();
            centroidesCambiaron = actualizarCentroides();
            imprimirClusters();
            iteraciones++;
            System.out.println("=====================================");
        } while (centroidesCambiaron && iteraciones < MAX_ITERACIONES);

        System.out.println("Convergencia alcanzada en " + iteraciones + " rondas.");
    }

    // asigna cada punto a cluster
    private void asignarPuntosAClusters() {
        for (Cluster cluster : clusters) {
            cluster.limpiarPuntos();
        }

        for (Datos punto : dataset.obtenerDatos()) {
            Cluster clusterMasCercano = null;
            double distanciaMinima = Double.MAX_VALUE;

            for (Cluster cluster : clusters) {
                double distancia = calcularDistanciaEuclidiana(punto, cluster.obtenerCentroide());
                if (distancia < distanciaMinima) {
                    distanciaMinima = distancia;
                    clusterMasCercano = cluster;
                }
            }

            if (clusterMasCercano != null) {
                clusterMasCercano.agregarPunto(punto);
            }
        }
    }

    // Actualiza los centroides
    private boolean actualizarCentroides() {
        boolean centroidesCambiaron = false;
        final double UMBRAL_CONVERGENCIA = 0.0001;

        for (Cluster cluster : clusters) {
            Datos centroideAnterior = cluster.obtenerCentroide();
            cluster.actualizarCentroide();
            Datos nuevoCentroide = cluster.obtenerCentroide();

            // Calcular la diferencia
            double cambioCentroide = calcularDistanciaEuclidiana(centroideAnterior, nuevoCentroide);

            if (cambioCentroide > UMBRAL_CONVERGENCIA) {
                centroidesCambiaron = true;
            }
        }

        return centroidesCambiaron;
    }

    // Calcular la distancia entre dos puntos de datos
    private double calcularDistanciaEuclidiana(Datos punto1, Datos punto2) {
        double diferenciaEdad = punto1.obtenerEdad() - punto2.obtenerEdad();
        double diferenciaIngreso = punto1.obtenerIngresoAnual() - punto2.obtenerIngresoAnual();
        double diferenciaPuntaje = punto1.obtenerPuntajeGasto() - punto2.obtenerPuntajeGasto();

        return Math.sqrt(diferenciaEdad * diferenciaEdad + diferenciaIngreso * diferenciaIngreso + diferenciaPuntaje * diferenciaPuntaje);
    }

    // imprimir los clusters y sus puntos
    public void imprimirClusters() {
        int totalPuntos = dataset.obtenerDatos().size();
        for (int i = 0; i < clusters.size(); i++) {
            System.out.println("Cluster " + (i + 1) + ":");
            for (Datos punto : clusters.get(i).obtenerPuntos()) {
                //System.out.println(punto);
            }
            int puntosEnCluster = clusters.get(i).obtenerPuntos().size();
            double porcentaje = ((double) puntosEnCluster / totalPuntos) * 100;
            System.out.println("Centroide: " + clusters.get(i).obtenerCentroide()+ "(puntos: "+porcentaje+")"+"porcentaje: "+puntosEnCluster+"%");
            System.out.println("---------");


        }
    }

}
