package com.zlrx.algorithms;

//O(lengths*origlength)
public class RodCut {

    private int[] lengths;
    private int[] values;
    private int rodLength;
    private int[][] dpTable;

    public static void main(String[] args) {
        int[] values = new int[]{2, 5, 7, 3};
        int[] lengths = new int[]{1, 2, 3, 4};
        RodCut rodCut = new RodCut(lengths, values, 5);
        rodCut.solve();
    }

    public RodCut(int[] lengths, int[] values, int rodLength) {
        this.lengths = lengths;
        this.values = values;
        this.rodLength = rodLength;
        this.dpTable = new int[values.length + 1][rodLength + 1];
    }

    private void solve() {
        for (int row = 1; row <= lengths.length; row++) {
            for (int column = 1; column <= rodLength; column++) {
                int previousMax = dpTable[row - 1][column];
                int actualMax = 0;
                if (column - lengths[row - 1] >= 0) {
                    actualMax = values[row - 1] + dpTable[row][column - lengths[row - 1]];
                }
                int maxValue = Math.max(previousMax, actualMax);
                dpTable[row][column] = maxValue;

            }
        }
        System.out.println("Max: " + dpTable[values.length][rodLength]);
    }


}
