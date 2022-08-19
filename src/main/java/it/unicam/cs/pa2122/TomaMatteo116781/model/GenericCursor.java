package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Cursor;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Direction;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Plane;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;

import java.util.Objects;

/**
 * Classe che implementa un cursore, dandogli le coordinate X e Y dei punti in Double, e la direzione del cursore,
 * che &egrave; in un range dì 0-360. (gradi)
 */
public class GenericCursor implements Cursor<Point<Double>, GenericDirection> {

    private final Plane<Point<Double>> plane;
    private Point<Double> position;
    private GenericDirection direction;
    private RGBColor lineColor;
    private RGBColor areaColor;
    private boolean plot;
    private boolean pen;
    private int penSize;

    /**
     * Crea un cursore nel piano specificato, con le caratteristiche di default del piano da disegno: bianco e con
     * colore del tratto nero.
     *
     * @param plane il piano in cui si muover&agrave; il cursore.
     * @throws NullPointerException se il piano specificato &egrave; null.
     */
    public GenericCursor(Plane<Point<Double>> plane) {
        this(plane, plane.getHome(), Direction.defaultGenericDirection(), new RGBColor(0, 0, 0), new RGBColor(255, 255, 255));
    }

    /**
     * Crea un cursore nel piano specificato, con le caratteristiche del piano scelto, posizione, direzione, colore della linea
     * e il colore interno dell'area.
     *
     * @param plane     il piano in cui si muover&agrave; il cursore.
     * @param position  dove sar&agrave; posizionato all' inizio il cursore nel piano.
     * @param direction in quale direzione punter&agrave; all' inizio il cursore nel piano.
     * @param lineColor il colore della linea.
     * @param areaColor il colore interno dell' area.
     * @throws NullPointerException     se uno o pi&ugrave; uno dei parametri specificati &egrave; null.
     * @throws IllegalArgumentException se la posizione del cursore non &egrave; valida all' interno del piano specificato.
     */
    public GenericCursor(Plane<Point<Double>> plane, Point<Double> position, GenericDirection direction, RGBColor lineColor, RGBColor areaColor) {
        if (plane == null)
            throw new NullPointerException("Null plane specified!");
        if (position == null)
            throw new NullPointerException("Null position specified!");
        if (direction == null)
            throw new NullPointerException("Null direction specified!");
        if (lineColor == null)
            throw new NullPointerException("Null line color specified!");
        if (areaColor == null)
            throw new NullPointerException("Null fill area color specified!");
        this.plane = plane;
        if (!this.plane.isPartOfPlane(position))
            throw new IllegalArgumentException("Invalid cursor position in this plane!");
        this.position = position;
        this.direction = direction;
        this.lineColor = lineColor;
        this.areaColor = areaColor;
        this.plot = false;
        this.pen = true;
        this.penSize = 1;
    }

    /**
     * Crea un cursore, nel piano specificato, con le stesse caratteristiche del cursore specificato.
     *
     * @param plane  il piano che dovr&agrave; contenere il cursore specificato.
     * @param cursor il cursore da creare con le stesse caratteristiche del cursore specificato.
     */
    public GenericCursor(Plane<Point<Double>> plane, Cursor<Point<Double>, GenericDirection> cursor) {
        this.plane = plane;
        this.position = Point.cartesianPoint(cursor.getPosition().getX(), cursor.getPosition().getY());
        this.direction = Direction.genericDirection(cursor.getDirection().getDirectionWay());
        this.lineColor = new RGBColor(cursor.getLineColor().getRed(), cursor.getLineColor().getGreen(), cursor.getLineColor().getBlue());
        this.areaColor = new RGBColor(cursor.getAreaColor().getRed(), cursor.getAreaColor().getGreen(), cursor.getAreaColor().getBlue());
        this.plot = cursor.isPlot();
        this.pen = cursor.isPen();
        this.penSize = cursor.getPenSize();
    }

    @Override
    public Point<Double> getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Point<Double> position) {
        this.position = position;
    }

    @Override
    public GenericDirection getDirection() {
        return this.direction;
    }

    @Override
    public void setDirection(GenericDirection direction) {
        this.direction = direction;
    }

    @Override
    public RGBColor getLineColor() {
        return this.lineColor;
    }

    @Override
    public void setLineColor(RGBColor color) {
        this.lineColor = color;
    }

    @Override
    public RGBColor getAreaColor() {
        return this.areaColor;
    }

    @Override
    public void setAreaColor(RGBColor color) {
        this.areaColor = color;
    }

    @Override
    public boolean isPlot() {
        return this.plot;
    }

    @Override
    public void setPlot(boolean plot) {
        this.plot = plot;
    }

    @Override
    public Plane<Point<Double>> getPlane() {
        return this.plane;
    }

    @Override
    public boolean isPen() {
        return this.pen;
    }

    @Override
    public void penUp() {
        this.pen = false;
    }

    @Override
    public void penDown() {
        this.pen = true;
    }

    @Override
    public int getPenSize() {
        return this.penSize;
    }

    @Override
    public void setPenSize(int size) {
        if (size < 1)
            throw new IllegalArgumentException("Invalid pen size!");
        this.penSize = size;
    }

    /**
     * Restituisce la rappresentazione del cursore sotto forma di stringa, con le sue caratteristiche:
     * posizione, direzione, colore della linea, colore dell'area, disegno, penna, e spessore del tratto della penna.
     *
     * @return rappresentazione del cursore sotto forma di stringa.
     */
    @Override
    public String toString() {
        return "GenericCursor{" +
                "\nposition=" + position +
                "\ndirection=" + direction +
                "\nlineColor=" + lineColor +
                "\nareaColor=" + areaColor +
                "\nplot=" + plot +
                "\npen=" + pen +
                "\npenSize=" + penSize +
                '}';
    }

    /**
     * Verifica che due cursori siano uguali.
     *
     * @param o l' oggetto da verificarne l'uguaglianza.
     * @return true se l' oggetto &egrave; uguale all' oggetto passato, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericCursor that = (GenericCursor) o;
        return plot == that.plot && pen == that.pen && penSize == that.penSize && Objects.equals(plane, that.plane) && Objects.equals(position, that.position) && Objects.equals(direction, that.direction) && Objects.equals(lineColor, that.lineColor) && Objects.equals(areaColor, that.areaColor);
    }

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove il cursore &egrave; allocato con un intero univoco.
     *
     * @return l'hashcode calcolato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(plane, position, direction, lineColor, areaColor, plot, pen, penSize);
    }
}
