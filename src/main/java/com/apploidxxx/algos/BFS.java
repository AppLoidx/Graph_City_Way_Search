package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

import java.util.*;

/**
 * @author Arthur Kupriyanov on 03.10.2020
 */
public class BFS implements Algos {
    @Override
    public Path search(City base, City target) {

        Queue<City> cityQueue = new ArrayDeque<>();
        cityQueue.add(base);

        Set<City> visited = new HashSet<>();
        visited.add(base);

        // Algorithm's path
        Path path = new Path(base);

        while (!cityQueue.isEmpty()) {
            City cursor = cityQueue.poll();
            for (City neighborCity : cursor.getNeighbors().keySet()) {

                if (!visited.contains(neighborCity)) {
                    path.addPath(neighborCity);
                    cityQueue.add(neighborCity);
                    visited.add(neighborCity);
                }

                if (neighborCity.equals(target)) {
                    return path;
                    // we don't need to check next iteration, because we already found our city
                    // but if we remove this target-check we will get full algorithm's path
                }
            }
        }

        return path;
    }

    @Override
    public String name() {
        return "BFS";
    }
}
