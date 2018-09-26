package com.vaani.algo.ds.algos.graph;

import com.vaani.algo.ds.core.graph.Edge;
import com.vaani.algo.ds.core.graph.Graph;
import com.vaani.algo.ds.core.graph.Vertex;
import com.vaani.algo.ds.core.graph.intgraph.IntegerGraph;

import java.util.*;

//https://www.evernote.com/shard/s34/nl/3857659/7c5b3cd1-d324-497c-9e16-78de558ffdb9/
public class Dijkstra {
    Dijkstra obj = new Dijkstra();

    // Create a new graph.


    private static IntegerGraph initGraph() {
        IntegerGraph g = new IntegerGraph(9);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 1, 8);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(2, 3, 7);
        g.addEdge(3, 2, 7);
        g.addEdge(3, 5, 14);
        g.addEdge(3, 4, 9);
        g.addEdge(4, 3, 9);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 4, 10);
        g.addEdge(5, 3, 9);
        g.addEdge(5, 2, 4);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(6, 5, 2);
        g.addEdge(7, 0, 8);
        g.addEdge(7, 8, 7);
        g.addEdge(7, 1, 11);
        g.addEdge(7, 6, 1);
        g.addEdge(8, 2, 2);
        g.addEdge(8, 7, 7);
        g.addEdge(8, 6, 6);
        return g;
    }

    public static void main(String[] args) {
        IntegerGraph g = initGraph();
        calculate(g, g.getVertices().stream().findFirst().get());
    }
    // Add the required edges.


    // Print the minimum Distance.
//		for(
//    Vertex v:g.getAdjacencyList())
//
//    {
//        System.out.print("Vertex - " + v + " , Dist - " + v.minDistance + " , Path - ");
//        for (Vertex pathvert : v.path) {
//            System.out.print(pathvert + " ");
//        }
//        System.out.println("" + v);
//    }

//}

    public static void calculate(IntegerGraph g, Vertex source) {
        // Algo:
        // 1. Take the unvisited node with minimum weight.
        // 2. Visit all its neighbours.
        // 3. Update the distances for all the neighbours (In the Priority Queue).
        // Repeat the process till all the connected nodes are visited.
        Map<Vertex, Double> distanceMatrix = new HashMap<>();
        for (Vertex<Integer> vertex : g.getVertices()) {
            distanceMatrix.put(vertex, Double.MAX_VALUE);//set every node to infinte
        }

        distanceMatrix.put(source, 0.0);

        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        queue.add(source);


        while (!queue.isEmpty()) {

            Vertex<Integer> u = queue.poll();

            for (Edge<Integer> neighbour : g.getAdjEdges(u)) {
                Double newDist = distanceMatrix.get(u) + neighbour.weight;
                Vertex<Integer> target = neighbour.getAdjacentVertex(u);
                if (newDist < distanceMatrix.get(target)) {
                    // Remove the node from the queue to update the distance value.
                    queue.remove(target);
                    distanceMatrix.put(target, newDist);

                    // Take the path visited till now and add the new node.s
//                    neighbour.target.path = new LinkedList<Vertex>(u.path);
//                    neighbour.target.path.add(u);

                    //Reenter the node with new distance.
                    queue.add(target);
                }
            }
        }
    }
}
