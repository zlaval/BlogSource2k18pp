package com.zlrx.designpattern.observable;

public interface Subject {
    void register(Observer observer);

    void unregister(Observer observer);

    void notifyAllObserver();
}
