package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Arthur Kupriyanov on 06.10.2020
 */
public class DFS implements Algos, AlgosWithLimit {
    /**
     * DFS algorithm without depth limit
     * @param base from
     * @param target to
     * @return algorithm's path (not path from one city to another)
     */
    @Override
    public Path search(City base, City target) {
        return dfs(base, target);
    }


    /**
     * DFS algorithm with depthLimit
     * @param base from
     * @param target to
     * @param maxDepth recursive call limit
     * @return algorithm's path (not path from one city to another)
     */
    @Override
    public Path search(City base, City target, int maxDepth) {
        return dfs(base,  target, maxDepth);
    }

    private Path dfs(City base, City target) {
        return dfs(base, new HashSet<>(), new Path(base), target, 0, Integer.MAX_VALUE);
    }

    private Path dfs(City base, City target, int depthLimit) {
        return dfs(base, new HashSet<>(), new Path(base), target, 0, depthLimit);
    }

    private Path dfs(City point, Set<City> visited, Path path, City target, int currentDepth, final int depthLimit) {

        if (currentDepth == depthLimit)
            return path;

        visited.add(point);

        for (City neighborCity : point.getNeighbors().keySet()) {
            if (!visited.contains(neighborCity)) {
                path.addPath(neighborCity);
                if (neighborCity.equals(target)) return path;

                Path pathDFS = dfs(neighborCity, visited, path, target, ++currentDepth, depthLimit);
                if (pathDFS.getLinkedList().contains(target)) return pathDFS;

            }
        }
        return path;
    }

    @Override
    public String name() {
        return "DFS";
    }


}
