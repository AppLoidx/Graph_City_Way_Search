package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

/**
 * @author Arthur Kupriyanov on 06.10.2020
 */
public class DFSIterative implements Algos {
    private final static DFS DFS = new DFS();

    /**
     * Iterative use DFS
     * @param base from
     * @param target to
     * @return algorithm's path (not path from one city to another)
     */
    @Override
    public Path search(City base, City target) {
        Path answer = null;
        int index = 2;
        for (; index < 100; index++) {
            Path path = DFS.search(base, target, index);
            answer = path;

            if (path.getLinkedList().contains(target)) {
                break;
            }
        }

        System.out.println("Total iterations: " + index);

        return answer;
    }

    @Override
    public String name() {
        return "DFS (Iterative)";
    }
}
