package com.zlrx.algorithms;

import java.util.stream.IntStream;

public class CountingSort {

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{8, 1, 10, 3, 10, 12, 1, 6, 1, 10, 1, 2};
        int[] sortedIntArray = countingSort(unsortedIntegerArray, 0, 12);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    private static int[] countingSort(int[] array, int min, int max) {
        int[] countArray = new int[(max - min + 1)];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        int j = 0;
        for (int i = min; i <= max; i++) {
            while (countArray[i - min] > 0) {
                array[j++] = i;
                countArray[i - min]--;
            }
        }
        return array;
    }


}
