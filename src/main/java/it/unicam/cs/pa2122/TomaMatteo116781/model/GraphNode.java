package it.unicam.cs.pa2122.TomaMatteo116781.model;

import java.util.Objects;

/**
 * Rappresenta un generico nodo di un grafo.
 *
 * @param <L> il tipo parametrico per i nomi dei nodi.
 * @param <D> il tipo parametrico per il dato nel nodo.
 */
public record GraphNode<L, D>(L name, D data) {
    /**
     * Colori da associare ai nodi
     */
    public static int COLOR_WHITE = 0;

    public static int COLOR_GREY = 1;

    public static int COLOR_BLACK = 2;

    /**
     * Crea un nodo con l' etichetta e l' oggetto da contenere specificati.
     *
     * @param name l' etichetta del nodo.
     * @param data il dato da contenere nel nodo.
     */
    public GraphNode {
    }

    /**
     * Restituisce il nome associato al nodo del grafo.
     *
     * @return il nome del nodo.
     */
    public L getName() {
        return this.name;
    }

    /**
     * Restituisce il dato contenuto nel nodo.
     *
     * @return il dato contenuto nel nodo.
     */
    public D getData() {
        return this.data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode<?, ?> graphNode = (GraphNode<?, ?>) o;
        return Objects.equals(name, graphNode.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "NODO [ " + name.toString() + " ]";
    }
}
