package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.CartesianPoint;

/**
 * Rappresenta un generico punto nel piano.
 *
 * @param <N> il tipo parametrico per le coordinate numeriche del punto.
 */
public interface Point<N extends Number> {

    /**
     * Metodo che crea un punto cartesiano in base alle coordinate specificate.
     *
     * @param x   l' ascissa del punto da creare.
     * @param y   l' ordinata del punto da creare.
     * @param <N> il tipo parametrico per le coordinate del punto.
     * @return un nuovo punto cartesiano con le coordinate passate.
     */
    static <N extends Number> CartesianPoint cartesianPoint(N x, N y) {
        return new CartesianPoint(x.doubleValue(), y.doubleValue());
    }

    /**
     * Ritorna l' ascissa di un punto.
     *
     * @return l' ascissa di un punto.
     */
    N getX();

    /**
     * Ritorna l' ordinata di un punto.
     *
     * @return l' ordinata di un punto.
     */
    N getY();

    /**
     * Restituisce la rappresentazione del punto sotto forma di stringa, quindi le sue coordinate.
     *
     * @return rappresentazione del punto sotto forma di stringa.
     */
    @Override
    String toString();

    /**
     * Verifica che due punti siano uguali.
     *
     * @param o l' oggetto da verificarne l'uguaglianza.
     * @return true se l' oggetto &egrave; uguale all' oggetto passato, false altrimenti.
     */
    @Override
    boolean equals(Object o);

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove il punto &egrave; allocata con un intero univoco.
     *
     * @return l'hashcode calcolato.
     */
    @Override
    int hashCode();

}
