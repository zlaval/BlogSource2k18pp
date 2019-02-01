package com.zlrx.algorithms;

public class LargestSubarray {

    public static void main(String[] args) {
        int[] array = new int[]{1, -2, 3, 4, -5, 8};

        int globalMax = array[0];
        int currentMax = array[0];

        for (int i = 1; i < array.length; i++) {
            currentMax = Math.max(array[i], currentMax + array[i]);
            globalMax = currentMax > globalMax ? currentMax : globalMax;
        }

        System.out.println(globalMax);
    }


}
