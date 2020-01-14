package com.zlrx.designpattern.observable;

import java.util.ArrayList;
import java.util.List;

public class Company implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int maxSalary = 1000;

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        observers.forEach(o -> o.updateSalary(maxSalary));
    }

    public void updateMaxSalary(int salary) {
        this.maxSalary = salary;
        notifyAllObserver();
    }
}
