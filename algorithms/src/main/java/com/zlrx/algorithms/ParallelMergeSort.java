package com.zlrx.algorithms;

import java.util.Arrays;

//TODO refactor
public class ParallelMergeSort {

    private int[] numbers;
    private int[] tmp;

    public static void main(String[] args) {
        int[] unsortedIntegerArray = new int[]{20, 30, 10, -23, 9, 63, 1, -42, 0};
        ParallelMergeSort sort = new ParallelMergeSort(unsortedIntegerArray);
        Thread worker = sort.parallelMergeSort(0, unsortedIntegerArray.length - 1, 4);
        worker.start();
        try {
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Arrays.stream(sort.getNumbers()).forEach(System.out::println);

    }

    public ParallelMergeSort(int[] numbers) {
        this.numbers = numbers;
        this.tmp = new int[numbers.length];
    }

    private Thread parallelMergeSort(int low, int high, int numOfThreads) {
        return new Thread(() -> this.divide(low, high, numOfThreads / 2));
    }


    private void divide(int low, int high, int numOfThreads) {
        if (numOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }
        int middleIndex = (low + high) / 2;
        Thread leftSorter = parallelMergeSort(low, middleIndex, numOfThreads);
        Thread rightSorter = parallelMergeSort(middleIndex + 1, high, numOfThreads);
        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        merge(low, middleIndex, high);
    }


    private void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }
        int middle = (low + high) / 2;
        mergeSort(low, middle);
        mergeSort(middle + 1, high);
        merge(low, middle, high);
    }

    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tmp[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while ((i <= middle) && j <= high) {
            if (tmp[i] <= tmp[j]) {
                numbers[k] = tmp[i++];
            } else {
                numbers[k] = tmp[j++];
            }
            k++;
        }
        while (i <= middle) {
            numbers[k++] = tmp[i++];
        }
        while (j <= high) {
            numbers[k++] = tmp[j++];
        }

    }

    public int[] getNumbers() {
        return numbers;
    }
}
