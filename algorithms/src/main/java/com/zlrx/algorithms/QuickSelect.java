package com.zlrx.algorithms;

import java.util.Random;

public class QuickSelect {

    private int[] array;
    private Random random;

    public static void main(String[] args) {
        int[] array = new int[]{2, 6, 4, 8, -3, 5, 9};
        QuickSelect quickSelect = new QuickSelect(array);
        int secondGreater = quickSelect.select(2);
        System.out.println(secondGreater);
    }

    public QuickSelect(int[] array) {
        this.array = array;
        random = new Random();
    }

    public int select(int k) {
        return select(0, array.length - 1, k - 1);
    }

    private int select(int firstIndex, int lastIndex, int k) {
        int pivot = partition(firstIndex, lastIndex);
        if (pivot > k) {
            return select(firstIndex, pivot - 1, k);
        } else if (pivot < k) {
            return select(pivot + 1, lastIndex, k);
        }
        return array[k];
    }

    private int partition(int firstIndex, int lastIndex) {
        int pivot = random.nextInt(lastIndex - firstIndex + 1) + firstIndex;
        swap(lastIndex, pivot);
        for (int i = 0; i < lastIndex; i++) {
            if (array[i] > array[lastIndex]) {
                swap(i, firstIndex);
                firstIndex++;
            }
        }
        swap(firstIndex, lastIndex);
        return firstIndex;
    }

    private void swap(int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;

    }

}
