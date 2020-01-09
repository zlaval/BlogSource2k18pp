package com.zlrx.algorithms;

public class HanoiTower {

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        hanoiTower.solve(3, 'a', 'b', 'c');

    }

    private void solve(int numberOfPlates, char rodFrom, char middleRod, char rodTo) {
        if (numberOfPlates == 1) {
            System.out.println("plate 1 from " + rodFrom + " to " + rodTo);
            return;
        }

        solve(numberOfPlates - 1, rodFrom, rodTo, middleRod);
        System.out.println("plate " + numberOfPlates + " from " + rodFrom + " to " + rodTo);
        solve(numberOfPlates - 1, middleRod, rodFrom, rodTo);
    }


}
