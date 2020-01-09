package com.zlrx.algorithms;

//O(logN)
public class BinarySearch {

    private int[] array = new int[]{1, 3, 5, 6, 9, 12, 34, 65, 87, 234, 456, 543, 2435};

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int itemIndex = binarySearch.find();
        System.out.println(itemIndex);
    }

    private int find() {
        return search(0, array.length, 543);
    }

    private int search(int startIndex, int endIndex, int item) {
        if (endIndex < startIndex) {
            System.out.println("Not find value");
            return Integer.MIN_VALUE;
        }

        int middleIndex = (startIndex + endIndex) / 2;
        int actualElement = array[middleIndex];
        if (item == actualElement) {
            return middleIndex;
        } else if (item < actualElement) {
            return search(0, middleIndex - 1, item);
        } else {
            return search(middleIndex + 1, endIndex, item);
        }
    }

}
