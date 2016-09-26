package com.vaani.algo.compete.codility;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Battleship {
    public static void main(String[] args) {
        Battleship battleship = new Battleship();
//        String result = battleship.solution(5, "2A 2B,1D 4D", "2A 2B 1D 2D 3D 4D");
//        System.out.println(result);
        
        String result = battleship.solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A");
        System.out.println(result);
    }
    public String solution(int dimension, String ships, String hits) {

        int sunkShips = 0;
        int totalHits = 0;

        // Builds and initializes the board
        char[][] board = build(dimension);
        System.out.println(Arrays.deepToString(board));

        // Parses the ships positions and retrieve the coordinates
        List<String> coordinates = new LinkedList<>();
        
        StringTokenizer tokenizer = new StringTokenizer(ships, ",");
        while(tokenizer.hasMoreTokens()) {
            String shipCoordinates = tokenizer.nextToken();
            markShipCoordinates(shipCoordinates, board);
            coordinates.add(shipCoordinates);
        }
        System.out.println(coordinates);

        // Parses the hits
        StringTokenizer hitToken = new StringTokenizer(hits);
        
        while(hitToken.hasMoreTokens()) {
            String hit = hitToken.nextToken();
//            System.out.println(hit);
            markHit(hit, board);
        }
        
        for(int i=0; i < board.length; i++){
        	System.out.println(Arrays.toString(board[i]));
        }
        // Count hits and sunken ships
        for(String ship : coordinates) {
            StringTokenizer shipTokenizer = new StringTokenizer(ship);

            String startCoord = shipTokenizer.nextToken();
            String endCoord = shipTokenizer.nextToken();

            int[] startCoords = parseCoordinates(startCoord);
            int startRow = startCoords[0];
            int startCol = startCoords[1];

            int[] endCoords = parseCoordinates(endCoord);
            int endRow = endCoords[0];
            int endCol = endCoords[1];

            int shipHits = 0;
            int intactShipParts = 0;
            for(int i = startRow; i <= endRow; i++) {
                for(int j = startCol; j <= endCol; j++) {
                    if(board[i][j] == 'S') {
                        intactShipParts++;
                    } else if(board[i][j] == 'H') {
                        shipHits++;
                    }
                }
            }

            if(shipHits > 0) {
                if(intactShipParts == 0) {
                    sunkShips++;
                } else {
                    totalHits += shipHits;
                }
            }
        }

        return String.format("%d,%d", sunkShips, totalHits);
    }

    private char[][] build(int dimension) {
        char[][] board = new char[dimension][dimension];

        for(int i = 0; i < dimension; i++) {
            for(int j = 0; j < dimension; j++) {
                board[i][j] = 'W';
            }
        }

        return board;
    }

    private int convertLetterToPosition(char position) {
        return (int)Character.toUpperCase(position) - 65;
    }

    private void markShipCoordinates(String coordinates, char[][] board) {
        StringTokenizer tokenizer = new StringTokenizer(coordinates);

        String startCoord = tokenizer.nextToken();
        String endCoord = tokenizer.nextToken();

        int[] startCoords = parseCoordinates(startCoord);
        int startRow = startCoords[0];
        int startCol = startCoords[1];

        int[] endCoords = parseCoordinates(endCoord);
        int endRow = endCoords[0];
        int endCol = endCoords[1];

        for(int i = startRow; i <= endRow; i++) {
            for(int j = startCol; j <= endCol; j++) {
                board[i][j] = 'S';
            }
        }
    }

    private void markHit(String hit, char[][] board) {
        int[] coords = parseCoordinates(hit);
        board[coords[0]][coords[1]] = 'H';
    }

    private int[] parseCoordinates(String coordinate) {
        int[] coords = new int[2];
        coords[0] = Character.getNumericValue(coordinate.charAt(0)) - 1;
        coords[1] = convertLetterToPosition(coordinate.charAt(1));
//        System.out.println(coordinate);
//        System.out.println(Arrays.toString(coords));
        return coords;
    }
}