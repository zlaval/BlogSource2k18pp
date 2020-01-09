package com.zlrx.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class QuickSort {

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        int[] sortedIntArray = quickSort(unsortedIntegerArray);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    private static int[] quickSort(int[] array) {
        int[] sortableArray = Arrays.copyOf(array, array.length);
        sort(sortableArray, 0, sortableArray.length);
        return sortableArray;
    }

    private static void sort(int[] array, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int pivotIndex = partition(array, start, end);
        sort(array, start, pivotIndex);
        sort(array, pivotIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[start]; //Use the first element
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && array[--j] >= pivot) ;
            if (i < j) {
                array[i] = array[j];
            }
            while (i < j && array[++i] <= pivot) ;
            if (i < j) {
                array[j] = array[i];
            }
        }
        array[j] = pivot;
        return j;
    }

}
