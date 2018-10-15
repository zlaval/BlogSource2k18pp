package com.zlrx.algorithms.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Node implements Comparable<Node> {

    private final String name;
    private Node previous;
    private List<Edge> adjacencies = new LinkedList<>();
    private double distanceFromRoot = Double.MAX_VALUE;

    public void addNeighbour(Edge edge) {
        adjacencies.add(edge);
    }

    @Override
    public int compareTo(@NotNull Node o) {
        return Double.compare(distanceFromRoot, o.distanceFromRoot);
    }

    @Override
    public String toString() {
        return "NODE: " + name + "(" + distanceFromRoot + ")";
    }
}
