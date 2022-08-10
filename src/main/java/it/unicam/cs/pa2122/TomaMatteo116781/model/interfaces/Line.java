package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;

/**
 * Rappresenta una generica linea per disegnare sul piano.
 *
 * @param <C> il tipo parametrico delle coordinate del punto della linea nel piano.
 */
public interface Line<C> {

    /**
     * Ritorna le coordinate dell'estremo iniziale della linea.
     *
     * @return le coordinate dell' estremo iniziale della linea.
     */
    C getStartingPoint();

    /**
     * Ritorna le coordinate dell' estremo finale della linea.
     *
     * @return le coordinate dell' estremo finale della linea.
     */
    C getEndPoint();

    /**
     * Ritorna il colore associato alla linea.
     *
     * @return il colore della linea.
     */
    RGBColor getColor();

    /**
     * Ritorna lo spessore del tratto della linea.
     *
     * @return lo spessore del tratto della linea.
     */
    int getSize();

    /**
     * Ritorna la rappresentazione della linea come stringa.
     *
     * @return rappresentazione della linea come stringa.
     */
    @Override
    String toString();

    /**
     * Compara l' oggetto specificato con la linea per verificarne l' uguaglianza.
     *
     * @param o l' oggetto da comparare con la linea per l' uguaglianza.
     * @return true se l' oggetto specificato &egrave; uguale alla linea, false altrimenti.
     */
    @Override
    boolean equals(Object o);

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove la linea &egrave; allocata con un intero univoco.
     *
     * @return il valore hash code della linea.
     */
    @Override
    int hashCode();

}
