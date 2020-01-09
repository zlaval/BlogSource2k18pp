package com.zlrx.algorithms;

import com.zlrx.algorithms.models.Edge;
import com.zlrx.algorithms.models.Node;

import java.util.Objects;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    /**
     * A--3---E
     * | \     \
     * 1  2     7
     * |   \     \
     * S    C--3--F
     * |   / \   /
     * 4  5  2  1
     * | /   | /
     * B     D
     */
    public static void main(String[] args) {
        DijkstraAlgorithm dijkstraAlgorithm = new DijkstraAlgorithm();
        dijkstraAlgorithm.start();
    }

    void start() {
        Node start = new Node("START");
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node target = new Node("TARGET");

        Edge sa = new Edge(1, start, a);
        start.addNeighbour(sa);
        Edge sb = new Edge(4, start, b);
        start.addNeighbour(sb);
        Edge ac = new Edge(2, a, c);
        a.addNeighbour(ac);
        Edge ae = new Edge(3, a, e);
        a.addNeighbour(ae);
        Edge et = new Edge(7, e, target);
        e.addNeighbour(et);
        Edge bc = new Edge(5, b, c);
        b.addNeighbour(bc);
        Edge cd = new Edge(2, c, d);
        c.addNeighbour(cd);
        Edge ct = new Edge(3, c, target);
        c.addNeighbour(ct);
        Edge dt = new Edge(1, d, target);
        d.addNeighbour(dt);

        computePath(start);
        getShortestPath(target);
    }


    void computePath(Node startNode) {

        startNode.setDistanceFromStart(0);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node actualNode = queue.poll();
            actualNode.getAdjacencies().forEach(edge -> {
                Node target = edge.getTo();
                double newDistance = actualNode.getDistanceFromStart() + edge.getWeight();
                if (newDistance < target.getDistanceFromStart()) {
                    queue.remove(target);
                    target.setDistanceFromStart(newDistance);
                    target.setPrevious(actualNode);
                    queue.add(target);
                }
            });
        }
    }

    void getShortestPath(Node target) {
        Node actual = target;
        while (Objects.nonNull(actual)) {
            System.out.print(actual);
            System.out.print("  <--- ");
            actual = actual.getPrevious();
        }
    }

}
