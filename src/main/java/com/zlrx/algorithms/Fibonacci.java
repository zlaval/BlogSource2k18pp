package com.zlrx.algorithms;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private Map<Integer, Long> memoizeTable;

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        long start1 = System.currentTimeMillis();
        fibonacci.calculateFibonacci(15);
        long start2 = System.currentTimeMillis();
        System.out.println(fibonacci.fibonacciDP(90));

        long end = System.currentTimeMillis();

        System.out.println("Recursive: " + (start2 - start1) + " ms");
        System.out.println("DP: " + (end - start2) + " ms");

    }

    public Fibonacci() {
        this.memoizeTable = new HashMap<>();
        memoizeTable.put(0, 0L);
        memoizeTable.put(1, 1L);
    }

    public void calculateFibonacci(int number) {
        System.out.println(fibonacci(number));
    }

    public int fibonacci(int number) {
        if (number == 0 || number == 1) {
            return number;
        }
        int left = fibonacci(number - 1);
        int right = fibonacci(number - 2);
        return left + right;
    }

    public long fibonacciDP(int number) {
        if (memoizeTable.containsKey(number)) {
            return memoizeTable.get(number);
        }

        memoizeTable.put(number - 1, fibonacciDP(number - 1));
        memoizeTable.put(number - 2, fibonacciDP(number - 2));
        long calculatedNumber = memoizeTable.get(number - 1) + memoizeTable.get(number - 2);
        memoizeTable.put(number, calculatedNumber);
        return calculatedNumber;

    }


}
