package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.PlaneUpdateSupport;
import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;
import it.unicam.cs.pa2122.TomaMatteo116781.model.SimpleDirection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Rappresenta un generico piano bidimensionale.
 *
 * @param <C> il tipo parametrico che rappresenta le coordinate di un punto nel piano.
 */
public interface Plane<C> {

    /**
     * Metodo necessario a calcolare il punto d'intersezione di due linee.
     *
     * @param a la prima linea.
     * @param b la seconda linea.
     * @return il punto d'intersezione tra la linea a e la linea b se esiste, Optional.isEmpty() altrimenti.
     */
    static Optional<Point<Double>> intersect(Line<Point<Double>> a, Line<Point<Double>> b) {
        //si prendono le coordinate X e Y dei due estremi delle linee
        double x1 = a.getStartingPoint().getX(), y1 = a.getStartingPoint().getY(),
                x2 = a.getEndPoint().getX(), y2 = a.getEndPoint().getY(),
                x3 = b.getStartingPoint().getX(), y3 = b.getStartingPoint().getY(),
                x4 = b.getEndPoint().getX(), y4 = b.getEndPoint().getY();
        //si calcola il determinante
        double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (d == 0)
            return Optional.empty(); //se il determinante è nullo, l'intersezione è vuota
        double x = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
        double y = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;
        x = (x == -0.0) ? 0.0 : x; //se x è negativa la rende positiva
        y = (y == -0.0) ? 0.0 : y; //se y è negativa la rende positiva
        return Optional.of(Point.cartesianPoint(Math.round(x * 100.0) / 100.0, Math.round(y * 100.0) / 100.0));
    }

    /**
     * Ritorna la lunghezza del piano.
     *
     * @return la lunghezza del piano.
     */
    double getLength();

    /**
     * Ritorna l' altezza del piano.
     *
     * @return l' altezza del piano.
     */
    double getHeight();

    /**
     * Ritorna la coordinata di origine del piano, dove si intersecano gli assi.
     *
     * @return la coordinata di origine del piano.
     */
    C getOrigin();

    /**
     * Ritorna la coordinata del punto centrale del piano.
     *
     * @return la coordinata del punto centrale del piano.
     */
    C getHome();

    /**
     * Ritorna l' insieme delle linee presenti nel piano in modo FIFO (first-in-first-out).
     *
     * @return l' insieme delle linee presenti nel piano.
     */
    Queue<Line<C>> getLines();

    /**
     * Ritorna il numero totale delle linee nel piano.
     *
     * @return il numero totale delle linee nel piano.
     */
    int getNumLines();

    /**
     * Ritorna il colore dello sfondo del piano.
     *
     * @return il colore dello sfondo del piano.
     */
    RGBColor getBackgroundColor();

    /**
     * Metodo per impostare un certo colore RGB dello sfondo piano.
     *
     * @param backgroundColor il colore da impostare.
     */
    void setBackgroundColor(RGBColor backgroundColor);

    /**
     * Aggiunge una linea al piano.
     *
     * @param line la linea da aggiungere al piano.
     */
    void addLine(Line<C> line);

    /**
     * Ritorna il numero di punti appartenenti alle linee presenti nel piano.
     *
     * @return il numero di punti delle linee presenti nel piano.
     */
    int getNumPoints();

    /**
     * Ritorna il cursore che si trova attualmente nel piano.
     * SimpleDirection per la direzione del cursore (l'angolo).
     *
     * @return il cursore situato nel piano.
     */
    Cursor<C, SimpleDirection> getCursor();

    /**
     * Ritorna l' insieme di tutte le aree chiuse presenti nel piano in ordine FIFO.
     *
     * @return l' insieme di tutte le aree chiuse presenti nel piano.
     */
    Queue<ClosedArea<Line<C>>> getClosedAreas();

    /**
     * Ritorna il numero totale di aree chiuse nel piano.
     *
     * @return il numero totale di aree chiuse nel piano.
     */
    int getNumClosedAreas();

    /**
     * Verifica se un punto specificato appartiene o meno al piano.
     *
     * @param point il punto di cui controllare l' appartenenza al piano.
     * @return true se un punto specificato appartiene al piano, false altrimenti.
     */
    boolean isPartOfPlane(C point);

    /**
     * Ritorna le coordinate del punto sull' angolo del in basso a sinistra piano.
     *
     * @return il punto del piano in basso a sinistra.
     */
    C getDownLeftPoint();

    /**
     * Ritorna le coordinate del punto sull' angolo in basso a destra del piano.
     *
     * @return il punto del piano in basso a destra.
     */
    C getDownRightPoint();

    /**
     * Ritorna le coordinate del punto sull' angolo in alto a sinistra del piano.
     *
     * @return il punto del piano in alto a sinistra.
     */
    C getUpLeftPoint();

    /**
     * Ritorna le coordinate del punto sull'angolo in alto a destra del piano.
     *
     * @return il punto del piano in alto a destra.
     */
    C getUpRightPoint();

    /**
     * Ritorna la mappa con tutti i punti (e le loro caratteristiche).
     * Tipo integer per i punti.
     *
     * @return la mappa dei punti con le caratteristiche che li identificano.
     */
    Map<C, Integer> getPoints();

    /**
     * Restituisce il grafo dei punti nel piano.
     *
     * @return il grafo dei punti nel piano.
     */
    Graph<C> getGraph();

    /**
     * Permette di gestire i cambiamenti delle propriet&agrave; del piano.
     * C &egrave; il tipo parametrico per le coordinate del punto nel piano.
     */
    PlaneUpdateSupport<C> getPlaneUpdateSupport();

    /**
     * Ritorna la posizione attuale del cursore nel piano con le coordinate X e Y.
     *
     * @return la posizione attuale del cursore nel piano identificato dalle coordinate X e Y.
     */
    default C getCursorPosition() {
        return getCursor().getPosition();
    }

    /**
     * Ritorna l' insieme delle linee aventi come estremo un punto passato come parametro.
     *
     * @param point il punto per cui passano le linee.
     * @return l' insieme delle linee che passano per un determinato punto.
     */
    default Set<Line<C>> getLinesAt(C point) {
        Set<Line<C>> lines = new HashSet<>();
        if (isPartOfPlane(point)) {
            /* Prende tutte le linee e poi filtra solo quelle che hanno come punto d'inizio
            il punto passato come parametro e come punto di fine il punto passato come parametro.
            Tramite collect aggiunge man mano le linee al set lines */
            lines.addAll(getLines().
                    stream().
                    filter(l -> l.getStartingPoint().equals(point)
                            || l.getEndPoint().equals(point))
                    .collect(Collectors.toSet()));
        }
        return lines;
    }


    /**
     * Aggiunge il listener per gli aggiornamenti al piano.
     *
     * @param listener il listener da aggiungere al piano per gli aggiornamenti.
     */
    /**
     * Aggiunge il listener per gli aggiornamenti al piano.
     *
     * @param listener il listener da aggiungere al piano per gli aggiornamenti.
     */
    void addPlaneUpdateListener(PlaneListener<Point<Double>> listener);

    /**
     * Rimuove il listener dal piano.
     *
     * @param listener il listener da rimuovere dal piano.
     */
    void removePlaneUpdateListener(PlaneListener<Point<Double>> listener);

}
