package com.zlrx.algorithms;

public class KnightsTour {

    private int borardSize;
    private int numberOfMoves = 8; // knight has 8 possible moves
    private int[][] chessBoard;
    private int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};//+ right,- left
    private int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};//+ up,- down

    public static void main(String[] args) {
        KnightsTour knightsTour = new KnightsTour(6);
        knightsTour.solveTour();
    }

    public KnightsTour(int borardSize) {
        this.chessBoard = new int[borardSize][borardSize];
        this.borardSize = borardSize;
    }

    private void solveTour() {
        chessBoard[0][0] = 1;
        if (solve(2, 0, 0)) {
            printSolution();
        } else {
            System.out.println("No solution");
        }
    }

    private void printSolution() {
        for (int row = 0; row < borardSize; row++) {
            for (int column = 0; column < borardSize; column++) {
                int step = chessBoard[row][column];
                String place = step < 10 ? " " : "";
                System.out.print(" |" + place + step);
            }
            System.out.print(" |");
            System.out.println();
            System.out.print(" ");
            for (int i = 0; i < borardSize; i++) {
                System.out.print("----");
            }
            System.out.println();
        }
    }

    private boolean solve(int stepCount, int x, int y) {
        if (stepCount == borardSize * borardSize + 1) {
            return true;
        }
        for (int move = 0; move < numberOfMoves; move++) {
            int nextX = x + xMoves[move];
            int nextY = y + yMoves[move];
            if (isStepValid(nextX, nextY)) {
                chessBoard[nextX][nextY] = stepCount;
                if (solve(stepCount + 1, nextX, nextY)) {
                    return true;
                }
                chessBoard[nextX][nextY] = 0;
            }
        }
        return false;
    }

    private boolean isStepValid(int nextX, int nextY) {
        if (nextX < 0 || nextX >= borardSize) {
            return false;
        }
        if (nextY < 0 || nextY >= borardSize) {
            return false;
        }
        return chessBoard[nextX][nextY] == 0;
    }

}
