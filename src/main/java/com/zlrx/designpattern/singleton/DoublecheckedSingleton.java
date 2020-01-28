package com.zlrx.designpattern.singleton;

public class DoublecheckedSingleton {
    private static volatile DoublecheckedSingleton doublecheckedInitSingleton;

    private DoublecheckedSingleton() {
    }

    public static DoublecheckedSingleton getInstance() {
        if (doublecheckedInitSingleton == null) {
            synchronized (DoublecheckedSingleton.class) {
                if (doublecheckedInitSingleton == null) {
                    doublecheckedInitSingleton = new DoublecheckedSingleton();
                }
            }
        }
        return doublecheckedInitSingleton;
    }

    public void printHashCode() {
        System.out.println(doublecheckedInitSingleton.hashCode());
    }

}
