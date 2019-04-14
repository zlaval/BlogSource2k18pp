package com.zlrx.algorithms.travellingsalesman;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class SingleTour {

    private List<City> tour;
    private double distance = 0;

    public SingleTour() {
        this.tour = new ArrayList<>();
        for (int i = 0; i < Repository.getInstance().numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public SingleTour(List<City> currentTour) {
        this.tour = new ArrayList<>(currentTour);
    }

    public void generateIndividual() {
        for (int i = 0; i < Repository.getInstance().numberOfCities(); i++) {
            setCity(i, Repository.getInstance().getCity(i));
        }
        Collections.shuffle(tour);
    }

    public void setCity(int i, City city) {
        this.tour.set(i, city);
        this.distance = 0;
    }

    public double calculateDistance() {
        if (distance == 0) {
            for (int i = 0; i < getTourSize(); i++) {
                City from = getCity(i);
                City destination;
                if (i + 1 < getTourSize()) {
                    destination = getCity(i + 1);
                } else {
                    destination = getCity(0);
                }
                distance += from.distanceTo(destination);
            }
        }
        return distance;
    }

    public City getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    public int getTourSize() {
        return tour.size();
    }

}
