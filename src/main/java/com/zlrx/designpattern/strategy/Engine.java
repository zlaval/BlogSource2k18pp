package com.zlrx.designpattern.strategy;

public interface Engine {
    void startEngine();
}

class HasEngine implements Engine {
    @Override
    public void startEngine() {
        System.out.println("The engine is started");
    }
}

class NoEngine implements Engine {
    @Override
    public void startEngine() {
        System.out.println("No engine");
    }
}
