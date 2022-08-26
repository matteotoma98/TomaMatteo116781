package it.unicam.cs.pa2122.TomaMatteo116781.model;

import java.util.Objects;

/**
 * Rappresenta un generico nodo di un grafo.
 *
 * @param <L> il tipo parametrico per i nomi dei nodi.
 * @param <D> il tipo parametrico per il dato nel nodo.
 */
public class GraphNode<L, D> {
    /**
     * Colori da associare ai nodi
     */
    public final static int COLOR_WHITE = 0;

    public final static int COLOR_GREY = 1;

    public final static int COLOR_BLACK = 2;

    private final L name;

    private final D data;

    /**
     * Crea un nodo con l' etichetta e l' oggetto da contenere specificati.
     *
     * @param name l' etichetta del nodo.
     * @param data il dato da contenere nel nodo.
     */
    public GraphNode(L name, D data) {
        this.name = name;
        this.data = data;
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
