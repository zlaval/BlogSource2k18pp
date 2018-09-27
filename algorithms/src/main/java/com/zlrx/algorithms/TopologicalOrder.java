package com.zlrx.algorithms;

import com.zlrx.algorithms.models.Vertex;

import java.util.Stack;

public class TopologicalOrder {


    private Stack<Vertex> stack;


    public TopologicalOrder() {
        this.stack = new Stack<>();
    }


    private void dfs(Vertex vertex) {
        vertex.setVisited(true);
        for (Vertex v : vertex.getNeighbour()) {
            if (!v.isVisited()) {
                dfs(v);
            }
        }
        stack.push(vertex);
    }


    /****************
     *        O      *
     *      /  \     *
     *     4 <- 3    *
     *    / \   \    *
     *   2  1   5    *
     *   |----->|
     ***************/
    public static void main(String[] args) {
        TopologicalOrder topologicalOrder = new TopologicalOrder();

        Vertex root = new Vertex(0);
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);
        root.addNeighbour(vertex4);
        root.addNeighbour(vertex3);
        vertex4.addNeighbour(vertex2);
        vertex4.addNeighbour(vertex1);
        vertex3.addNeighbour(vertex5);
        vertex3.addNeighbour(vertex4);
        vertex2.addNeighbour(vertex5);

        topologicalOrder.dfs(root);

        Stack<Vertex> stack = topologicalOrder.stack;

        while (!stack.empty()) {
            System.out.print(" -> " + stack.pop().getData());
        }

    }


}
