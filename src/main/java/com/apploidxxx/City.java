package com.apploidxxx;

import java.util.*;

/**
 * @author Arthur Kupriyanov on 03.10.2020
 */
public class City {
    private final String name;

    /**
     * Contains cities relationships
     *
     * map contains neighbor city and his length to this city
     */
    private final Map<City, Integer> neighbors = new HashMap<>();

    public City(String name) {
        this.name = name;
    }
    public City(City city) {
        this.name = city.getName();
        this.neighbors.putAll(city.getNeighbors());
    }

    /**
     * Add new neighbor city
     * @param city neighbor
     * @param length distance between "this" city and neighbor
     */
    public void addNeighbor(City city, int length) {
        neighbors.put(city, length);
        city.neighbors.put(this, length);   // bidirectional ways
    }

    public int distanceTo(City city) {
        if (neighbors.containsKey(city)) {
           return neighbors.get(city);
        } else {
            throw new IllegalArgumentException("Provided city " + city.getName() + " is not neighbor for " + this.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public Map<City, Integer> getNeighbors() {
        return neighbors;
    }

}
