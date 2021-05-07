package com.zlrx.algorithms;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static com.zlrx.algorithms.GeneticAlgorithm.SOLUTION_SEQUENCE;

class Individual {
    private int[] genes;
    private int fitness;
    private Random random;

    public Individual() {
        this.genes = new int[GeneticAlgorithm.CHROMOSOME_LENGTH];
        this.random = new Random(System.nanoTime());
        generateIndividual();
    }

    private void generateIndividual() {
        for (int i = 0; i < genes.length; ++i) {
            genes[i] = random.nextInt(GeneticAlgorithm.CHROMOSOME_LENGTH);
        }
    }

    public int getFitness() {
        if (fitness == 0) {
            for (int i = 0; i < genes.length; ++i) {
                if (Objects.equals(genes[i], SOLUTION_SEQUENCE[i])) {
                    fitness++;
                }
            }
        }
        return fitness;
    }

    public int getGene(int index) {
        return genes[index];
    }

    public void setGene(int value, int index) {
        genes[index] = value;
        fitness = 0;
    }

    @Override
    public String toString() {
        return Arrays.stream(genes).mapToObj(String::valueOf).collect(Collectors.joining("-", "[", "]"));
    }
}

class Population {

    private List<Individual> individuals;

    public Population(int populationSize) {
        individuals = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i++) {
            individuals.add(null);
        }
    }

    public void initPopulation() {
        for (int i = 0; i < individuals.size(); i++) {
            var individual = new Individual();
            saveIndividual(i, individual);
        }
    }

    public void saveIndividual(int index, Individual value) {
        individuals.set(index, value);
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public int size() {
        return individuals.size();
    }

    public Individual getFittest() {
        return individuals.stream().max(Comparator.comparing(Individual::getFitness)).orElseThrow();
    }

}

public class GeneticAlgorithm {

    static final int[] SOLUTION_SEQUENCE = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static final double CROSSOVER_RATE = 0.05;
    static final double MUTATION_RATE = 0.01;
    static final int TOURNAMENT_SIZE = 5;
    static final int MAX_FITNESS = 10;
    static final int CHROMOSOME_LENGTH = 10;

    private Random random;

    public static void main(String[] args) {
        GeneticAlgorithm algorithm = new GeneticAlgorithm();
        Population population = new Population(100);
        population.initPopulation();
        int generationCounter = 0;
        while (population.getFittest().getFitness() != MAX_FITNESS) {
            generationCounter++;
            population = algorithm.evolvePopulation(population);
            System.out.println("Generation " + generationCounter + " fittest is " + population.getFittest().getFitness());
            System.out.println(population.getFittest());
            System.out.println();
        }
    }

    public GeneticAlgorithm() {
        random = new Random(System.nanoTime());
    }

    public Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.size());

        for (int i = 0; i < population.size(); i++) {
            Individual first = randomSelection(population);
            Individual second = randomSelection(population);
            Individual child = crossOver(first, second);
            mutate(child);
            newPopulation.saveIndividual(i, child);
        }

        return newPopulation;
    }

    private void mutate(Individual individual) {
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (Math.random() <= MUTATION_RATE) {
                individual.setGene(i, random.nextInt(10));
            }
        }
    }

    private Individual crossOver(Individual firstParent, Individual secondParent) {
        Individual child = new Individual();
        for (int i = 0; i < CHROMOSOME_LENGTH; i++) {
            if (Math.random() <= CROSSOVER_RATE) {
                child.setGene(i, firstParent.getGene(i));
            } else {
                child.setGene(i, secondParent.getGene(i));
            }
        }
        return child;
    }

    private Individual randomSelection(Population population) {
        Population newPopulation = new Population(TOURNAMENT_SIZE);

        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomIndex = (int) (Math.random() * population.size());
            newPopulation.saveIndividual(i, population.getIndividual(randomIndex));
        }
        return newPopulation.getFittest();
    }

}
