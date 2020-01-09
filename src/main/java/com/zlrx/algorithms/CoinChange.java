package com.zlrx.algorithms;

@SuppressWarnings("ALL")
public class CoinChange {

    private int amount;
    private int[] coins;
    private int[][] dpTable;

    public static void main(String[] args) {
        int[] coins = new int[]{1, 4, 7};
        CoinChange coinChange = new CoinChange(15, coins);
        int result = coinChange.solveRecursive(4, coins, 0);
        System.out.println(result);
        coinChange.solve();
    }

    public CoinChange(int amount, int[] coins) {
        this.amount = amount;
        this.coins = coins;
        this.dpTable = new int[coins.length + 1][amount + 1];
    }

    //O(2^coins.length)
    public int solveRecursive(int m, int[] v, int index) {
        if (m < 0) {
            return 0;
        }
        if (m == 0) {
            return 1;
        }
        if (v.length == index) {
            return 0;
        }
        return solveRecursive(m - v[index], v, index) + solveRecursive(m, v, index + 1);
    }


    //O(coins.length*amount)
    public void solve() {
        for (int i = 0; i < coins.length; i++) {
            dpTable[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] <= j) {
                    dpTable[i][j] = dpTable[i - 1][j] + dpTable[i][j - coins[i - 1]];
                } else {
                    dpTable[i][j] = dpTable[i - 1][j];
                }
            }
        }
        System.out.println("Result: " + dpTable[coins.length][amount]);
    }

}
