package ui;

import algoritmo.Dijkstra;
import grafo.Grafo;
import mapa.NodoMapa;
import servicio.BuscadorNodoCercano;
import servicio.CargadorOSM;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private JTextArea resultado;
    private MapaPanel mapaPanel;
    private List<NodoMapa> nodos;
    private Grafo grafo;
    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JComboBox<String> comboTransporte;
    private JCheckBox checkTrafico;

    public VentanaPrincipal(){
        setTitle("Enrutador Avanzado - OpenStreetMap");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mapaPanel = new MapaPanel();
        add(mapaPanel, BorderLayout.CENTER);

        JPanel panelControles = new JPanel(new FlowLayout());
        txtOrigen = new JTextField(10);
        txtDestino = new JTextField(10);
        comboTransporte = new JComboBox<>(new String[]{"Coche", "A pie", "Bicicleta"});
        checkTrafico = new JCheckBox("Evitar Tráfico");
        JButton buscar = new JButton("Buscar Ruta");
        JButton limpiar = new JButton("Limpiar Mapa");

        panelControles.add(new JLabel("Origen (Texto):"));
        panelControles.add(txtOrigen);
        panelControles.add(new JLabel("Destino:"));
        panelControles.add(txtDestino);
        panelControles.add(new JLabel("Transporte:"));
        panelControles.add(comboTransporte);
        panelControles.add(checkTrafico);
        panelControles.add(buscar);
        panelControles.add(limpiar);
        add(panelControles, BorderLayout.NORTH);

        resultado = new JTextArea(8, 20);
        resultado.setEditable(false);
        add(new JScrollPane(resultado), BorderLayout.SOUTH);

        CargadorOSM cargador = new CargadorOSM();
        nodos = cargador.cargarNodos();
        grafo = cargador.construirGrafo(nodos);

        buscar.addActionListener(e -> calcularRuta());
        limpiar.addActionListener(e -> {
            mapaPanel.limpiarMapa();
            resultado.setText("");
            txtOrigen.setText("");
            txtDestino.setText("");
        });
    }

    private void calcularRuta(){
        BuscadorNodoCercano buscador = new BuscadorNodoCercano();
        NodoMapa nodoOrigen = null;
        NodoMapa nodoDestino = null;

        if (!txtOrigen.getText().isEmpty() && !txtDestino.getText().isEmpty()) {
            nodoOrigen = buscador.buscarPorNombre(txtOrigen.getText(), nodos);
            nodoDestino = buscador.buscarPorNombre(txtDestino.getText(), nodos);
        } 
        else {
            Coordinate cOrigen = mapaPanel.getOrigen();
            Coordinate cDestino = mapaPanel.getDestino();
            if(cOrigen != null && cDestino != null){
                nodoOrigen = buscador.encontrarNodoMasCercano(cOrigen.getLat(), cOrigen.getLon(), nodos);
                nodoDestino = buscador.encontrarNodoMasCercano(cDestino.getLat(), cDestino.getLon(), nodos);
            }
        }

        if(nodoOrigen == null || nodoDestino == null){
            resultado.setText("Error: Seleccione en el mapa o escriba lugares válidos (ej. Capitolio, La Hoyada).");
            return;
        }

        String transporte = (String) comboTransporte.getSelectedItem();
        boolean considerarTrafico = checkTrafico.isSelected();

        Dijkstra dijkstra = new Dijkstra(grafo);
        List<NodoMapa> ruta = dijkstra.buscarRuta(nodoOrigen, nodoDestino, transporte, considerarTrafico);

        if (ruta.isEmpty()) {
            resultado.setText("No se encontró una ruta.");
            return;
        }

        mapaPanel.limpiarMapa(); 
        mapaPanel.dibujarRuta(ruta);

        resultado.setText("--- DIRECCIONES PASO A PASO (" + transporte + ") ---\n");
        resultado.append("Inicio en: " + ruta.get(0).getNombre() + "\n");
        
        for (int i = 0; i < ruta.size() - 1; i++) {
            NodoMapa actual = ruta.get(i);
            NodoMapa siguiente = ruta.get(i + 1);
            resultado.append("➡ Dirígete desde " + actual.getNombre() + " hacia " + siguiente.getNombre() + ".\n");
        }
        resultado.append("📍 ¡Has llegado a tu destino: " + ruta.get(ruta.size() - 1).getNombre() + "!");
    }
}