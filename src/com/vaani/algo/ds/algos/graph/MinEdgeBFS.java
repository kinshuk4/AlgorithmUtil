package com.vaani.algo.ds.algos.graph;
import com.vaani.algo.ds.core.graph.AdjacentListGraph;
import com.vaani.algo.ds.core.graph.Vertex;

import java.util.*;

//https://www.geeksforgeeks.org/minimum-number-of-edges-between-two-vertices-of-a-graph/
public class MinEdgeBFS {
    // Find the min number of edges between 2 adjacencyList

    static int minEdgeBFS(int edges[][], int u,
                          int v, int n)
    {
//        AdjacentListGraph<Integer> graph = new AdjacentListGraph<>();
//
//
//        // visited[n] for keeping track of visited
//        // node in BFS
//        Vector<Boolean> visited = new Vector<Boolean>(n);
//
//        for (int i = 0; i < n; i++) {
//            visited.addElement(false);
//        }
//
//        for(int[] edge: edges){
//            graph.addEdge(edge[0], edge[1]);
//        }
//
//        //map of vertex and distances
//        Map<Vertex<Integer>, Integer> distance = new HashMap<>();
//
//        for(Vertex<Integer> vertex: graph.getVertexSet()){
//            distance.put(vertex, 0);
//        }
//        // Initialize distances as 0
//
//
//        // queue to do BFS.
//        Queue<Vertex<Integer>> Q = new LinkedList<>();
//
//        Q.add(new Vertex<>(u));
//        graph.get(u).isVisited = true;
//
//        while (!Q.isEmpty())
//        {
//            Vertex<Integer> x = Q.peek();
//            Q.poll();
//            List<Vertex<Integer>> adjVertices = graph.getAdjacentList(x.getValue());
//            for (int i=0; i<adjVertices.size(); i++)
//            {
//                if (adjVertices.get(i).isVisited)
//                    continue;
//
//                // update distance for i
//                distance.setElementAt(distance.get(x) + 1,edges[x].get(i));
//                Q.add(edges[x].get(i));
//                visited.setElementAt(true,edges[x].get(i));
//            }
//        }
//        return distance.get(v);
        return -1;
    }
}
