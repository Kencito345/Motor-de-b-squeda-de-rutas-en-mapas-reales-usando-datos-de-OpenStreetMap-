package servicio;
import mapa.NodoMapa;
import grafo.Grafo;
import java.util.*;
public class CargadorOSM {

    public List<NodoMapa> cargarNodos() {
        List<NodoMapa> nodos = new ArrayList<>();
        nodos.add(new NodoMapa(1, 10.48, -66.90, "Capitolio"));
        nodos.add(new NodoMapa(2, 10.47, -66.88, "La Hoyada"));
        nodos.add(new NodoMapa(3, 10.46, -66.86, "Bellas Artes"));
        nodos.add(new NodoMapa(4, 10.45, -66.84, "Plaza Venezuela"));
        nodos.add(new NodoMapa(5, 10.44, -66.82, "Sabana Grande"));
        return nodos;
    }
    public Grafo construirGrafo(List<NodoMapa> nodos) {
        Grafo g = new Grafo();
        for (NodoMapa n : nodos) { g.agregarNodo(n); }

        conectar(g, nodos.get(0), nodos.get(1), 2.5); 
        conectar(g, nodos.get(1), nodos.get(2), 1.0); 
        conectar(g, nodos.get(2), nodos.get(3), 3.0); 
        conectar(g, nodos.get(3), nodos.get(4), 1.2); 
        return g;
    }
    private void conectar(Grafo g, NodoMapa a, NodoMapa b, double trafico){
        double dx = a.getLat()-b.getLat();
        double dy = a.getLon()-b.getLon();
        double dist = Math.sqrt(dx*dx+dy*dy);
        g.agregarArista(a, b, dist, trafico);
        g.agregarArista(b, a, dist, trafico);
    }
}