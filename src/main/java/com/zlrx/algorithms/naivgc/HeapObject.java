package com.zlrx.algorithms.naivgc;

import java.util.ArrayList;
import java.util.List;

public class HeapObject {

    private boolean collectable = true;
    private String name;
    private List<HeapObject> pointersTo = new ArrayList<>();

    public HeapObject(String name) {
        this.name = name;
    }

    public boolean isCollectable() {
        return collectable;
    }

    public void setCollectable(boolean collectable) {
        this.collectable = collectable;
    }

    public List<HeapObject> getPointersTo() {
        return pointersTo;
    }

    public void addPointer(HeapObject pointer) {
        this.pointersTo.add(pointer);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "HeapObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
