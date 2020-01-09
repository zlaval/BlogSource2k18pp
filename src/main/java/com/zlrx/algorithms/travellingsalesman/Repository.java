package com.zlrx.algorithms.travellingsalesman;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private List<City> cities = new ArrayList<>();

    private Repository() {

    }

    private static class InstanceHolder {
        private static final Repository INSTANCE = new Repository();
    }

    public static Repository getInstance() {
        return InstanceHolder.INSTANCE;
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public City getCity(int index) {
        return cities.get(index);
    }

    public int numberOfCities() {
        return cities.size();
    }

}
