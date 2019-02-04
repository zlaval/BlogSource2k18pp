package com.zlrx.algorithms.naivgc;

import java.util.ArrayList;
import java.util.List;

public class Stack {

    private List<HeapObject> pointersToHeapObject = new ArrayList<>();

    public void addHeapObjectPointer(HeapObject heapObject) {
        pointersToHeapObject.add(heapObject);
    }

    public List<HeapObject> getPointersToHeapObject() {
        return pointersToHeapObject;
    }
}
