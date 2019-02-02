package com.zlrx.algorithms;

public class NQueen {

    private int[][] chessTable;
    private int numberOfQueens;

    public static void main(String[] args) {
        NQueen nQueen = new NQueen(8);
        nQueen.solve();
    }

    public NQueen(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        chessTable = new int[numberOfQueens][numberOfQueens];
    }

    private void solve() {
        if (setQueens(0)) {
            printQueens();
        } else {
            System.out.println("No solution");
        }
    }

    private void printQueens() {
        for (int i = 0; i < numberOfQueens; i++) {
            for (int j = 0; j < numberOfQueens; j++) {
                if (chessTable[i][j] == 1) {
                    System.out.print("| * ");
                } else {
                    System.out.print("| - ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("--------------------------------");

    }

    private boolean setQueens(int columnIndex) {
        if (columnIndex == numberOfQueens) {
            return true;
        }

        for (int rowIndex = 0; rowIndex < numberOfQueens; rowIndex++) {
            if (isPlaceValid(rowIndex, columnIndex)) {
                chessTable[rowIndex][columnIndex] = 1;
                if (setQueens(columnIndex + 1)) {
                    return true;
                }
                chessTable[rowIndex][columnIndex] = 0;
            }
        }
        return false;

    }

    private boolean isPlaceValid(int rowIndex, int columnIndex) {
        for (int i = 0; i < columnIndex; i++) {
            if (chessTable[rowIndex][i] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = columnIndex; i >= 0 && j >= 0; i--, j--) {
            if (chessTable[i][j] == 1) {
                return false;
            }
        }

        for (int i = rowIndex, j = columnIndex; i < numberOfQueens && j >= 0; i++, j--) {
            if (chessTable[i][j] == 1) {
                return false;
            }
        }

        return true;
    }


}
