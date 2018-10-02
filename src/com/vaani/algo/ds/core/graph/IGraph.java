package com.vaani.algo.ds.core.graph;

//https://introcs.cs.princeton.edu/java/45graph/
public interface IGraph<T> {
    void addEdge(T a, T b); // add edge a-b
    int getV(); // get number of adjacencyList
    int getE();  // get number of edges
    Iterable<T> getAdjacencyList(); // get all adjacencyList
    Iterable<T> getAdjacentVertices(); // get all adjacent adjacencyList
    int getDegree(T a); // get degree of node at t
    boolean hasVertex(T a); // has vertice?
    boolean hasEdge(T a, T b); // has edge?
    boolean isDirected();
    boolean addVertex(T a);
}
