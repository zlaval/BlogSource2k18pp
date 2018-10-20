package com.zlrx.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class InsertionSort {

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        int[] sortedIntArray = insertionSort(unsortedIntegerArray);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    private static int[] insertionSort(int[] array) {
        int[] sortableArray = Arrays.copyOf(array, array.length);
        for (int i = 1; i < sortableArray.length; i++) {
            int actualElement = sortableArray[i];
            int j;
            for (j = i; j > 0 && sortableArray[j - 1] > actualElement; j--) {
                sortableArray[j] = sortableArray[j - 1];
            }
            sortableArray[j] = actualElement;
        }
        return sortableArray;
    }

}
