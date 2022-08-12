package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;

import java.util.Objects;

/**
 * Implementazione del punto cartesiano avente una coppia di coordinate in double, per aumentare la precisione.
 * Viene utilizzato l'identificatore Record visto che &egrave; una classe di dati immutabili che richiedono solo
 * il tipo e il nome dei campi.
 */

public record CartesianPoint(Double x, Double y) implements Point<Double> {

    /**
     * Creazione di un generico punto cartesiano nel piano.
     *
     * @param x l' ascissa del punto.
     * @param y l' ordinata del punto.
     */
    public CartesianPoint {
    }

    @Override
    public Double getX() {
        return x;
    }

    @Override
    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "POINT(" + getX() + "," + getY() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartesianPoint that = (CartesianPoint) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
