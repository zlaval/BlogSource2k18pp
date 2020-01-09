package com.zlrx.algorithms;

public class Recursion {

    public static void main(String[] args) {
        System.out.println(factorial(3));
        System.out.println(factorial(20));
    }

    private static long factorial(long n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }


}
