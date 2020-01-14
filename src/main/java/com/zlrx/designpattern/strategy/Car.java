package com.zlrx.designpattern.strategy;

public class Car extends Vehicle {

    public Car() {
        setEngine(new HasEngine());
    }

}
