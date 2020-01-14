package com.zlrx.designpattern.factory;

//Encapsulate object creation, define the class of an object at runtime
public class Main {

    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();
        Animal lion = factory.createAnimal("Lion");
        Animal whale = factory.createAnimal("Whale");
        lion.introduce();
        whale.introduce();
    }

}
