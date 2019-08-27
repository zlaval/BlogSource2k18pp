package com.zlrx.algorithms.travellingsalesman;

public class SimulatedAnnealing {

    private SingleTour bestSolution;

    public void simulation() {
        double temperature = 10_000;
        double coolingRate = 0.003;
        SingleTour currentSolution = new SingleTour();
        currentSolution.generateIndividual();
        System.out.println("Initial solution distance: " + currentSolution.calculateDistance());
        bestSolution = new SingleTour(currentSolution.getTour());
        while (temperature > 1) {
            SingleTour newSolution = new SingleTour(currentSolution.getTour());
            int randomIndex1 = (int) (newSolution.getTourSize() * Math.random());
            City city1 = newSolution.getCity(randomIndex1);
            int randomIndex2 = (int) (newSolution.getTourSize() * Math.random());
            City city2 = newSolution.getCity(randomIndex2);
            newSolution.setCity(randomIndex1, city2);
            newSolution.setCity(randomIndex2, city1);

            double currentEnergy = currentSolution.calculateDistance();
            double newEnergy = newSolution.calculateDistance();

            if (acceptancePropbability(currentEnergy, newEnergy, temperature) > Math.random()) {
                currentSolution = new SingleTour(newSolution.getTour());
            }

            if (currentSolution.calculateDistance() < bestSolution.calculateDistance()) {
                bestSolution = new SingleTour(currentSolution.getTour());
            }
            temperature *= 1 - coolingRate;
        }
        System.out.println("Best approximated solution distance: " + bestSolution.calculateDistance());
        bestSolution.getTour().forEach(c -> System.out.print(c + " -> "));
    }

    private double acceptancePropbability(double currentEnergy, double newEnergy, double temperature) {
        if (currentEnergy > newEnergy)
            return 10;
        return Math.exp((currentEnergy - newEnergy) / temperature);
    }

}
