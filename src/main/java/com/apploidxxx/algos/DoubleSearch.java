package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author Arthur Kupriyanov on 07.10.2020
 */
public class DoubleSearch implements Algos {
    @Override
    public Path search(City base, City target) {

        Queue<City> cities1 = new ArrayDeque<>();
        Queue<City> cities2 = new ArrayDeque<>();
        cities1.add(base);
        cities2.add(target);

        Set<City> visited1 = new HashSet<>();
        Set<City> visited2 = new HashSet<>();
        visited1.add(base);
        visited2.add(target);

        Path path1 = new Path(base);
        Path path2 = new Path(target);
        while (!cities1.isEmpty() || !cities2.isEmpty()) {
            City cursor1 = cities1.poll();
            City cursor2 = cities2.poll();

            if (cursor1 != null) oneIterationBFS(cursor1, path1, cities1, visited1);
            if (cursor2 != null) oneIterationBFS(cursor2, path2, cities2, visited2);

            // check is path was found
            if (path1.getLast().equals(path2.getLast())) {
                concat(path1, path2);
                break;
            }
        }

        return path1;
    }

    private void oneIterationBFS(City cursor, Path path, Queue<City> cities, Set<City> visited) {
        for (City n : cursor.getNeighbors().keySet()) {
            if (!visited.contains(n)) {
                path.addPath(n);
                cities.add(n);
                visited.add(n);
            }
        }
    }

    private void concat(Path base, Path additional) {
        while (!additional.isEmpty()) {
            base.addPath(additional.poll());
        }
    }


    @Override
    public String name() {
        return "Double search";
    }
}
