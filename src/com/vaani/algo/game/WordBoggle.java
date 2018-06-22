package com.vaani.algo.game;


import com.vaani.algo.ds.core.tree.Trie;

import java.util.*;

/**
 * Boggle is a popular word game in which players attempt to find words in sequences of adjacent letters on a rectangular board.
 * <p>
 * Given a two-dimensional array board that represents the character cells of the Boggle board and an array of unique strings words, find all the possible words from words that can be formed on the board.
 * <p>
 * Note that in Boggle when you're finding a word, you can move from a cell to any of its 8 neighbors, but you can't use the same cell twice in one word.
 * <p>
 * Example
 * <p>
 * For
 * <p>
 * board = [
 * ['R', 'L', 'D'],
 * ['U', 'O', 'E'],
 * ['C', 'S', 'O']
 * ]
 * and words = ["CODE", "SOLO", "RULES", "COOL"], the output should be
 * wordBoggle(board, words) = ["CODE", "RULES"].
 */
public class WordBoggle {
    static String[] wordBoggle(char[][] board, String[] words) {
        Trie dictionary = new Trie();
        for(String word: words){
            dictionary.insert(word);
        }

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        Set<String> resultSet = new HashSet<>();

        for(int i=0; i< m; i++){
            for(int j = 0; j<n; j++){
                dfs(board, visited, dictionary, resultSet, "", i, j);
            }
        }

        List<String> sortedList = new ArrayList(new TreeSet(resultSet));

        String[] result = sortedList.toArray(new String[sortedList.size()]);
        Arrays.sort(result);
//        int i = 0;
//        for(String word: words){
//            if(resultSet.contains(word)){
//                result[i++] = word;
//            }
//        }

        return result;
    }

    static void dfs(char[][] board, boolean[][] visited, Trie dictionary, Set<String> resultSet, String str, int i, int j){
        int m = board.length;
        int n = board[0].length;

        if(i>=m || j>=n || i<0 || j<0){
            return;
        }

        if(visited[i][j]){
            return;
        }

        str = str + board[i][j];

        if(!dictionary.startsWith(str)){
            return;
        }

        if(dictionary.search(str)){
            resultSet.add(str);
        }

        visited[i][j] = true;

        dfs(board, visited, dictionary, resultSet, str,i-1, j);
        dfs(board, visited, dictionary, resultSet, str,i+1, j);
        dfs(board, visited, dictionary, resultSet, str,i, j-1);
        dfs(board, visited, dictionary, resultSet, str,i, j+1);
        dfs(board, visited, dictionary, resultSet, str,i-1, j+1);
        dfs(board, visited, dictionary, resultSet, str,i-1, j-1);
        dfs(board, visited, dictionary, resultSet, str,i+1, j+1);
        dfs(board, visited, dictionary, resultSet, str,i+1, j-1);

        visited[i][j] = false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'R', 'L', 'D'},
                {'U', 'O', 'E'},
                {'C', 'S', 'O'}
        };

        String[] words = {
                "RULES","CODE", "SOLO",  "COOL"
        };

        System.out.println(Arrays.toString(wordBoggle(board, words)));
    }
}
