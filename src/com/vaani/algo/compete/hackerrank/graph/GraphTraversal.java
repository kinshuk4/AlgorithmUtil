package com.vaani.algo.compete.hackerrank.graph;

import java.util.LinkedList;

/**
 * Created by andersonkmi on 8/2/16.
 */
public class GraphTraversal {
    public static void main(String[] args) {
        Node root = new Node();
        root.setValue(0);

        Node node1 = new Node();
        node1.setValue(1);

        Node node2 = new Node();
        node2.setValue(2);

        Node node3 = new Node();
        node3.setValue(3);

        root.add(node1);
        root.add(node2);
        root.add(node3);

        Node node4 = new Node();
        node4.setValue(4);

        node2.add(node4);

        Node node5 = new Node();
        node5.setValue(5);

        Node node6 = new Node();
        node6.setValue(6);

        node3.add(node5);
        node3.add(node6);

        //depthFirst(root);
        System.out.println("-----");
        breadthFirst(root);
    }

    private static void depthFirst(Node node) {
        if(node != null) {
            System.out.println(node.getValue());
            node.setStatus(Status.Visited);
            node.getAdjacentNodes().stream().filter(adjacentNode -> adjacentNode.getStatus() == Status.Unvisited).forEach(GraphTraversal::depthFirst);
        }
    }

    private static void breadthFirst(Node node) {
        LinkedList<Node> nodes = new LinkedList<>();
        node.setStatus(Status.Visited);
        System.out.println(node.getValue());
        nodes.add(node);

        while(!nodes.isEmpty()) {
            Node current = nodes.removeFirst();
            for(Node adjacentNode : current.getAdjacentNodes()) {
                if(adjacentNode.getStatus() == Status.Unvisited) {
                    System.out.println(adjacentNode.getValue());
                    adjacentNode.setStatus(Status.Visited);
                    nodes.add(adjacentNode);
                }
            }
        }
    }
}
