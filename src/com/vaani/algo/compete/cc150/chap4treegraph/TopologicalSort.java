package com.vaani.algo.compete.cc150.chap4treegraph;


import com.vaani.algo.ds.core.graph.AdjacentListGraph;
import com.vaani.algo.ds.core.graph.Vertex;

import java.util.*;

/**
 * Created by Xiaomeng on 11/25/2014.
 */
public class TopologicalSort {
    public static Vertex[] topologicalSort(AdjacentListGraph graph) {
//        Set<Vertex> vertexSet = graph.getVertexMap();
//        if (vertexSet.size() < 2) {
//            return vertexSet.toArray(new Vertex[0]);
//        }
//
//        LinkedList<Vertex> sortedList = new LinkedList<Vertex>();
//        TimeRecorder recorder = new TimeRecorder();
//
//        for (Vertex vertex : vertexSet) {
//            if (vertex.color == Vertex.Color.WHITE) {
//                visitVertex(graph, vertex, recorder, sortedList);
//            }
//        }
//
//        return sortedList.toArray(new Vertex[0]);
        return null;
    }

    /**
     * (Depth First Search)
     */
    public static void visitVertex(AdjacentListGraph graph, Vertex vertex, TimeRecorder recorder, LinkedList<Vertex> sortedList) {
        recorder.time += 1;
        vertex.color = Vertex.Color.GRAY;
        vertex.discover = recorder.time;

        Map<Vertex, List<Vertex>> edgeMap = graph.getAdjacencys();
        List<Vertex> adjacencys = edgeMap.get(vertex);
        if (adjacencys != null && adjacencys.size() > 0) {
            for (Vertex adjacency : adjacencys) {
                if (adjacency.color == Vertex.Color.WHITE) {
                    adjacency.parent = vertex;
                    visitVertex(graph, adjacency, recorder, sortedList);
                }
            }
        }

        recorder.time += 1;
        vertex.color = Vertex.Color.BLACK;
        vertex.finish = recorder.time;
        sortedList.addLast(vertex);
    }

    public static void printVertex(Vertex[] Vertexs) {
        for (Vertex vertex : Vertexs) {
            System.out.println(vertex.getValue() + "  discover time:"
                    + vertex.getDiscover() + "  finish time:"
                    + vertex.getFinish());
        }
    }

    public static void main(String[] args) {
//        AdjacentListGraph graph = new AdjacentListGraph();
//        Set<Vertex> vertexSet = graph.getVertexMap();
//        Map<Vertex, List<Vertex>> edgeMap = graph.getAdjacencys();
//
//        Vertex aVertex = new Vertex('a');
//        Vertex bVertex = new Vertex('b');
//        Vertex cVertex = new Vertex('c');
//        Vertex dVertex = new Vertex('d');
//
//        vertexSet.add(aVertex);
//        vertexSet.add(bVertex);
//        vertexSet.add(cVertex);
//        vertexSet.add(dVertex);
//
//        edgeMap.put(aVertex, new ArrayList<Vertex>());
//        edgeMap.put(bVertex, new ArrayList<Vertex>());
//        edgeMap.put(dVertex, new ArrayList<Vertex>());
//
//        edgeMap.get(bVertex).add(dVertex);
//        edgeMap.get(bVertex).add(aVertex);
//        edgeMap.get(dVertex).add(aVertex);
//        edgeMap.get(aVertex).add(cVertex);
//
//        Vertex[] sortedVertexs = topologicalSort(graph);
//        printVertex(sortedVertexs);
    }

    public static class TimeRecorder {
        private int time = 0;

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}
