package com.vaani.algo.ds.core.graph;

import java.util.*;

public class AdjacentListGraph<T> {
    private Map<T, Vertex<T>> vertexMap = new HashMap<>();
    private Map<Vertex<T>, List<Vertex<T>>> adjacencys = new HashMap<>();
    private boolean isDirected;

    public AdjacentListGraph(){
        isDirected = false;
    }

    public Set<Vertex<T>> getVertexSet() {
        return new HashSet<>(vertexMap.values());
    }

    public Map<Vertex<T>, List<Vertex<T>>> getAdjacencys() {
        return adjacencys;
    }

    public List<Vertex<T>> getAdjacentList(T nodeId){
        return adjacencys.get(new Vertex<T>(nodeId));
    }

    public void addVertices(T[] vertices){
        for(T item: vertices){
            vertexMap.put(item, new Vertex<T>(item));
        }
    }

    public void addEdge(T source, T destination){
        Vertex<T> sourceVertex = new Vertex<T>(source);
        Vertex<T> destinationVertex = new Vertex<T>(destination);
        vertexMap.put(source, sourceVertex);
        vertexMap.put(destination, destinationVertex);
        addEdge(sourceVertex, destinationVertex);
    }

    private void addEdge(Vertex<T> source, Vertex<T> destination){
        updateEdge(source, destination);
        if(!isDirected){
            updateEdge(destination, source);
        }
    }

    private void updateEdge(Vertex<T> source, Vertex<T> destination){
        if(!adjacencys.containsKey(source)){
            adjacencys.put(source, new LinkedList<>());
        }

        adjacencys.get(source).add(destination);
    }

    public Vertex<T> get(T item){
        return vertexMap.get(item);
    }

}
