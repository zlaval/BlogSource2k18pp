package com.zlrx.algorithms;

public class GCD {

    public static void main(String[] args) {
        System.out.println(iterative(32, 40));
        System.out.println(recursive(32, 40));
    }

    public static int iterative(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    public static int recursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        return recursive(b, a % b);
    }

}
