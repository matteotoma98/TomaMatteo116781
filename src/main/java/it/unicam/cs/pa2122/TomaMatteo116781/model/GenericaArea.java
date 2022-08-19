package it.unicam.cs.pa2122.TomaMatteo116781.model;


import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.ClosedArea;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Line;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;

import java.util.List;
import java.util.Objects;

/**
 * Classe per implementare un' area chiusa dell' interfaccia it.unicam.cs.2122.TomaMatteo116781.model.interfaces.ClosedArea.
 * La classe è parametrizzata da una linea, e da un punto di tipo Double per aumentare la precisione.
 * In questo modo, è possibile rappresentare una semplice area tramite linee chiuse, formate da coppie di punti rappresentati
 * attraverso Double per aumentare la precisione.
 */
public class GenericaArea implements ClosedArea<Line<Point<Double>>> {
    private final List<Line<Point<Double>>> lines;
    private final RGBColor color;

    /**
     * Crea un' area chiusa nel piano individuata da un insieme di linee e determinati colori RGB.
     *
     * @param lines l'insieme di linee, rappresentate tramite una lista di punti con coordinate double,
     *              che individuano l' area chiusa.
     * @param color il colore RGB da impostare all' area chiusa.
     */
    public GenericaArea(List<Line<Point<Double>>> lines, RGBColor color) {
        this.lines = lines;
        this.color = color;
    }

    @Override
    public List<Line<Point<Double>>> getArea() {
        return this.lines;
    }

    @Override
    public RGBColor getColor() {
        return this.color;
    }

    /**
     * Restituisce la rappresentazione dell'area sotto forma di stringa, quindi le linee che la formano e i loro colori.
     *
     * @return rappresentazione del punto sotto forma di stringa.
     */
    @Override
    public String toString() {
        StringBuilder lines = new StringBuilder();
        for (Line<Point<Double>> l : this.lines)
            lines.append("\n").append(l);
        return "AREA{ " +
                "lines= " + lines
                +
                "\n, color= " + color +
                " }";
    }

    /**
     * Restituisce la linee che formano l'area chiusa.
     *
     * @return l'insieme delle linee che formano l'area.
     */
    public List<Line<Point<Double>>> getLines() {
        return lines;
    }

    /**
     * Verifica che due aree siano uguali.
     *
     * @param o l' oggetto da verificarne l'uguaglianza.
     * @return true se l' oggetto &egrave; uguale all' oggetto passato, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericaArea that = (GenericaArea) o;
        return Objects.equals(lines, that.lines) && Objects.equals(color, that.color);
    }

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove l'area &egrave; allocata con un intero univoco.
     *
     * @return l'hashcode calcolato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(lines, color);
    }
}
