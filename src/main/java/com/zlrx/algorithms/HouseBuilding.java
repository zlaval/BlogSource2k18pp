package com.zlrx.algorithms;

public class HouseBuilding {

    public static void main(String[] args) {
        HouseBuilding houseBuilding = new HouseBuilding();
        houseBuilding.buildLayer(5);
    }

    //head recursion
    private void buildLayer(int height) {
        if (height == 0) return;
        buildLayer(height - 1);
        System.out.println(height);
    }


}
