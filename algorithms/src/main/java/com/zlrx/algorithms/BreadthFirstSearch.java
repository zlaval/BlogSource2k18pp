package com.zlrx.algorithms;

import com.zlrx.algorithms.models.Vertex;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    /****************
     *       O      *
     *      / \     *
     *     4  3     *
     *    / \  \    *
     *   2  1  5    *
     ***************/
    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
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
        bfs.bfs(root);
    }

    private void bfs(Vertex root) {
        Queue<Vertex> queue = new LinkedList<>();
        root.setVisited(true);
        queue.add(root);
        while (!queue.isEmpty()) {
            Vertex actual = queue.remove();
            System.out.println("Actual element is:" + actual);
            actual.getNeighbours().forEach(neighbour -> visitNeighbour(queue, neighbour));
        }
    }

    private void visitNeighbour(Queue<Vertex> queue, Vertex neighbour) {
        if (!neighbour.isVisited()) {
            neighbour.setVisited(true);
            queue.add(neighbour);
        }
    }

}
