package com.zlrx.algorithms.naivgc;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    private List<HeapObject> heap = new ArrayList<>();

    public void addHeapObject(HeapObject heapObject) {
        heap.add(heapObject);
    }

    public List<HeapObject> getHeap() {
        return heap;
    }
}
