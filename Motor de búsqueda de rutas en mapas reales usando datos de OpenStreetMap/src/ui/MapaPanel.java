package ui;

import org.openstreetmap.gui.jmapviewer.*;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapaPanel extends JPanel {
    private JMapViewer mapa;
    private Coordinate origen;
    private Coordinate destino;

    public MapaPanel() {
        setLayout(new java.awt.BorderLayout());
        mapa = new JMapViewer();
        mapa.setDisplayPosition(new Coordinate(10.48,-66.90), 12);
        add(mapa);

        mapa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ICoordinate ic = mapa.getPosition(e.getPoint());
                Coordinate c = new Coordinate(ic.getLat(),ic.getLon());

                if(origen==null){
                    origen=c;
                    mapa.addMapMarker(new MapMarkerDot("Origen",origen));
                }else if(destino==null){
                    destino=c;
                    mapa.addMapMarker(new MapMarkerDot("Destino",destino));
                }
            }
        });
    }

    public Coordinate getOrigen(){ return origen; }
    public Coordinate getDestino(){ return destino; }

    public void dibujarRuta(java.util.List<mapa.NodoMapa> ruta){
        java.util.List<Coordinate> coords = new java.util.ArrayList<>();
        for(mapa.NodoMapa n : ruta){
            coords.add(new Coordinate(n.getLat(), n.getLon()));
        }
        mapa.addMapPolygon(new MapPolygonImpl(coords));
    }

    public void limpiarMapa() {
        mapa.removeAllMapPolygons();
        mapa.removeAllMapMarkers();
        origen = null;
        destino = null;
    }
}