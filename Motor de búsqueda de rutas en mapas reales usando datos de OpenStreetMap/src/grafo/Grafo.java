package grafo;

import mapa.NodoMapa;
import java.util.*;

public class Grafo {
    private Map<NodoMapa, List<Arista>> adyacencia = new HashMap<>();

    public void agregarNodo(NodoMapa nodo) {
        adyacencia.putIfAbsent(nodo, new ArrayList<>());
    }

    public void agregarArista(NodoMapa origen, NodoMapa destino, double peso, double factorTrafico) {
        adyacencia.get(origen).add(new Arista(destino, peso, factorTrafico));
    }

    public Map<NodoMapa, List<Arista>> getAdyacencia() { return adyacencia; }
}