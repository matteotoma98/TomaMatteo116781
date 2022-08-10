package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;

/**
 * Rappresenta un generico cursore.
 * Gli attributi sono:
 * &#9679; Posizione: posizione del cursore
 * &#9679; Direzione: indica l’angolo verso cui &egrave; puntato il cursore. L’angolo viene rappresentato con un intero nell’intervallo [0,360] dove lo 0 indica la direzione orizzontale verso destra (ore 3, se consideriamo un orologio).
 * &#9679; Colore Linea: indica il colore della linea prodotta dal cursore come conseguenza di uno spostamento.
 * &#9679; Colore Area: indica il colore dell’area che viene colorata quando una serie di linee producono un’area chiusa.
 * &#9679; Plot: &egrave; un parametro booleano che sta ad indicare se durante uno spostamento il cursore genera o meno un tracciato.
 *
 * @param <C> rappresenta il tipo parametrico delle coordinate del punto nel piano.
 * @param <D> rappresenta il tipo parametrico per la direzione in cui punta il cursore nel piano.
 */
public interface Cursor<C, D extends Directional<?>> {

    /**
     * Restituisce le coordinate X e Y  della posizione del cursore.
     *
     * @return le coordinate della posizione del cursore.
     */
    C getPosition();

    /**
     * Imposta il cursore in una nuova posizione nel piano.
     *
     * @param position la nuova posizione del cursore nel piano.
     */
    void setPosition(C position);

    /**
     * Restituisce la direzione verso cui il cursore punta.
     *
     * @return la direzione verso cui il cursore punta.
     */
    D getDirection();

    /**
     * Imposta la direzione verso cui il cursore dovr&agrave; puntare.
     * Per default, il cursore &egrave; posizionato nella home, ha una direzione di gradi 0, il colore della linea &egrave; il nero,
     * mentre il colore dell’area &egrave; il bianco (come il colore di sfondo di default).
     *
     * @param direction la nuova direzione verso cui il cursore dovr&agrave; puntare
     */
    void setDirection(D direction);

    /**
     * Restituisce il colore della linea prodotta del cursore come conseguenza di uno spostamento.
     *
     * @return il colore della linea prodotta dal cursore.
     */
    RGBColor getLineColor();

    /**
     * Imposta il colore della linea prodotta dal cursore come conseguenza di uno spostamento.
     *
     * @param color il colore da impostare alla linea prodotta dal cursore.
     */
    void setLineColor(RGBColor color);

    /**
     * Restituisce il colore dell' area formata da una serie di linee.
     *
     * @return il colore dell' area formata da una serie di linee.
     */
    RGBColor getAreaColor();

    /**
     * Imposta il colore dell'area prodotta quando una serie di linee producono un'aria chiusa.
     *
     * @param color il colore da impostare all' area.
     */
    void setAreaColor(RGBColor color);

    /**
     * Questo parametro booleano indica se durante uno spostamento, il cursore genera o meno un tracciato.
     *
     * @return true se &egrave; stato generato un tracciato, false altrimenti.
     */
    boolean isPlot();

    /**
     * Imposta il plot a seconda della generazione di un tracciato.
     *
     * @param plot il plot da impostare.
     */
    void setPlot(boolean plot);

    /**
     * Restituisce il piano in cui &egrave; contenuto il cursore.
     *
     * @return il piano in cui &egrave; contenuto il cursore.
     */
    Plane<C> getPlane();

    /**
     * Restituisce true se la penna &egrave; attaccata al piano, false altrimenti.
     *
     * @return true se la penna &egrave; attaccata al piano, false altrimenti.
     */
    boolean isPen();

    /**
     * Imposta la penna attaccata al piano del cursore.
     */
    void penUp();

    /**
     * Imposta la penna staccata dal piano del cursore.
     */
    void penDown();

    /**
     * Restituisce la size del tratto della penna.
     *
     * @return un intero >=1 che rappresenta la size del tratto della penna.
     */
    int getPenSize();

    /**
     * Imposta la size alla penna.
     *
     * @param size la grandezza del tratto della penna.
     * @throws IllegalArgumentException se size &egrave; minore di 1.
     */
    void setPenSize(int size);

    /**
     * Restituisce la rappresentazione del cursore sotto forma di stringa.
     *
     * @return rappresentazione del cursore sotto forma di stringa.
     */
    @Override
    String toString();

    /**
     * Compara l' oggetto specificato con questo cursore per verificarne l' uguaglianza.
     *
     * @param o l' oggetto da comparare con questo cursore per l' uguaglianza.
     * @return true se l' oggetto specificato &egrave; uguale a questo cursore, false altrimenti.
     */
    @Override
    boolean equals(Object o);

    /**
     * Restituisce il valore hash code per questo cursore.
     *
     * @return il valore hash code per questo cursore.
     */
    @Override
    int hashCode();


}
