package com.zlrx.algorithms;

import com.zlrx.algorithms.models.Edge;
import com.zlrx.algorithms.models.Node;
import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
public class BellmanFord {

    private List<Edge> edges;

    private List<Node> nodes;


    public static void main(String[] args) {

        List<Node> nodes = new LinkedList<>();
        Node start = new Node("Start");
        Node a = new Node("A");
        Node b = new Node("B");
        Node target = new Node("Target");
        nodes.add(start);
        nodes.add(a);
        nodes.add(b);
        nodes.add(target);

        Edge startA = new Edge(1, start, a);
        // Edge startB = new Edge(1, start, b);
        Edge bstart = new Edge(-1, b, start);
        Edge ab = new Edge(-1, a, b);
        Edge aTarget = new Edge(-1, a, target);

        List<Edge> edges = new LinkedList<>();
        edges.add(startA);
        //edges.add(startB);
        edges.add(bstart);
        edges.add(ab);
        edges.add(aTarget);


        BellmanFord bellmanFord = new BellmanFord(edges, nodes);
        bellmanFord.bellmanFord(start, target);

    }


    void bellmanFord(Node start, Node target) {
        start.setDistanceFromStart(0);
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Edge edge : edges) {
                Node actualNode = edge.getFrom();
                double distance = actualNode.getDistanceFromStart();
                if (distance == Double.MAX_VALUE) {
                    System.out.println("In iteration " + i + " " + actualNode + " distance is max");
                    continue;
                }
                double newDistance = distance + edge.getWeight();
                Node targetNode = edge.getTo();
                System.out.println("In iteration " + i + " " + targetNode + " distance calculated from " + actualNode + " = " + newDistance);
                if (newDistance < targetNode.getDistanceFromStart()) {
                    targetNode.setDistanceFromStart(newDistance);
                    targetNode.setPrevious(actualNode);
                    System.out.println("In iteration " + i + " " + targetNode + " distance is lower then old distance, set it ");
                }
                System.out.println();
            }
        }
        for (Edge edge : edges) {
            if (edge.getFrom().getDistanceFromStart() != Double.MAX_VALUE) {
                if (hasCycle(edge)) {
                    System.out.println("There has been a negetive cycle on edge " + edge);
                    return;
                }
            }
        }
        getShortestPath(target);
    }

    private boolean hasCycle(Edge edge) {
        return edge.getFrom().getDistanceFromStart() + edge.getWeight() < edge.getTo().getDistanceFromStart();
    }

    void getShortestPath(Node target) {
        if (target.getDistanceFromStart() == Double.MAX_VALUE) {
            System.out.println("No path");
        } else {
            Node actual = target;
            while (Objects.nonNull(actual)) {
                System.out.print(actual);
                System.out.print("  <--- ");
                actual = actual.getPrevious();
            }
        }
    }

}
