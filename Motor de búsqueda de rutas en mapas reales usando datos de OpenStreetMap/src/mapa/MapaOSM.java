package mapa;

import java.util.ArrayList;
import java.util.List;

public class MapaOSM {
    private List<NodoMapa> nodos = new ArrayList<>();
    private List<Camino> caminos = new ArrayList<>();

    public void agregarNodo(NodoMapa nodo) { nodos.add(nodo); }
    public void agregarCamino(Camino camino) { caminos.add(camino); }
    public List<NodoMapa> getNodos() { return nodos; }
    public List<Camino> getCaminos() { return caminos; }
}