package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.CityASearch;
import com.apploidxxx.Parser;
import com.apploidxxx.path.Path;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation of A* Search
 *
 * @author Arthur Kupriyanov on 15.10.2020
 */
public class ASearch implements Algos {

    private final Map<String, Integer> heuristicMap = Parser.readHeuristic();

    public ASearch() throws FileNotFoundException {
    }

    public Path search(City base, City target) {
        LinkedList<CityASearch> queue = new LinkedList<>();
        Set<CityASearch> visited = new HashSet<>();
        Path path = new Path(base);
        queue.add((CityASearch) base);
        while (!queue.isEmpty()) {
            queue.sort(Comparator.comparingInt(c -> c.distanceToEndCity));
            City cursor = queue.poll();

            if (cursor != null) {
                if (!cursor.equals(base))
                    path.addPath(cursor);

                if (cursor.equals(target)) {
                    return path;
                }

                LinkedList<City> neighbors = new LinkedList<>(cursor.getNeighbors().keySet());
                LinkedList<CityASearch> cities = neighbors.stream()
                        .map(c -> new CityASearch(c, heuristicMap.getOrDefault(c.getName(), 0)))
                        .sorted(Comparator.comparingInt(c -> c.distanceToEndCity))
                        .collect(Collectors.toCollection(LinkedList::new));

                for (CityASearch aCity : cities) {
                    if (!visited.contains(aCity)) {
                        visited.add(aCity);
                        queue.add(aCity);
                    }
                }

            }

        }

        return path;
    }

    public String name() {
        return "A* Search";
    }
}

