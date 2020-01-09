package com.zlrx.algorithms;

//O(m^n)=9^blank fields
public class Sudoku {

    private final int boardSize = 9;
    private final int minNumber = 1;
    private final int maxNumber = 9;
    private final int boxSize = 3;

    private int[][] table;

    public static void main(String[] args) {
        int[][] table = new int[][]{
                {5, 1, 8,/**/0, 3, 0,/**/0, 0, 2},
                {3, 4, 0,/**/0, 0, 0,/**/0, 0, 0},
                {0, 6, 0,/**/0, 0, 9,/**/0, 0, 0},
                //--------------------------------/
                {0, 2, 0,/**/0, 0, 0,/**/7, 0, 4},
                {4, 0, 0,/**/7, 0, 2,/**/0, 0, 1},
                {1, 0, 6,/**/0, 0, 0,/**/0, 2, 0},
                //--------------------------------/
                {0, 0, 0,/**/5, 0, 0,/**/0, 6, 0},
                {0, 0, 0,/**/0, 0, 0,/**/0, 3, 9},
                {9, 0, 0,/**/0, 6, 0,/**/5, 4, 7},
        };
        Sudoku sudoku = new Sudoku(table);
        sudoku.solveSudoku();
    }

    public Sudoku(int[][] table) {
        this.table = table;
    }

    private void solveSudoku() {
        if (solve(0, 0)) {
            showResult();
        } else {
            System.out.println("No solution..");
        }
    }

    private boolean solve(int rowIndex, int columnIndex) {
        if (rowIndex == boardSize && ++columnIndex == boardSize) {
            return true;
        }
        if (rowIndex == boardSize) {
            rowIndex = 0;
        }
        if (table[rowIndex][columnIndex] != 0) {
            return solve(rowIndex + 1, columnIndex);
        }
        for (int number = minNumber; number <= maxNumber; number++) {
            if (valid(rowIndex, columnIndex, number)) {
                table[rowIndex][columnIndex] = number;
                if (solve(rowIndex + 1, columnIndex)) {
                    return true;
                }
            }
            table[rowIndex][columnIndex] = 0;
        }
        return false;
    }

    private boolean valid(int rowIndex, int columnIndex, int number) {
        for (int row = 0; row < boardSize; row++) {
            if (table[row][columnIndex] == number) {
                return false;
            }
        }
        for (int column = 0; column < boardSize; column++) {
            if (table[rowIndex][column] == number) {
                return false;
            }
        }

        int rowOffset = (rowIndex / 3) * boxSize;
        int columnOffset = (columnIndex / 3) * boxSize;

        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                if (number == table[i + rowOffset][j + columnOffset]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showResult() {
        System.out.println(" *************************************");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                String separator = j % 3 == 0 ? " * " : " | ";
                System.out.print(separator + table[i][j]);
            }
            System.out.println(" * ");

            if ((i + 1) % 3 == 0) {
                System.out.println(" *************************************");
            } else {
                System.out.println(" *-----------*-----------*-----------*");
            }
        }
    }

}
