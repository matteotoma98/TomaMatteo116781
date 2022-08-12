package it.unicam.cs.pa2122.TomaMatteo116781.model;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Graph;

import java.util.*;

/**
 * Rappresenta un grafo non orientato (poich&egrave; ogni arco avr&agrave; una relazione biunivoca) tramite
 * la matrice delle adiacenze.
 * Si utilizza una mappa di nodi, composti da campi Integer e D (che sono i dati dei nodi).
 * - Integer contiene l'indice che viene assegnato al determinato grafo.
 * - arcs rappresenta gli archi tra i nodi, e la lista markedNodes i nodi che hanno altri nodi vicini.
 * - iterations rappresenta il numero d' iterazioni da fare
 *
 * @param <D> il tipo parametrico per il dato (l'oggetto) che il nodo contiene
 */
public final class UndirectedGraph<D> implements Graph<D> {

    final int iterations = 100000;
    List<List<GraphNode<Integer, D>>> matrix;
    Map<Integer, D> nodes;
    private List<List<Integer>> cycles;
    private int arcs;
    private int cycleNumber;
    private List<Integer> parents;
    private List<Integer> markedNodes;
    private List<Integer> colors;

    /**
     * Crea un grafo non orientato.
     */
    public UndirectedGraph() {
        initialize();
    }

    public UndirectedGraph(Graph<D> g) {
        this.matrix = new ArrayList<>();
        this.matrix.addAll(g.getMatrix());
        this.cycles = new ArrayList<>();
        this.cycles.addAll(g.getCycles());
        this.nodes = new HashMap<>();
        this.nodes.putAll(g.getNodes());
        this.arcs = g.getArcs();
        this.cycleNumber = g.getCycleNumber();
        this.parents = new ArrayList<>();
        this.parents.addAll(g.getParents());
        this.markedNodes = new ArrayList<>();
        this.markedNodes.addAll(g.getMarkedNodes());
        this.colors = new ArrayList<>();
        this.colors.addAll(g.getColors());
    }

    @Override
    public List<List<GraphNode<Integer, D>>> getMatrix() {
        return matrix;
    }

    @Override
    public List<List<Integer>> getCycles() {
        return cycles;
    }

    @Override
    public Map<Integer, D> getNodes() {
        return nodes;
    }

    @Override
    public int getArcs() {
        return arcs;
    }

    @Override
    public int getCycleNumber() {
        return cycleNumber;
    }

    @Override
    public List<Integer> getParents() {
        return parents;
    }

    @Override
    public List<Integer> getMarkedNodes() {
        return markedNodes;
    }

    @Override
    public List<Integer> getColors() {
        return colors;
    }


    /**
     * Istanzia un grafo non orientato.
     */
    private void initialize() {
        this.matrix = new ArrayList<>();
        this.cycles = new ArrayList<>();
        this.nodes = new HashMap<>();
        this.arcs = 0;
        this.cycleNumber = 0;
        this.parents = new ArrayList<>();
        this.markedNodes = new ArrayList<>();
        this.colors = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            this.matrix.add(i, new ArrayList<>());
            this.cycles.add(i, new ArrayList<>());
        }
        for (int i = 0; i < iterations; i++) {
            this.parents.add(0);
            this.markedNodes.add(0);
            this.colors.add(GraphNode.COLOR_WHITE);
        }
    }

    /**
     * Metodo per trovare il grafo pi&ugrave; in profondit&agrave; tramite DFS
     */
    @Override
    public void cycleDFS(GraphNode<Integer, D> u, GraphNode<Integer, D> p) {
        if (this.colors.get(u.getName()) == GraphNode.COLOR_BLACK)
            return;
        if (this.colors.get(u.getName()) == GraphNode.COLOR_GREY) {
            this.cycleNumber++;
            Integer cur = p.getName();
            this.markedNodes.set(cur, this.cycleNumber);
            while (!cur.equals(u.getName())) {
                cur = this.parents.get(cur);
                this.markedNodes.set(cur, this.cycleNumber);
            }
            return;
        }
        this.parents.set(u.getName(), p.getName());
        this.colors.set(u.getName(), GraphNode.COLOR_GREY);
        for (GraphNode<Integer, D> v : this.matrix.get(u.getName())) {
            if (Objects.equals(v.getName(), this.parents.get(u.getName())))
                continue;
            this.cycleDFS(v, u);
        }
        this.colors.set(u.getName(), GraphNode.COLOR_BLACK);
    }

    @Override
    public void addArc(GraphNode<Integer, D> u, GraphNode<Integer, D> v) {
        matrix.get(u.getName()).add(v);
        matrix.get(v.getName()).add(u);
        nodes.put(u.getName(), u.getData());
        nodes.put(v.getName(), v.getData());
        this.arcs++;
    }

    @Override
    public List<D> getCycle(GraphNode<Integer, D> u, GraphNode<Integer, D> p) {
        this.cycleNumber = 0;
        for (int i = 0; i < iterations; i++)
            this.cycles.set(i, new ArrayList<>());
        for (int i = 0; i < iterations; i++) {
            this.parents.set(i, 0);
            this.markedNodes.set(i, 0);
            this.colors.set(i, GraphNode.COLOR_WHITE);
        }
        this.cycleDFS(u, p);
        for (int i = 1; i <= this.arcs; i++) {
            if (this.markedNodes.get(i) != 0)
                this.cycles.get(this.markedNodes.get(i)).add(i);
        }
        List<D> cycle = new ArrayList<>();
        for (int x : this.cycles.get(this.cycleNumber)) {
            cycle.add(nodes.get(x));
            this.nodes.remove(x);
            this.matrix.get(x).clear();
            //se il grafo contiene dei cicli, viene rimosso
            for (List<GraphNode<Integer, D>> graphNodes : this.matrix)
                graphNodes.removeIf(cycle::contains);
        }
        this.arcs -= cycle.size();
        return cycle;
    }

    @Override
    public void clear() {
        initialize();
    }


}