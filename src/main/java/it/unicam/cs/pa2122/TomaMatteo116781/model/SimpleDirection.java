package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Direction;

import java.util.Objects;

/**
 * Classe per implementare i metodi per la direzione del cursore, data dall'angolo e nell'intervallo [0,360]
 */

public class SimpleDirection implements Direction<Integer> {

    private int angle;

    /**
     * Zero indica la direzione del cursore in orizzontale e verso destra.
     */
    public SimpleDirection() {
        this(0);
    }

    /**
     * Crea una direzione in base all'angolo specificato.
     *
     * @param angle l' angolo specificato.
     */
    public SimpleDirection(int angle) {
        checkAngle(angle);
        this.angle = angle;
    }

    /**
     * Metodo vuoto per controllare che l'angolo della direzione del cursore sia nel range 0 - 360 gradi.
     *
     * @param angle l' angolo specificato.
     * @throws IllegalArgumentException se l' angolo da impostare &egrave; fuori dal range predefinito
     */
    private void checkAngle(int angle) {
        if (angle < 0 || angle > 360)
            throw new IllegalArgumentException("Angolo della direzione del cursore fuori dal range, impostarne una corretta.");
    }

    @Override
    public Integer getDirectionWay() {
        return this.angle;
    }

    /**
     * Imposta l' angolo in base a quello specificato.
     *
     * @param direction l' angolo da impostare.
     * @throws IllegalArgumentException se l' angolo da impostare &egrave; fuori dal range predefinito
     */
    @Override
    public void setDirectionWay(Integer direction) {
        checkAngle(direction);
        this.angle = direction;
    }

    /**
     * Restituisce la rappresentazione della direzione del cursore sotto forma di stringa, con l'angolo.
     *
     * @return rappresentazione del cursore sotto forma di stringa.
     */
    @Override
    public String toString() {
        return "" + this.angle;
    }

    /**
     * Verifica che due angoli siano uguali.
     *
     * @param o l' oggetto da verificarne l'uguaglianza.
     * @return true se l' oggetto &egrave; uguale all' oggetto passato, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleDirection that = (SimpleDirection) o;
        return angle == that.angle;
    }

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove la direzione &egrave; allocata con un intero univoco.
     *
     * @return l'hashcode calcolato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(angle);
    }
}
