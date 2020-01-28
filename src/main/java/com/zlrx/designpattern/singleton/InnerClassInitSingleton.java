package com.zlrx.designpattern.singleton;

public class InnerClassInitSingleton {

    private InnerClassInitSingleton() {
    }

    private static class InitHelper {
        private static final InnerClassInitSingleton innerClassInitSingleton
                = new InnerClassInitSingleton();
    }

    public static InnerClassInitSingleton getInstance() {
        return InitHelper.innerClassInitSingleton;
    }

    public void printHashCode() {
        System.out.println(InitHelper.innerClassInitSingleton.hashCode());
    }

}
