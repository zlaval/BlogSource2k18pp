package com.zlrx.algorithms.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
public class Vertex {

    private int data;
    private boolean visited;
    private List<Vertex> neighbour;

    public Vertex(int data) {
        this.data = data;
        this.neighbour = new LinkedList<>();
    }

    public boolean addNeighbour(Vertex vertex) {
        return neighbour.add(vertex);
    }

}
