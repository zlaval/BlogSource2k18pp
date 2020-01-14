package com.zlrx.designpattern.factory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal {

    private String name;

    public void introduce() {
        System.out.println("Hello my name is " + name);
    }

}
