package com.zlrx.algorithms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Kruskal {

    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public static void main(String[] args) {
        Kruskal kruskal = new Kruskal();
        kruskal.spanningTree();
    }

    public Kruskal() {
        vertices = new ArrayList<>();
        vertices.add(new Vertex("0"));
        vertices.add(new Vertex("1"));
        vertices.add(new Vertex("2"));
        vertices.add(new Vertex("3"));
        vertices.add(new Vertex("4"));
        vertices.add(new Vertex("5"));
        vertices.add(new Vertex("6"));
        vertices.add(new Vertex("7"));
        vertices.add(new Vertex("8"));

        edges = new ArrayList<>();
        edges.add(new Edge(vertices.get(0), vertices.get(1), 3));
        edges.add(new Edge(vertices.get(0), vertices.get(2), 2));
        edges.add(new Edge(vertices.get(0), vertices.get(3), 5));
        edges.add(new Edge(vertices.get(0), vertices.get(8), 8));
        edges.add(new Edge(vertices.get(1), vertices.get(5), 11));
        edges.add(new Edge(vertices.get(1), vertices.get(3), 2));
        edges.add(new Edge(vertices.get(2), vertices.get(4), 5));
        edges.add(new Edge(vertices.get(2), vertices.get(3), 2));
        edges.add(new Edge(vertices.get(3), vertices.get(4), 4));
        edges.add(new Edge(vertices.get(3), vertices.get(5), 6));
        edges.add(new Edge(vertices.get(3), vertices.get(6), 3));
        edges.add(new Edge(vertices.get(4), vertices.get(6), 6));
        edges.add(new Edge(vertices.get(5), vertices.get(6), 2));
        edges.add(new Edge(vertices.get(5), vertices.get(7), 3));
        edges.add(new Edge(vertices.get(6), vertices.get(7), 6));
        edges.add(new Edge(vertices.get(7), vertices.get(8), 1));
    }

    public void spanningTree() {
        DisjointSet disjointSet = new DisjointSet(vertices);
        List<Edge> minimumSpanningTree = new ArrayList<>();
        Collections.sort(edges);

        for (Edge edge : edges) {
            Vertex u = edge.getStart();
            Vertex v = edge.getTarget();
            if (disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())) {
                minimumSpanningTree.add(edge);
                disjointSet.union(u.getNode(), v.getNode());
            }
        }

        minimumSpanningTree.forEach(e -> System.out.println("(" + e.getStart() + "  " + e.getTarget() + " weight: " + e.getWeight() + ") --> "));
        int cost = minimumSpanningTree.stream().map(Edge::getWeight).reduce((a, b) -> a += b).orElse(0);
        System.out.println("Cost: " + cost);
    }

    @Getter
    @Setter
    @ToString(of = "name")
    @RequiredArgsConstructor
    private class Vertex {
        private final String name;
        private Node node;
    }

    @Getter
    @ToString
    @AllArgsConstructor
    private class Edge implements Comparable<Edge> {
        private Vertex start;
        private Vertex target;
        private int weight;

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    @Getter
    @Setter
    @ToString
    @RequiredArgsConstructor
    private class Node {
        private final int id;
        private int rank;
        private Node parent;
    }

    @Getter
    @Setter
    @ToString
    private class DisjointSet {
        private int nodeCount;
        private int setCount;
        private List<Node> representatives;

        public DisjointSet(List<Vertex> vertices) {
            makeSets(vertices);
        }

        private void makeSets(List<Vertex> vertices) {
            representatives = vertices.stream()
                    .map(this::makeSet)
                    .collect(Collectors.toList());
        }

        private Node makeSet(Vertex vertex) {
            //for simplicity, id = index in list
            Node node = new Node(nodeCount++);
            vertex.setNode(node);
            return node;
        }

        public int find(Node node) {
            Node rootNode = node;
            while (Objects.nonNull(rootNode.getParent())) {
                rootNode = rootNode.getParent();
            }

            Node current = node;
            while (current != rootNode) {
                Node tmp = current.getParent();
                current.setParent(rootNode);
                current = tmp;
            }
            return rootNode.getId();
        }

        public void union(Node a, Node b) {
            int aIndex = find(a);
            int bIndex = find(b);

            if (aIndex == bIndex) {
                return;
            }

            Node rootFirstSet = representatives.get(aIndex);
            Node rootSecondSet = representatives.get(bIndex);

            if (rootFirstSet.getRank() < rootSecondSet.getRank()) {
                rootFirstSet.setParent(rootSecondSet);
            } else if (rootFirstSet.getRank() > rootSecondSet.getRank()) {
                rootSecondSet.setParent(rootFirstSet);
            } else {
                rootSecondSet.setParent(rootFirstSet);
                rootFirstSet.setRank(rootFirstSet.getRank() + 1);
            }
        }

    }


}

