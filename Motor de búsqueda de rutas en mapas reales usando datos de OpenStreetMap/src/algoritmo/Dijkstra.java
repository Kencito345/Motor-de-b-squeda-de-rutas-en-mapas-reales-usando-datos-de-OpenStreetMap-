package algoritmo;

import grafo.*;
import mapa.NodoMapa;
import java.util.*;

public class Dijkstra extends BuscadorRuta {

    public Dijkstra(Grafo grafo) { super(grafo); }

    @Override
    public List<NodoMapa> buscarRuta(NodoMapa origen, NodoMapa destino, String transporte, boolean usarTrafico) {
        Map<NodoMapa, Double> dist = new HashMap<>();
        Map<NodoMapa, NodoMapa> prev = new HashMap<>();
        PriorityQueue<NodoMapa> cola = new PriorityQueue<>(Comparator.comparingDouble(dist::get));

        for (NodoMapa n : grafo.getAdyacencia().keySet()) {
            dist.put(n, Double.MAX_VALUE);
        }

        dist.put(origen, 0.0);
        cola.add(origen);

        while (!cola.isEmpty()) {
            NodoMapa actual = cola.poll();
            if (actual.equals(destino)) break;

            for (Arista arista : grafo.getAdyacencia().get(actual)) {
                NodoMapa vecino = arista.getDestino();
                
                // Algoritmo de rutas adaptativo
                double multiplicadorTransporte = transporte.equals("A pie") ? 0.8 : (transporte.equals("Bicicleta") ? 0.9 : 1.0);
                double multiplicadorTrafico = (usarTrafico && transporte.equals("Coche")) ? arista.getFactorTrafico() : 1.0;
                
                double costoArista = arista.getPeso() * multiplicadorTransporte * multiplicadorTrafico;
                double nuevaDist = dist.get(actual) + costoArista;

                if (nuevaDist < dist.get(vecino)) {
                    dist.put(vecino, nuevaDist);
                    prev.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }

        List<NodoMapa> ruta = new ArrayList<>();
        NodoMapa paso = destino;
        while (paso != null) {
            ruta.add(paso);
            paso = prev.get(paso);
        }
        Collections.reverse(ruta);
        
        // Si no hay ruta (el primer elemento no es el origen), devolver lista vacía
        if(ruta.size() > 0 && !ruta.get(0).equals(origen)) return new ArrayList<>();
        
        return ruta;
    }
}