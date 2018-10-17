package com.zlrx.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BubbleSort {

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        int[] sortedIntArray = bubbleSort(unsortedIntegerArray);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    static int[] bubbleSort(int[] array) {
        int[] sortableArray = Arrays.copyOf(array, array.length);
        for (int i = sortableArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                int actual = sortableArray[j];
                int next = sortableArray[j + 1];
                if (actual > next) {
                    sortableArray[j] = next;
                    sortableArray[j + 1] = actual;
                }
            }
        }
        return sortableArray;
    }

}
