package com.zlrx.algorithms.travellingsalesman;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            City city = new City();
            Repository.getInstance().addCity(city);
        }
        SimulatedAnnealing annealing = new SimulatedAnnealing();
        annealing.simulation();
    }
}
