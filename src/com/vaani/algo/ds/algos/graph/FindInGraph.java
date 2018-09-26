package com.vaani.algo.ds.algos.graph;

import com.vaani.algo.ds.core.graph.Edge;
import com.vaani.algo.ds.core.graph.Graph;
import com.vaani.algo.ds.core.graph.Vertex;

import java.util.*;

public class FindInGraph {

    public static <T> Vertex<T> find(Graph<T> g, Vertex<T> rootVertex, T element) {
        if (null == rootVertex || null == element) {
            throw new NullPointerException("Either root vertex or search element is null !!");
        }


        if (!g.getAdjList().containsKey(rootVertex)) {
            throw new IllegalArgumentException("Search Failed : Invalid root vertex !!");
        }
//        setAllVisitedFalse(); by default all vertices are visited as false

        Queue<Vertex<T>> queue = new ArrayDeque<>();
        queue.add(rootVertex);
        rootVertex.setVisited(true);

        while (!queue.isEmpty()) {
            Vertex<T> vertexToBeProcessed = queue.remove();
            if (vertexToBeProcessed.equals(element)) {
                return vertexToBeProcessed;
            }
            Set<Edge<T>> edgesConnectedToVertex = g.getAdjList().get(vertexToBeProcessed);
            if (null == edgesConnectedToVertex) {
                continue;
            }
            for (Edge<T> edge : edgesConnectedToVertex) {
                Vertex<T> adjacentVertex = edge.getAdjacentVertex(vertexToBeProcessed);
                if (!adjacentVertex.isVisited()) {
                    adjacentVertex.setVisited(true);
                    queue.add(adjacentVertex);
                }
            }
        }
        return null;
    }
}
