package algoritmo;

import grafo.Grafo;
import mapa.NodoMapa;
import java.util.List;

public abstract class BuscadorRuta {
    protected Grafo grafo;

    public BuscadorRuta(Grafo grafo) { this.grafo = grafo; }

    // Nuevo: Considera el medio de transporte y si se debe evitar el tráfico
    public abstract List<NodoMapa> buscarRuta(NodoMapa origen, NodoMapa destino, String transporte, boolean usarTrafico);
}