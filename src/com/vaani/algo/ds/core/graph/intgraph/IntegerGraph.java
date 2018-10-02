package com.vaani.algo.ds.core.graph.intgraph;

import com.vaani.algo.ds.core.graph.Edge;
import com.vaani.algo.ds.core.graph.Graph;
import com.vaani.algo.ds.core.graph.Vertex;

import java.util.*;


public class IntegerGraph extends Graph<Integer> {
    public IntegerGraph(int numberVertices) {
        super();
        for(int i=0;i<numberVertices;i++){
            getAdjList().put(new Vertex<Integer>(i), new HashSet<>());
        }
    }
}
