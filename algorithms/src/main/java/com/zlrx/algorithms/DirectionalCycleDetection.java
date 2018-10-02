package com.zlrx.algorithms;

import com.zlrx.algorithms.models.Vertex;

public class DirectionalCycleDetection {


    /******************
     *          0
     *         / \
     *        v  v
     *       1   2
     *      /   / ^
     *     v   v  \
     *     3   4->5
     ******************/
    public static void main(String[] args) {
        Vertex root = new Vertex(0);
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);

        root.addNeighbour(vertex1);
        root.addNeighbour(vertex2);
        vertex1.addNeighbour(vertex3);
        vertex2.addNeighbour(vertex4);
        vertex4.addNeighbour(vertex5);
        vertex5.addNeighbour(vertex2);

        detectCycle(root);
    }


    private static void detectCycle(Vertex parent) {
        parent.setVisiting(true);
        System.out.println("Visiting vertex " + parent);
        for (Vertex vertex : parent.getNeighbours()) {
            if (vertex.isVisiting()) {
                System.out.println("Cycle detected when visiting vertex " + vertex);
                return;
            }

            if (!vertex.isVisited()) {
                vertex.setVisited(true);
                detectCycle(vertex);
            }

        }
        parent.setVisiting(true);
        parent.setVisiting(false);
    }


}
