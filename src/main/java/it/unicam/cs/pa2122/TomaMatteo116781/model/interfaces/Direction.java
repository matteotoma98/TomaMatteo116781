package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.SimpleDirection;

/**
 * Interfaccia che indica dove l'oggetto puograve; essere direzionato.
 * Cio&egrave; la direzione da seguire per una certa classe che implementa
 */
public interface Direction<D> {
    /**
     * Metodo che crea un oggetto con le propriet&agrave; della direzione nel piano di disegno
     *
     * @return la direzione di default nel piano di disegno.
     */
    static SimpleDirection defaultSimpleDirection() {
        return new SimpleDirection();
    }

    /**
     * Metodo per creare un oggetto dandogli una direzione tramite l'angolo
     *
     * @param angle l' angolo verso cui punta la direzione.
     * @return la direzione con l' angolo
     */
    static SimpleDirection simpleDirection(int angle) {
        return new SimpleDirection(angle);
    }

    /**
     * Restituisce la direzione attuale.
     *
     * @return la direzione verso cui l' oggetto sta puntando.
     */
    D getDirectionWay();

    /**
     * Imposta la direzione specificata.
     *
     * @param direction la direzione che si vuole impostare.
     * @throws IllegalArgumentException se l' angolo da impostare &egrave; fuori dal range previsto.
     */
    void setDirectionWay(D direction);

    /**
     * Restituisce la rappresentazione della direzione sotto forma di stringa.
     *
     * @return rappresentazione della direzione sotto forma di stringa.
     */

    @Override
    String toString();

    /**
     * Compara l' oggetto specificato con la direzione, serve per verificare che sia corretto.
     *
     * @param o &egrave; l' oggetto da comparare con la direzione per verificare l' uguaglianza.
     * @return true se l' oggetto specificato &egrave; uguale alla direzione, false altrimenti.
     */
    @Override
    boolean equals(Object o);

    /**
     * Restituisce l' hashcode per mappare l’indirizzo dell’area di memoria dove la direzione &egrave; allocata con un intero univoco.
     *
     * @return il valore hash code per la direzione
     */
    @Override
    int hashCode();
}
