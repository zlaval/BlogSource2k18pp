package com.zlrx.algorithms;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimJarnik {

    private List<Vertex> unvisitedVertices;
    private List<Edge> spanningTree;
    private PriorityQueue<Edge> edgeHeap;
    private int cost;


    public static void main(String[] args) {
        PrimJarnik primJarnik = new PrimJarnik();
        primJarnik.solve();
    }

    public PrimJarnik() {
        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");

        Edge e01 = new Edge(v0, v1, 3);
        Edge e02 = new Edge(v0, v2, 2);
        Edge e03 = new Edge(v0, v3, 5);
        Edge e08 = new Edge(v0, v8, 8);
        v0.setAdjacencies(Lists.newArrayList(e01, e02, e03, e08));

        Edge e15 = new Edge(v1, v5, 11);
        Edge e13 = new Edge(v1, v3, 2);
        Edge e10 = new Edge(v1, v0, 3);
        v1.setAdjacencies(Lists.newArrayList(e15, e13, e10));

        Edge e24 = new Edge(v2, v4, 5);
        Edge e23 = new Edge(v2, v3, 2);
        Edge e20 = new Edge(v2, v0, 2);
        v2.setAdjacencies(Lists.newArrayList(e24, e23, e20));

        Edge e34 = new Edge(v3, v4, 4);
        Edge e35 = new Edge(v3, v5, 6);
        Edge e36 = new Edge(v3, v6, 3);
        Edge e30 = new Edge(v3, v0, 5);
        Edge e31 = new Edge(v3, v1, 2);
        Edge e32 = new Edge(v3, v2, 2);
        v3.setAdjacencies(Lists.newArrayList(e34, e35, e36, e30, e31, e32));

        Edge e46 = new Edge(v4, v6, 6);
        Edge e42 = new Edge(v4, v2, 5);
        Edge e43 = new Edge(v4, v3, 4);
        v4.setAdjacencies(Lists.newArrayList(e46, e42, e43));

        Edge e56 = new Edge(v5, v6, 2);
        Edge e57 = new Edge(v5, v7, 3);
        Edge e51 = new Edge(v5, v1, 11);
        Edge e53 = new Edge(v5, v3, 6);
        v5.setAdjacencies(Lists.newArrayList(e56, e57, e51, e53));

        Edge e67 = new Edge(v6, v7, 6);
        Edge e63 = new Edge(v6, v3, 3);
        Edge e64 = new Edge(v6, v4, 6);
        Edge e65 = new Edge(v6, v5, 2);
        v6.setAdjacencies(Lists.newArrayList(e67, e63, e64, e65));

        Edge e78 = new Edge(v7, v8, 1);
        Edge e75 = new Edge(v7, v5, 3);
        Edge e76 = new Edge(v7, v6, 6);
        v7.setAdjacencies(Lists.newArrayList(e78, e75, e76));

        Edge e80 = new Edge(v8, v0, 8);
        Edge e87 = new Edge(v8, v7, 1);
        v8.setAdjacencies(Lists.newArrayList(e80, e87));

        unvisitedVertices = Lists.newArrayList(v0, v1, v2, v3, v4, v5, v6, v7, v8);
        this.spanningTree = new ArrayList<>();
        this.edgeHeap = new PriorityQueue<>();
    }

    public void solve() {
        spanningTree(unvisitedVertices.get(0));
        spanningTree.forEach(e -> System.out.println("(" + e.getStart() + "  " + e.getTarget() + ") --> "));
        System.out.println("Cost: " + cost);
    }


    private void spanningTree(Vertex vertex) {
        unvisitedVertices.remove(vertex);
        while (!unvisitedVertices.isEmpty()) {
            for (Edge edge : vertex.getAdjacencies()) {
                if (unvisitedVertices.contains(edge.getTarget())) {
                    edgeHeap.add(edge);
                }
            }

            Edge minimum = edgeHeap.remove();
            if (unvisitedVertices.contains(minimum.target)) {
                spanningTree.add(minimum);
                cost += minimum.getWeight();
                vertex = minimum.getTarget();
                unvisitedVertices.remove(vertex);
            }

        }
    }

    @Getter
    @Setter
    @ToString(of = "name")
    @RequiredArgsConstructor
    class Vertex {
        private final String name;
        private List<Edge> adjacencies;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    class Edge implements Comparable<PrimJarnik.Edge> {
        private Vertex start;
        private Vertex target;
        private int weight;

        @Override
        public int compareTo(PrimJarnik.Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}

