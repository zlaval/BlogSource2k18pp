package com.zlrx.algorithms;

public class KnapSack {

    private int numberOfItems;
    private int capacityOfKnapsack;
    private int totalBenefit;
    private int[] weights;
    private int[] values;
    private int[][] knapSackTable;


    public static void main(String[] args) {
        int[] weights = new int[]{4, 2, 3, 1};
        int[] values = new int[]{10, 4, 7, 2};

        KnapSack knapSack = new KnapSack(4, 5, weights, values);
        knapSack.solve();
    }

    public KnapSack(int numberOfItems, int capacityOfKnapsack, int[] weights, int[] values) {
        this.numberOfItems = numberOfItems;
        this.capacityOfKnapsack = capacityOfKnapsack;
        this.weights = weights;
        this.values = values;
        this.knapSackTable = new int[numberOfItems + 1][capacityOfKnapsack + 1];
    }

    //O(numberOfItems*capacityOfKnapsack)
    public void solve() {
        for (int row = 1; row <= numberOfItems; row++) {
            int actualItemWeight = weights[row - 1];
            int actualItemValue = values[row - 1];
            for (int column = 1; column <= capacityOfKnapsack; column++) {
                int left = knapSackTable[row - 1][column];
                int right = 0;
                if (column - actualItemWeight >= 0) {
                    right = actualItemValue + knapSackTable[row - 1][column - actualItemWeight];
                }
                int newTableValue = Math.max(left, right);
                knapSackTable[row][column] = newTableValue;
            }
        }
        totalBenefit = knapSackTable[numberOfItems][capacityOfKnapsack];
        System.out.println(totalBenefit);
        showSelectedItems();
    }

    public void showSelectedItems() {
        for (int n = numberOfItems, c = capacityOfKnapsack; n > 0; n--) {
            if (knapSackTable[n][c] != 0 && knapSackTable[n][c] != knapSackTable[n - 1][c]) {
                System.out.println("Take item: #" + n);
                c -= weights[n - 1];
            }

        }

    }


}
