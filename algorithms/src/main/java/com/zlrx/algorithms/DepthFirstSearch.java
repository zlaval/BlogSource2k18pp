package com.zlrx.algorithms;

import com.zlrx.algorithms.models.Vertex;

import java.util.Deque;
import java.util.LinkedList;

public class DepthFirstSearch {

    /******************
     *        O       *
     *       / \      *
     *      /   \     *
     *     4    3     *
     *    / \  / \    *
     *   2  1  5 7    *
     *  /         \   *
     * 6          8   *
     *****************/
    public static void main(String[] args) {
        Vertex root = new Vertex(0);
        Vertex vertex1 = new Vertex(1);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex4 = new Vertex(4);
        Vertex vertex5 = new Vertex(5);
        Vertex vertex6 = new Vertex(6);
        Vertex vertex7 = new Vertex(7);
        Vertex vertex8 = new Vertex(8);
        root.addNeighbour(vertex4);
        root.addNeighbour(vertex3);
        vertex4.addNeighbour(vertex2);
        vertex4.addNeighbour(vertex1);
        vertex3.addNeighbour(vertex5);
        vertex2.addNeighbour(vertex6);
        vertex3.addNeighbour(vertex7);
        vertex7.addNeighbour(vertex8);


        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        depthFirstSearch.dfs(root);
        //depthFirstSearch.dfsRecursion(root);
    }

    private void dfs(Vertex root) {
        Deque<Vertex> stack = new LinkedList<>();
        stack.push(root);
        root.setVisited(true);

        while (!stack.isEmpty()) {
            Vertex vertex = stack.pop();
            System.out.println(vertex);
            for (Vertex neightBour : vertex.getNeighbour()) {
                if (!neightBour.isVisited()) {
                    neightBour.setVisited(true);
                    stack.push(neightBour);
                }
            }
        }
    }

    private void dfsRecursion(Vertex root) {
        root.setVisited(true);
        System.out.println(root);
        for (Vertex vertex : root.getNeighbour()) {
            if (!vertex.isVisited()) {
                dfsRecursion(vertex);
            }
        }
    }

}
