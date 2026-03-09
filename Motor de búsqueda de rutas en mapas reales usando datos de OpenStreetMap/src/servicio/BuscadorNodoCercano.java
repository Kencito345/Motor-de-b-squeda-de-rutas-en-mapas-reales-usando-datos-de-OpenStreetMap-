package servicio;

import mapa.NodoMapa;
import java.util.List;

public class BuscadorNodoCercano {

    public NodoMapa encontrarNodoMasCercano(double lat, double lon, List<NodoMapa> nodos) {
        NodoMapa mejor = null;
        double mejorDist = Double.MAX_VALUE;
        for (NodoMapa n : nodos) {
            double dx = lat - n.getLat();
            double dy = lon - n.getLon();
            double dist = Math.sqrt(dx*dx + dy*dy);
            if (dist < mejorDist) { mejorDist = dist; mejor = n; }
        }
        return mejor;
    }

    // Nuevo: Geocodificación simulada (Búsqueda por texto)
    public NodoMapa buscarPorNombre(String nombre, List<NodoMapa> nodos) {
        for (NodoMapa n : nodos) {
            if (n.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return n;
            }
        }
        return null;
    }
}