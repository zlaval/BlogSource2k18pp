package com.zlrx.algorithms;

public class HamiltonianPath {

    private int numberOfVertices;
    private int[] path;
    private int[][] graph;

    /**
     * Graph:
     * <p>
     * 0-----1--
     * |\   /|  \
     * |  2  |   4
     * |/   \| /
     * 3-----5/
     * <p>
     * <p>
     * -||0|1|2|3|4|5
     * -------------
     * 0||0|1|1|1|0|0
     * 1||1|0|1|0|1|1
     * 2||1|1|0|1|0|1
     * 3||1|0|1|0|0|1
     * 4||0|1|0|0|0|1
     * 5||0|1|1|1|1|0
     */
    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        HamiltonianPath hamiltonianPath = new HamiltonianPath(graph);
        hamiltonianPath.solve();
    }

    public HamiltonianPath(int[][] graph) {
        this.graph = graph;
        this.path = new int[graph.length];
        this.numberOfVertices = graph.length;
        this.path[0] = 0;
    }

    public void solve() {
        if (findSolution(1)) {
            showPath();
        } else {
            System.out.println("No solution");
        }
    }

    private boolean findSolution(int position) {
        if (position == numberOfVertices) {
            if (graph[path[position - 1]][path[0]] == 1) {
                return true;
            }
            return false;
        }

        for (int v = 1; v < numberOfVertices; v++) {
            if (isConnectedAndNotVisited(v, position)) {
                path[position] = v;
                if (findSolution(position + 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isConnectedAndNotVisited(int vertexIndex, int actualPosition) {
        if (graph[path[actualPosition - 1]][vertexIndex] == 0) {
            return false;
        }
        for (int i = 0; i < actualPosition; i++) {
            if (path[i] == vertexIndex) {
                return false;
            }
        }
        return true;
    }

    private void showPath() {
        System.out.println("The Hamiltonian cycle: ");
        for (int a : path) {
            System.out.print(a + " -> ");
        }
        System.out.print(path[0]);
    }

}
