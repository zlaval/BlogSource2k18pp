package com.zlrx.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SelectionSort {

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        int[] sortedIntArray = selectionSort(unsortedIntegerArray);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    private static int[] selectionSort(int[] array) {
        int[] sortableArray = Arrays.copyOf(array, array.length);
        for (int i = sortableArray.length - 1; i > 0; i--) {
            int largestElementPosition = 0;
            for (int j = 1; j <= i; j++) {
                if (sortableArray[j] > sortableArray[largestElementPosition]) {
                    largestElementPosition = j;
                }
            }
            int swappable = sortableArray[i];
            sortableArray[i] = sortableArray[largestElementPosition];
            sortableArray[largestElementPosition] = swappable;
        }
        return sortableArray;
    }

}
