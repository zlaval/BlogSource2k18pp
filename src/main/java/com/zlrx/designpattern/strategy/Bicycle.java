package com.zlrx.designpattern.strategy;

public class Bicycle extends Vehicle {
    public Bicycle() {
        setEngine(new NoEngine());
    }
}
