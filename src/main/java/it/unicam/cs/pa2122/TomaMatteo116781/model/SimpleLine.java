package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Line;

import java.util.Objects;

/**
 * Classe necessaria a implementare una linea.
 * Serve per poter creare una linea ogni volta che si riposiziona il cursore.
 *
 * @param <C> il tipo parametrico per le coordinate del punto.
 */
public record SimpleLine<C>(C startingPoint, C endPoint, RGBColor color,
                            int size) implements Line<C> {

    /**
     * Crea una linea generica.
     *
     * @param startingPoint l' estremo iniziale.
     * @param endPoint      l' estremo finale.
     * @param color         il colore della linea.
     * @param size          lo spessore della linea.
     */
    public SimpleLine {
    }

    @Override
    public C getStartingPoint() {
        return this.startingPoint;
    }

    @Override
    public C getEndPoint() {
        return this.endPoint;
    }

    @Override
    public RGBColor getColor() {
        return color;
    }

    @Override
    public int getSize() {
        return this.size;
    }


    /**
     * Restituisce la rappresentazione della linea sotto forma di stringa con le sue caratteristiche.
     *
     * @return rappresentazione del punto sotto forma di stringa.
     */
    @Override
    public String toString() {
        return "L{ " + this.startingPoint + " , " + this.endPoint + " color = " + this.color + " } ";
    }

    /**
     * Verifica che due linee siano uguali.
     *
     * @param o l' oggetto da verificarne l'uguaglianza.
     * @return true se l' oggetto &egrave; uguale all' oggetto passato, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleLine<?> line = (SimpleLine<?>) o;
        return size == line.size
                && Objects.equals(startingPoint, line.startingPoint)
                && Objects.equals(endPoint, line.endPoint)
                && Objects.equals(color, line.color);
    }

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove la linea &egrave; allocata con un intero univoco.
     *
     * @return l'hashcode calcolato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(startingPoint, endPoint, color, size);
    }
}
