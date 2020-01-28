package com.zlrx.designpattern.strategy;

//Strategy pattern change the algorithm used by an object at runtime
public class Main {

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle bicycle = new Bicycle();

        car.startEngine();
        bicycle.startEngine();
    }

}
