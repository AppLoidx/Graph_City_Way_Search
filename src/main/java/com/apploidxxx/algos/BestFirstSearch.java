package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

import java.util.*;

/**
 * @author Arthur Kupriyanov on 14.10.2020
 */
public class BestFirstSearch implements Algos {
    @Override
    public Path search(City base, City target) {
        LinkedList<City> open = new LinkedList<>();
        Set<City> visited = new HashSet<>();
        Path path = new Path(base);
        open.add(base);
        visited.add(base);
        while (!open.isEmpty()) {
            City cursor = open.poll();

            if (cursor != null) {
                if (!cursor.equals(base))
                    path.addPath(cursor);

                if (cursor.equals(target)) {
                    return path;
                }

                LinkedList<City> neighbors = new LinkedList<>(cursor.getNeighbors().keySet());
                neighbors.sort(Comparator.comparingInt(c -> c.distanceTo(cursor)));

                for (City neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        open.add(neighbor);
                    }
                }

            }
        }

        return path;
    }

    @Override
    public String name() {
        return "Best First Search";
    }
}
