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

//TODO refactor
public class Kruskal {

    public static void main(String[] args) {
        List<Vertex> vertices = new ArrayList<>();
        vertices.add(new Vertex("0"));
        vertices.add(new Vertex("1"));
        vertices.add(new Vertex("2"));
        vertices.add(new Vertex("3"));
        vertices.add(new Vertex("4"));
        vertices.add(new Vertex("5"));
        vertices.add(new Vertex("6"));
        vertices.add(new Vertex("7"));
        vertices.add(new Vertex("8"));

        List<Edge> edges = new ArrayList<>();
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

        Kruskal kruskal = new Kruskal();
        kruskal.spanningTree(vertices, edges);
    }

    public void spanningTree(List<Vertex> vertices, List<Edge> edges) {
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
        minimumSpanningTree.forEach(e -> System.out.println("(" + e.getStart() + "  " + e.getTarget() + ") --> "));
    }

}

@Getter
@Setter
@ToString(of = "name")
@RequiredArgsConstructor
class Vertex {
    private final String name;
    private Node node;
}

@Getter
@ToString
@AllArgsConstructor
class Edge implements Comparable<Edge> {
    private Vertex start;
    private Vertex target;
    private int weight;

    @Override
    public int compareTo(Edge o) {
        return Double.compare(this.weight, o.weight);
    }
}

@Getter
@Setter
@ToString
@RequiredArgsConstructor
class Node {
    private final int id;
    private int rank;
    private Node parent;
}

@Getter
@Setter
@ToString
class DisjointSet {
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
        Node node = new Node(nodeCount);
        vertex.setNode(node);
        setCount++;
        nodeCount++;
        return node;
    }

    public int find(Node node) {
        Node current = node;
        while (Objects.nonNull(current.getParent())) {
            current = current.getParent();
        }
        Node rootNode = current;
        current = node;
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

        this.setCount--;
    }

}
