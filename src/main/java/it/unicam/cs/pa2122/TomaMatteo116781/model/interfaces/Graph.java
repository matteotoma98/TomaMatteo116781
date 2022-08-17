package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.GraphNode;

import java.util.List;
import java.util.Map;

/** La seguente interfaccia serve per rappresentare i dati tramite dei grafi, per cercare quelli che formano un ciclo e rimuoverli.
 *
 *
 *
 * Rappresenta un grafo per controllare i dati adiacenti.
 * Se c'&egrave; un ciclo nel grafo, questo viene ritornato e rimosso dal grafo.
 *
 * @param <D> il tipo parametrico per i dati contenuti nodi.
 */
public interface Graph<D> {

    /**
     * Visita tutto il grafo tramite Depth-first Search per poter trovare uno o pi&ugrave; cicli.
     *
     * @param u il nodo "figlio".
     * @param p il nodo "padre".
     */
    void cycleDFS(GraphNode<Integer, D> u, GraphNode<Integer, D> p);

    /**
     * Aggiunge un arco(u, v) a questo grafo.
     *
     * @param u il nodo u dell'arco
     * @param v il nodo v dell'arco
     */
    void addArc(GraphNode<Integer, D> u, GraphNode<Integer, D> v);

    /**
     * Ritorna una lista e rimuove i nodi che formano un ciclo in un determinato grafo.
     * Se il grafo non contiene cicli, viene ritornata una lista vuota.
     *
     * @param u il nodo sorgente.
     * @param p il padre del nodo sorgente.
     * @return la lista di dati contenuti nei nodi che formano un ciclo nel grafo.
     */
    List<D> getCycle(GraphNode<Integer, D> u, GraphNode<Integer, D> p);

    /**
     * Resetta (cio&egrave; inizializza) il grafo.
     */
    void clear();

    /**
     * Ritorna la matrice delle adiacenze di questo grafo, cio&egrave; tutti i nodi adiacenti a ciascun nodo.
     *
     * @return la matrice di adiacenze di un grafo.
     */
    List<List<GraphNode<Integer, D>>> getMatrix();

    /**
     * Ritorna la lista di tutti i cicli presenti nel grafo.
     *
     * @return la lista dei cicli esistenti nel grafo.
     */
    List<List<Integer>> getCycles();

    /**
     * Ritorna la mappatura tra l' indice intero di un nodo e l'oggetto contenuto in quel nodo.
     *
     * @return la mappa dei nodi con i propri indici nel grafo.
     */
    Map<Integer, D> getNodes();

    /**
     * Ritorna il numero totale di archi presenti nel grafo.
     *
     * @return il numero di archi presenti nel grafo.
     */
    int getArcs();

    /**
     * Ritorna il numero del ciclo attuale nel grafo.
     *
     * @return il numero di ciclo corrente nel grafo.
     */
    int getCycleNumber();

    /**
     * Ritorna la lista degli indici di tutti i nodi padre all'interno del grafo.
     *
     * @return la lista degli indici di tutti i nodi padre all'interno del grafo.
     */
    List<Integer> getParents();

    /**
     * Restituisce, tramite una lista d'interi, gli indici dei nodi riguardanti un certo ciclo.
     *
     * @return la lista degli indici interi dei nodi riguardanti un determinato ciclo.
     */
    List<Integer> getMarkedNodes();

    /**
     * Restituisce la lista contenente gli indici dei nodi colorati.
     *
     * @return la lista degli indici dei nodi che sono colorati.
     */
    List<Integer> getColors();
}
