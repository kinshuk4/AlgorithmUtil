package com.vaani.algo.ds.core.graph;

import java.util.*;

public class Vertex<T> {
    public T value;
    public Color color;
    public Vertex parent;
    public int discover;
    public int finish;
    public boolean isVisited;
    public List<Edge<T>> neighbours;

    public Vertex(T value) {
        this.value = value;
        this.color = Color.WHITE;
        neighbours = new LinkedList<Edge<T>>();
    }

    public Vertex(T value, boolean isVisited) {
        this(value);
        this.isVisited = isVisited;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getDiscover() {
        return discover;
    }

    public void setDiscover(int discover) {
        this.discover = discover;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public List<Edge<T>> getNeighbours() {
        return neighbours;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    public enum Color {
        WHITE, GRAY, BLACK
    }
}