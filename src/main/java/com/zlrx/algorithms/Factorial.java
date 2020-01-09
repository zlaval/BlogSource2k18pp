package com.zlrx.algorithms;

public class Factorial {
    public static void main(String[] args) {
        System.out.println(factorial(5));
        System.out.println(tailRecFactorial(5));
    }

    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    private static int tailRecFactorial(int number) {
        return solve(1, number);
    }

    private static int solve(int acc, int n) {
        if (n == 0) {
            return acc;
        }
        return solve(acc * n, n - 1);
    }

}
