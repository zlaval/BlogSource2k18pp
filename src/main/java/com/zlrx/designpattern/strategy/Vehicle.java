package com.zlrx.designpattern.strategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private String name;
    private int weight;
    private int speed;
    private Engine engine;

    public int accelerate(int startSpeed, int time) {
        int accelerate = (speed - startSpeed) / time;
        return accelerate;
    }

    public void startEngine() {
        engine.startEngine();
    }
}
