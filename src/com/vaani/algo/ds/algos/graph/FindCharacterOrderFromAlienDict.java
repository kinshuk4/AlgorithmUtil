package com.vaani.algo.ds.algos.graph;


import com.vaani.algo.compete.cc150.chap4treegraph.TopologicalSort;
import com.vaani.algo.ds.core.graph.AdjacentListGraph;
import com.vaani.algo.ds.core.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.
 * <p>
 * Examples:
 * <p>
 * Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
 * Output: Order of characters is 'b', 'd', 'a', 'c'
 * Note that words are sorted and in the given language "baa"
 * comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 * <p>
 * Input:  words[] = {"caa", "aaa", "aab"}
 * Output: Order of characters is 'c', 'a', 'b'
 */
public class FindCharacterOrderFromAlienDict {
    public static void printOrder(String words[]) {
        AdjacentListGraph graph = new AdjacentListGraph();
        int size = words.length;
        Map<Vertex, List<Vertex>> edgeMap = graph.getAdjacencys();
//        graph.getVertexMap().add(new Vertex('a'));
//        graph.getVertexMap().add(new Vertex('b'));
//        graph.getVertexMap().add(new Vertex('c'));
//        graph.getVertexMap().add(new Vertex('d'));
        // Process all adjacent pairs of words and create a graph
        for (int i = 0; i < size - 1; i++) {
            // Take the current two words and find the first mismatching character
            String word1 = words[i];
            String word2 = words[i + 1];
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                // If we find a mismatching character, then add an edge
                // from character of word1 to that of word2
                if (word1.charAt(j) != word2.charAt(j)) {
                    Vertex vertex1 = new Vertex(word1.charAt(j));
                    Vertex vertex2 = new Vertex(word2.charAt(j));
                    if (!edgeMap.containsKey(vertex1)) {
                        edgeMap.put(vertex1, new ArrayList<Vertex>());
                    }
                    edgeMap.get(vertex1).add(vertex2);
                    break;
                }
            }
        }

        // Print topological sort of the above created graph
        TopologicalSort sortUtil = new TopologicalSort();
        Vertex[] result = sortUtil.topologicalSort(graph);
        for (Vertex vertex : result) {
            System.out.println(vertex.getValue());
        }
    }

    public static void main(String[] args) {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        printOrder(words);
    }
}
