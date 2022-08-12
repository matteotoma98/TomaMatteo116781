package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;


import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;

import java.util.List;

/**
 * Rappresenta un'area chiusa dalle linee.
 *
 * @param <L> &egrave; il tipo parametrico che rappresenta le linee che formano l' area chiusa.
 */
public interface ClosedArea<L> {

    /**
     * Restituisce una lista di linee che formano l' area chiusa.
     *
     * @return una lista di linee che formano l' area chiusa.
     */
    List<L> getArea();

    /**
     * Restituisce il colore RGB dell' area chiusa.
     *
     * @return il colore RGB dell' area chiusa.
     */
    RGBColor getColor();

    /**
     * Restituisce la rappresentazione dell' area sotto forma di stringa, composta dalle linee
     *
     * @return l' area come una stringa di linee che la compongono.
     **/
    @Override
    String toString();

    /**
     * Compara l' oggetto specificato con questa area chiusa, serve per verificare che sia corretta.
     *
     * @param o &egrave; l' oggetto da comparare con l' area chiusa per l' uguaglianza.
     * @return true se l' oggetto specificato &egrave; uguale all'area chiusa, false altrimenti.
     */
    @Override
    boolean equals(Object o);

    /**
     * Restituisce l' hashcode per mappare l’indirizzo dell’area di memoria dove l'area &egrave; allocata con un intero univoco.
     *
     * @return il valore hash code dell' area chiusa.
     */
    @Override
    int hashCode();

}
