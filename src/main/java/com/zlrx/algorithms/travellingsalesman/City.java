package com.zlrx.algorithms.travellingsalesman;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class City {

    private int x;
    private int y;

    public City() {
        this.x = (int) (Math.random() * 100);
        this.y = (int) (Math.random() * 100);
    }

    public double distanceTo(City other) {
        int xDistance = Math.abs(x - other.getX());
        int yDistance = Math.abs(y - other.getY());
        return Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
    }

}
