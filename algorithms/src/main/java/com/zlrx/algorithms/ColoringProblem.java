package com.zlrx.algorithms;


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


public class ColoringProblem {

    private int numberOfVertices;
    private int numberOfColors;
    private int[] colors;
    private int[][] adjacencyMatrix;

    public static void main(String[] args) {
        int[][] adjacencyMatrix = new int[][]{
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 1},
                {1, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}
        };
        ColoringProblem coloringProblem = new ColoringProblem(3, adjacencyMatrix);
        coloringProblem.solve();
    }

    public ColoringProblem(int numberOfColors, int[][] adjacencyMatrix) {
        this.numberOfColors = numberOfColors;
        this.adjacencyMatrix = adjacencyMatrix;
        this.numberOfVertices = adjacencyMatrix.length;
        this.colors = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            colors[i] = -1;
        }
    }

    public void solve() {
        if (solveProblem(0)) {
            showResults();
        } else {
            System.out.println("No solution...");
        }
    }

    private void showResults() {
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.println("Node " + (i) + " has color: " + colors[i]);
        }
    }

    private boolean solveProblem(int nodeIndex) {
        if (nodeIndex == numberOfVertices) {
            return true;
        }

        for (int colorIndex = 0; colorIndex < numberOfColors; colorIndex++) {
            if (isColorValid(nodeIndex, colorIndex)) {
                colors[nodeIndex] = colorIndex;
                if (solveProblem(nodeIndex + 1)) {
                    return true;
                }
                //BACKTRACK
            }
        }

        return false;
    }

    private boolean isColorValid(int nodeIndex, int colorIndex) {
        for (int i = 0; i < numberOfVertices; i++) {
            if (adjacencyMatrix[nodeIndex][i] == 1 && colorIndex == colors[i]) {
                return false;
            }
        }
        return true;
    }


}
