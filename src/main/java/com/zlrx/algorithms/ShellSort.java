package com.zlrx.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ShellSort {


    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        int[] sortedIntArray = shellSort(unsortedIntegerArray);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    private static int[] shellSort(int[] array) {
        int[] sortableArray = Arrays.copyOf(array, array.length);

        for (int gap = sortableArray.length / 2; gap > 0; gap--) {
            for (int i = gap; i < sortableArray.length; i++) {
                int newElement = sortableArray[i];
                int j = i;
                while (j >= gap && sortableArray[j - gap] > newElement) {
                    sortableArray[j] = sortableArray[j - gap];
                    j -= gap;
                }
                sortableArray[j] = newElement;
            }
        }

        return sortableArray;
    }

}
