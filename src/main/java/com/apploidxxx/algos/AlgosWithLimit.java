package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

/**
 * @author Arthur Kupriyanov on 06.10.2020
 */
public interface AlgosWithLimit extends Nameable {
    /**
     * Algos implementation with max depth limit
     *
     * The algorithm should return current path if he reaches the limit of depth
     * @param base from
     * @param target to
     * @param maxDepth depth limit
     * @return algorithm's path (not path from one city to another)
     */
    Path search(City base, City target, int maxDepth);
}
