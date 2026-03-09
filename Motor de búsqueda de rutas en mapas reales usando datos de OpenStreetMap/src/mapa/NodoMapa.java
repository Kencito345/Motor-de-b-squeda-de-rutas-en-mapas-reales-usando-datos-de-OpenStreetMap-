package mapa;

public class NodoMapa {
    private long id;
    private double lat;
    private double lon;
    private String nombre; // Nuevo: Para geocodificación simulada

    public NodoMapa(long id, double lat, double lon, String nombre) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.nombre = nombre;
    }

    public long getId() { return id; }
    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public String getNombre() { return nombre; }
}