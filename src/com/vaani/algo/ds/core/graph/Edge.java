package com.vaani.algo.ds.core.graph;

//To represent the edges in the graph.
public class Edge<T> {
    private static final int DEFAULT_WEIGHT = 1;

    public Vertex<T> v1, v2;
    public int weight;

    public Edge(Vertex<T> v1, Vertex<T> v2) {
        this(v1, v2, DEFAULT_WEIGHT);
    }

    public Edge(Vertex v1, Vertex v2, int weight) {
        super();
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Edge)) return false;

        Edge _obj = (Edge) obj;
        return _obj.v1.equals(v1) && _obj.v2.equals(v2) &&
                _obj.weight == weight;
    }

    @Override
    public int hashCode() {
        int result = v1.hashCode();
        result = 31 * result + v2.hashCode();
        result = 31 * result + weight;
        return result;
    }

    public Vertex<T> getAdjacentVertex(Vertex<T> vertexToBeProcessed) {
        if (v1.equals(vertexToBeProcessed)) {
            return v2;
        } else if (v2.equals(vertexToBeProcessed)) {
            return v1;
        } else {
            return null;
        }
    }
}
