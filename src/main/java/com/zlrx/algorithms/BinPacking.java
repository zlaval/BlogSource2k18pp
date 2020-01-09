package com.zlrx.algorithms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BinPacking {

    private List<Bin> bins;
    private List<Integer> items;
    private int binCapacity;
    private int binCounter = 1;

    public static void main(String[] args) {
        var items = Arrays.asList(3, 5, 1, 7, 5, 9, 2, 4, 8);
        var binPacking = new BinPacking(items, 13);
        binPacking.solve();
    }

    private BinPacking(List<Integer> items, int binCapacity) {
        this.items = items;
        this.binCapacity = binCapacity;
        bins = new ArrayList<>();
    }

    private void solve() {
        sortItemsDesc();
        var largestItem = findLargestItem();
        if (largestItem > binCapacity) {
            System.out.println("No solution");
            return;
        }
        initializeFirstBin();
        packIntoBins();
        printResult();
    }

    private void sortItemsDesc() {
        items = items.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private Integer findLargestItem() {
        return items.stream().findFirst().orElseThrow();
    }

    private void initializeFirstBin() {
        bins.add(new Bin(binCounter, binCapacity));
    }

    private void printResult() {
        bins.forEach(System.out::println);
    }

    private void packIntoBins() {
        items.forEach(this::tryIntoBins);
    }

    private void tryIntoBins(Integer i) {
        var currentBin = 0;
        var isOk = false;
        while (!isOk) {
            if (currentBin == bins.size()) {
                packIntoNewBin(i);
                isOk = true;
            } else if (bins.get(currentBin).put(i)) {
                isOk = true;
            } else {
                currentBin++;
            }
        }
    }

    private void packIntoNewBin(Integer i) {
        var newBin = new Bin(++binCounter, binCapacity);
        newBin.put(i);
        bins.add(newBin);
    }

    @Getter
    @Setter
    @ToString
    private class Bin {
        private int id;
        private int capacity;
        private int currentSize;
        private List<Integer> items;

        public Bin(int id, int capacity) {
            this.id = id;
            this.capacity = capacity;
            this.items = new ArrayList<>();
        }

        public boolean put(Integer item) {
            if (this.currentSize + item > capacity) {
                return false;
            }
            items.add(item);
            currentSize += item;
            return true;
        }

    }

}
