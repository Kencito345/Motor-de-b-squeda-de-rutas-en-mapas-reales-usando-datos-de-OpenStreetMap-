package grafo;

import mapa.NodoMapa;

public class Arista {
    private NodoMapa destino;
    private double peso;
    private double factorTrafico; // Nuevo: Simula congestión (1.0 = normal, 2.0 = mucho tráfico)

    public Arista(NodoMapa destino, double peso, double factorTrafico) {
        this.destino = destino;
        this.peso = peso;
        this.factorTrafico = factorTrafico;
    }

    public NodoMapa getDestino() { return destino; }
    public double getPeso() { return peso; }
    public double getFactorTrafico() { return factorTrafico; }
}