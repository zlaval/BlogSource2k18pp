package com.zlrx.algorithms;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MergeSort {

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        int[] sortedIntArray = mergeSort(unsortedIntegerArray);
        IntStream.of(sortedIntArray).forEach(System.out::println);
    }

    private static int[] mergeSort(int[] array) {
        int[] sortableArray = Arrays.copyOf(array, array.length);
        int startIndex = 0;
        int endIndex = sortableArray.length;
        sort(sortableArray, startIndex, endIndex);
        return sortableArray;
    }

    private static void sort(int[] array, int start, int end) {
        if (end - start < 2) {
            return;
        }
        int middle = (start + end) / 2;
        sort(array, start, middle);
        sort(array, middle, end);
        merge(array, start, middle, end);
    }

    private static void merge(int[] array, int start, int middle, int end) {
        if (array[middle - 1] <= array[middle]) {
            return;
        }
        int[] tmp = new int[end - start];
        int tmpIndex = 0;
        int i = start;
        int j = middle;
        while (i < middle && j < end) {
            int left = array[i];
            int right = array[j];
            if (left <= right) {
                tmp[tmpIndex] = left;
                i++;
            } else {
                tmp[tmpIndex] = right;
                j++;
            }
            tmpIndex++;
        }
        System.arraycopy(array, i, array, start + tmpIndex, middle - i);
        System.arraycopy(tmp, 0, array, start, tmpIndex);
    }


}
