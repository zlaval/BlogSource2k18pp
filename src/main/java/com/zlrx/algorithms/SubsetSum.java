package com.zlrx.algorithms;

public class SubsetSum {

    private int[] array;
    private int sum;
    private boolean[][] dpTable;

    public static void main(String[] args) {
        int[] array = new int[]{5, 2, 8, 6, 3};
        SubsetSum subsetSum = new SubsetSum(array, 12);
        subsetSum.solve();
        System.out.println(subsetSum.recursive(12, 0));
    }

    public SubsetSum(int[] array, int sum) {
        this.array = array;
        this.sum = sum;
        dpTable = new boolean[array.length + 1][sum + 1];
    }

    private boolean recursive(int s, int index) {
        if (s < 0) {
            return false;
        }
        if (s == 0) {
            return true;
        }
        if (index >= array.length) {
            return false;
        }
        return recursive(s - array[index], index + 1) || recursive(s, index + 1);
    }

    //O(sum*array.length)
    private void solve() {
        for (int row = 0; row <= array.length; row++) {
            dpTable[row][0] = true;
        }

        for (int row = 1; row <= array.length; row++) {
            for (int column = 1; column <= sum; column++) {
                if (column < array[row - 1]) {
                    dpTable[row][column] = dpTable[row - 1][column];
                } else {
                    if (dpTable[row - 1][column]) {
                        dpTable[row][column] = true;
                    } else {
                        dpTable[row][column] = dpTable[row - 1][column - array[row - 1]];
                    }
                }
            }
        }
        System.out.println(dpTable[array.length][sum]);
    }

}
