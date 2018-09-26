package com.vaani.algo.ds.algos.graph;

import com.vaani.algo.ds.core.graph.Graph;
import com.vaani.algo.ds.core.graph.Vertex;

import java.util.*;

public class DfsGraph {
    public static <T> List dfs(Graph<T> graph) {
        List<Vertex<T>> visitedList = new ArrayList<>();
        Stack<Vertex<T>> tempStack = new Stack();
        Vertex<T> anySource = graph.getVertices().stream().findFirst().get();
        tempStack.push(anySource);
        anySource.isVisited = true;

        visitedList.add(tempStack.peek());
        Vertex<T> temp = null;
        while (!tempStack.isEmpty()) {
            temp = tempStack.peek();
            boolean found = false;
            for (Vertex<T> vertex : graph.getAdjVertices(temp)) {
                if (!vertex.isVisited) {
                    visitedList.add(vertex);
                    tempStack.push(vertex);
                }
            }
        }
        return visitedList;
    }
}
