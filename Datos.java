public class Datos {
    private String genero;
    private int edad;
    private double ingresoAnual;
    private double puntajeGasto;

    public Datos(String genero, int edad, double ingresoAnual, double puntajeGasto) {
        this.genero = genero;
        this.edad = edad;
        this.ingresoAnual = ingresoAnual;
        this.puntajeGasto = puntajeGasto;
    }

    public String obtenerGenero() {
        return genero;
    }

    public int obtenerEdad() {
        return edad;
    }

    public double obtenerIngresoAnual() {
        return ingresoAnual;
    }

    public double obtenerPuntajeGasto() {
        return puntajeGasto;
    }


    @Override
    public String toString() {
        return "Datos{" +
                "genero='" + genero + '\'' +
                ", edad=" + edad +
                ", ingresoAnual=" + ingresoAnual +
                ", puntajeGasto=" + puntajeGasto +
                '}';
    }
}
