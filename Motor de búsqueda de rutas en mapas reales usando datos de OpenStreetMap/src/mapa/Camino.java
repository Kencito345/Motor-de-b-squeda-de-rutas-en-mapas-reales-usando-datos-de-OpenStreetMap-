package mapa;

public class Camino {
    private NodoMapa origen;
    private NodoMapa destino;
    private double distancia;

    public Camino(NodoMapa origen, NodoMapa destino, double distancia) {
        this.origen = origen;
        this.destino = destino;
        this.distancia = distancia;
    }

    public NodoMapa getOrigen() { return origen; }
    public NodoMapa getDestino() { return destino; }
    public double getDistancia() { return distancia; }
}