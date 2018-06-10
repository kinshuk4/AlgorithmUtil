package com.vaani.algo.ds.core.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xiaomeng on 9/13/2014.
 */
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
