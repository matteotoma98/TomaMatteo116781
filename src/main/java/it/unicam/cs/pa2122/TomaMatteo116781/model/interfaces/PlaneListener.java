package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;

/**
 * Descrive come far interagire la view e il model.
 *
 * @param <C> il tipo parametrico per le coordinate del punto nel piano.
 */
public interface PlaneListener<C> {

    /**
     * Metodo che indica che il cursore si &egrave; spostato in un punto specificato.
     *
     * @param point il punto in cui il cursore si &egrave; spostato.
     */
    void MovedCursor(C point);

    /**
     * Metodo che indica che &egrave; stata generata la linea specificata.
     *
     * @param line la linea generata dalle linee.
     */
    void GeneratedLine(Line<C> line);

    /**
     * Metodo che indica che &egrave; stata generata un' area chiusa.
     *
     * @param area l' area chiusa generata dalle linee.
     */
    void GeneratedArea(ClosedArea<Line<C>> area);

    /**
     * Metodo che indica che il colore del piano &egrave; cambiato.
     *
     * @param color il nuovo colore del piano.
     */
    void ScreenColor(RGBColor color);

    /**
     * Metodo che indica che &egrave; stato pulito tutto ci&ograve; che si vede a schermo.
     */
    void ScreenCleaned();

}
