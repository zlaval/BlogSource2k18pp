package com.zlrx.algorithms.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "neighbours")
public class Vertex {

    private int data;
    private boolean visited;
    private boolean visiting;
    private List<Vertex> neighbours;

    public Vertex(int data) {
        this.data = data;
        this.neighbours = new LinkedList<>();
    }

    public boolean addNeighbour(Vertex vertex) {
        return neighbours.add(vertex);
    }

}
