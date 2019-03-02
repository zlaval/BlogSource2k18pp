package com.zlrx.algorithms;

//max heap
public class Heap {

    private int[] heap;
    private int size;

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(10);
        heap.insert(7);
        heap.insert(23);
        heap.insert(4);
        heap.insert(15);
        heap.insert(18);
        heap.insert(11);
        heap.insert(8);
        heap.insert(6);
        heap.insert(5);
        heap.printHeap();
        heap.delete(1);
        heap.printHeap();
        System.out.println(heap.peek());
        System.out.println();
        heap.sort();
        heap.printHeap();
    }

    public Heap(int capacity) {
        this.heap = new int[capacity];
    }

    public int peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Heap is empty");
        }
        return heap[0];
    }

    public boolean isFull() {
        return size == heap.length;
    }

    public int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }

    private int getRightChildIndex(int index) {
        return 2 * index + 2;
    }

    public void insert(int value) {
        if (isFull()) {
            throw new IndexOutOfBoundsException("Heap is full");
        }
        heap[size] = value;
        heapifyAbove(size);
        size++;
    }

    public int delete(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
        int parentIndex = getParentIndex(index);
        int deletedValue = heap[index];

        heap[index] = heap[size - 1];
        if (index == 0 || heap[index] < heap[parentIndex]) {
            heapifyBelow(index, size - 1);
        } else {
            heapifyAbove(index);
        }

        size--;

        return deletedValue;
    }

    public void sort() {
        int lastHeapIndex = size - 1;
        for (int i = 0; i < lastHeapIndex; i++) {
            int tmp = heap[0];
            heap[0] = heap[lastHeapIndex - i];
            heap[lastHeapIndex - i] = tmp;
            heapifyBelow(0, lastHeapIndex - i - 1);
        }
    }

    private void heapifyBelow(int index, int lastHeapIndex) {
        int childIndexToSwap;
        while (index <= lastHeapIndex) {
            int leftChildIndex = getLeftChildIndex(index);
            int rightChildIndex = getRightChildIndex(index);
            if (leftChildIndex <= lastHeapIndex) {
                if (rightChildIndex > lastHeapIndex) {
                    childIndexToSwap = leftChildIndex;
                } else {
                    childIndexToSwap = heap[leftChildIndex] > heap[rightChildIndex] ? leftChildIndex : rightChildIndex;
                }
                if (heap[index] < heap[childIndexToSwap]) {
                    int tmp = heap[index];
                    heap[index] = heap[childIndexToSwap];
                    heap[childIndexToSwap] = tmp;
                } else {
                    break;
                }
                index = childIndexToSwap;
            } else {
                break;
            }
        }
    }

    private void heapifyAbove(int index) {
        int newValue = heap[index];
        while (index > 0 && newValue > heap[getParentIndex(index)]) {
            heap[index] = heap[getParentIndex(index)];
            index = getParentIndex(index);
        }
        heap[index] = newValue;
    }

    public void printHeap() {
        int k = 1;
        int n = 1;
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
            if ((i + 1) % (k) == 0) {
                n *= 2;
                k += n;
                System.out.println();
            }
        }
        System.out.println();
        System.out.println();
    }

}
