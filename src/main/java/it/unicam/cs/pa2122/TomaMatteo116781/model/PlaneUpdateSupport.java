package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.ClosedArea;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Line;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.PlaneUpdateListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe con metodi per collegare la view e gli elementi nel package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.
 * Quindi per far interagire il controller con gli elementi della vista.
 *
 * @param <C> il tipo parametrico delle coordinate del punto nel piano.
 */
public class PlaneUpdateSupport<C> {

    List<PlaneUpdateListener<C>> listeners;

    /**
     * Metodo per gestire i cambiamenti delle caratteristiche del piano.
     */
    public PlaneUpdateSupport() {
        this.listeners = new ArrayList<>();
    }

    /**
     * Aggiunge il listener per effettuare i cambiamenti al piano.
     *
     * @param listener il listener da aggiungere.
     */
    public synchronized void addListener(PlaneUpdateListener<C> listener) {
        if (listener == null) return;
        this.listeners.add(listener);
    }

    /**
     * Rimuove il listener per effettuare i cambiamenti al piano.
     *
     * @param listener il listener da rimuovere.
     */
    public synchronized void removeListener(PlaneUpdateListener<C> listener) {
        if (listener == null) return;
        this.listeners.remove(listener);
    }

    /**
     * Notifica tutti i listener quando il cursore si &egrave; spostato in un punto specificato.
     *
     * @param point il generico in cui il cursore si &egrave; spostato.
     */
    public synchronized void MovedCursor(C point) {
        listeners.forEach(p -> p.MovedCursor(point));
    }

    /**
     * Notifica tutti i listener quando &egrave; stata generata la linea specificata.
     *
     * @param line la generica linea generata.
     */
    public synchronized void GeneratedLine(Line<C> line) {
        listeners.forEach(l -> l.GeneratedLine(line));
    }

    /**
     * Notifica tutti i listener quando &egrave; stata generata l' area specificata.
     *
     * @param area la generica area generata.
     */
    public synchronized void GeneratedArea(ClosedArea<Line<C>> area) {
        listeners.forEach(a -> a.GeneratedArea(area));
    }

    /**
     * Notifica tutti i listener quando cambiato il colore del piano &egrave; cambiato.
     *
     * @param color il nuovo generico colore del piano.
     */
    public synchronized void ScreenColorChanged(RGBColor color) {
        listeners.forEach(s -> s.ScreenColor(color));
    }

    /**
     * Metodo per notificare quando &egrave; stato pulito lo schermo.
     */
    public synchronized void ScreenCleaned() {
        listeners.forEach(PlaneUpdateListener::ScreenCleaned);
    }
}
