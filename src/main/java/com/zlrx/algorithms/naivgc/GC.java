package com.zlrx.algorithms.naivgc;

public class GC {

    private Heap heap;
    private Stack stack;


    public static void main(String[] args) {
        Heap heap = new Heap();
        Stack stack = new Stack();

        HeapObject a = new HeapObject("A");
        HeapObject b = new HeapObject("B");
        HeapObject c = new HeapObject("C");
        HeapObject d = new HeapObject("D");
        HeapObject e = new HeapObject("E");
        HeapObject f = new HeapObject("F");
        HeapObject g = new HeapObject("G");
        HeapObject h = new HeapObject("H");
        HeapObject i = new HeapObject("I");
        HeapObject j = new HeapObject("J");
        HeapObject k = new HeapObject("K");
        HeapObject l = new HeapObject("L");

        heap.addHeapObject(a);
        heap.addHeapObject(b);
        heap.addHeapObject(c);
        heap.addHeapObject(d);
        heap.addHeapObject(e);
        heap.addHeapObject(f);
        heap.addHeapObject(g);
        heap.addHeapObject(h);
        heap.addHeapObject(i);
        heap.addHeapObject(j);
        heap.addHeapObject(k);
        heap.addHeapObject(l);

        stack.addHeapObjectPointer(a);
        stack.addHeapObjectPointer(k);

        a.addPointer(b);
        b.addPointer(c);
        b.addPointer(e);
        c.addPointer(d);
        e.addPointer(f);
        g.addPointer(e);
        g.addPointer(h);
        i.addPointer(h);
        i.addPointer(j);
        k.addPointer(j);
        k.addPointer(l);
        l.addPointer(l);

        GC gc = new GC(heap, stack);
        gc.cleanMemory();
        gc.printHeapAfterGC();
    }

    public GC(Heap heap, Stack stack) {
        this.heap = heap;
        this.stack = stack;
    }

    private void cleanMemory() {
        stack.getPointersToHeapObject().forEach(
                this::markReferencedHeapObject
        );
        heap.getHeap().removeIf(HeapObject::isCollectable);
    }

    private void markReferencedHeapObject(HeapObject heapObject) {
        if (heapObject.isCollectable()) {
            heapObject.setCollectable(false);
            heapObject.getPointersTo().forEach(this::markReferencedHeapObject);
        }
    }

    private void printHeapAfterGC() {
        heap.getHeap().forEach(System.out::println);
    }

}
