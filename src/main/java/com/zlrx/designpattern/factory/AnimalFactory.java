package com.zlrx.designpattern.factory;

public class AnimalFactory {

    public Animal createAnimal(String species) {
        if (species.equals("Lion")) {
            return new Lion();
        } else if (species.equals("Whale")) {
            return new Whale();
        }
        throw new UnsupportedOperationException("I dont know that kind of animal");
    }

}
