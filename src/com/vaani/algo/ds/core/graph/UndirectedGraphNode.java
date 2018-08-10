package com.vaani.algo.ds.core.graph;

import java.util.ArrayList;
import java.util.List;


public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;
    public boolean visited;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
        visited = false;
    }
}
