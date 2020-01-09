package com.zlrx.algorithms;

public class TwoEggsProblem {

    private final int numberOfEggs;

    private final int numberOfFloors;

    private final int[][] dpTable;

    /*
            n(n+1)
            ------- = 100 ~ 13,7
               2
     */
    public static void main(String[] args) {
        TwoEggsProblem twoEggsProblem = new TwoEggsProblem(2, 100);
        int result = twoEggsProblem.solve();
        System.out.println(result);
    }

    public TwoEggsProblem(int numberOfEggs, int numberOfFloors) {
        this.numberOfEggs = numberOfEggs;
        this.numberOfFloors = numberOfFloors;
        dpTable = new int[numberOfEggs + 1][numberOfFloors + 1];
    }


    public int solve() {
        dpTable[0][0] = 1;
        for (int i = 0; i <= numberOfFloors; i++) {
            dpTable[1][i] = i;
        }

        for (int egg = 2; egg <= numberOfEggs; egg++) {
            for (int floor = 1; floor <= numberOfFloors; floor++) {
                dpTable[egg][floor] = Integer.MAX_VALUE;
                for (int i = 1; i <= floor; i++) {
                    int maxDropps = 1 + Math.max(dpTable[egg - 1][i - 1], dpTable[egg][floor - i]);
                    if (maxDropps < dpTable[egg][floor]) {
                        dpTable[egg][floor] = maxDropps;
                    }
                }
            }


        }


        return dpTable[numberOfEggs][numberOfFloors];
    }


}
