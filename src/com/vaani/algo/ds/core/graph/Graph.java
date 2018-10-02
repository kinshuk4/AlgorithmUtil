package com.vaani.algo.ds.core.graph;

import java.util.*;
import java.util.stream.Collectors;

//https://www.geeksforgeeks.org/graph-and-its-representations/
//https://gist.github.com/smddzcy/bf8fc17dedf4d40b0a873fc44f855a58
//http://www.vogella.com/tutorials/JavaAlgorithmsDijkstra/article.html
public class Graph<T> {
//  	protected Map<T, Vertex<T>> adjacencyList;
//    private Map<Vertex<T>, Vertex<T>> connectedComponents = null;

    private Set<Vertex<T>> vertices;
    private Set<Edge<T>> edges;
    private Map<Vertex<T>, Set<Edge<T>>> adjList;
    private Map<Vertex<T>, Vertex<T>> connectedComponents = null;

    protected boolean isDirected;

    public Graph() {
        this(false);
    }

    public Graph(boolean isDirected) {
        vertices = new HashSet<>();
        edges = new HashSet<>();
        adjList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public boolean addVertex(T element) {
        if (null == element) {
            throw new NullPointerException("Element cannot be null !!");
        }
        return addVertex(new Vertex<T>(element));
//        Vertex<T> newVertex = findVertex(element);
//        if (null == newVertex) {
//            newVertex = new Vertex<T>(element);
////            adjacencyList.put(newVertex, new ArrayList<>());
////            connectedComponents.put(newVertex, newVertex);
//            return vertices.add(new Vertex<T>(element));;
//        }
//        return false;
    }

    public boolean addVertex(Vertex<T> v) {
        adjList.put(v, new HashSet<>());
        connectedComponents.put(v, v);
        return vertices.add(v);
    }

    public boolean addVertices(Collection<Vertex<T>> vertices) {
        return this.vertices.addAll(vertices);
    }


    public boolean removeVertex(T label) {
        return vertices.remove(new Vertex<T>(label));
    }

    public boolean removeVertex(Vertex<T> v) {
        return vertices.remove(v);
    }

    public boolean addEdge(Edge<T> e) {
        if (!edges.add(e)) {
            return false;
        }

        adjList.putIfAbsent(e.v1, new HashSet<>());
        adjList.putIfAbsent(e.v2, new HashSet<>());

        adjList.get(e.v1).add(e);

        if (!isDirected) {
            adjList.get(e.v2).add(e);
        }

        return true;
    }


    public boolean addEdge(T a, T b) {
        return addEdge(new Edge<T>(new Vertex<T>(a),
                new Vertex<T>(b)));
    }

    public boolean addEdge(T a, T b, int w) {
        return addEdge(new Edge<T>(new Vertex<T>(a),
                new Vertex<T>(b), w));
    }

    public boolean removeEdge(Edge e) {
        if (!edges.remove(e)) return false;
        Set<Edge<T>> edgesOfV1 = adjList.get(e.v1);
        Set<Edge<T>> edgesOfV2 = adjList.get(e.v2);

        if (edgesOfV1 != null) edgesOfV1.remove(e);
        if (edgesOfV2 != null) edgesOfV2.remove(e);

        return true;
    }

    public boolean removeEdge(T vertexLabel1, T vertexLabel2) {
        return removeEdge(new Edge<T>(new Vertex<T>(vertexLabel1),
                new Vertex<T>(vertexLabel2)));
    }

    public Set<Vertex> getAdjVertices(Vertex v) {
        return adjList.get(v).stream()
                .map(e -> e.v1.equals(v) ? e.v2 : e.v1)
                .collect(Collectors.toSet());
    }

    public Set<Edge<T>> getAdjEdges(Vertex<T> v) {
        return adjList.get(v);
    }

    public Set<Vertex> getVertices() {
        return Collections.unmodifiableSet(vertices);
    }

    public Set<Edge> getEdges() {
        return Collections.unmodifiableSet(edges);
    }

    public Map<Vertex<T>, Set<Edge<T>>> getAdjList() {
        return Collections.unmodifiableMap(adjList);
    }


    public int getV() {
        return vertices.size();
    }


    public int getE() {
        return edges.size();
    }

    public int getDegree(T a) {
        return 0;
    }


    public boolean hasVertex(T a) {
        return false;
    }


    public boolean hasEdge(T a, T b) {
        return false;
    }


    public boolean isDirected() {
        return false;
    }


//    private Vertex<T> findVertex(T element) {
//        if (null == element) {
//            throw new NullPointerException("Element cannot be null !!");
//        }
//        HashSet<Vertex<T>> vertexSet = getAllComponents();
//        for (Vertex<T> vertexFromSet : vertexSet) {
//            Vertex<T> foundVertex = performBFS(vertexFromSet, element);
//            if (null != foundVertex) {
//                return foundVertex;
//            }
//        }
//        return null;
//    }

//    private HashSet<Vertex<T>> getAllComponents() {
//        HashSet<Vertex<T>> setOfVertex = new HashSet<>();
//        for (Vertex<T> vertex : connectedComponents.keySet()) {
//            setOfVertex.add(findComponent(vertex));
//        }
//        return setOfVertex;
//    }
//    private Vertex<T> findComponent(Vertex<T> vertex) {
//        Vertex<T> currVertex = vertex;
//        while (!currVertex.equals(connectedComponents.get(currVertex))) {
//            currVertex = connectedComponents.get(currVertex);
//        }
//        return currVertex;
//    }
//    private void unionComponents(Vertex<T> firstVertex, Vertex<T> secondVertex) {
//        connectedComponents.put(findComponent(firstVertex), findComponent(secondVertex));
//    }
//
//    /**
//     * Method to perform a Breadth First Search
//     * @param rootVertex
//     * @param element
//     * @return {@link Vertex<T>}
//     */


//    /**
//     * Method to set all visited vertices as false
//     */
//    private void setAllVisitedFalse() {
//        for (Vertex<T> vertex : adjacencyList.keySet()) {
//            vertex.isVisited = false;
//        }
//    }


//	public Vertex getVertex(int vert){
//		return adjacencyList.get(vert);
//	}

    //
//	public Collection<Vertex<T>> getAdjacencyList() {
//		return adjacencyList.values();
//	}
}
