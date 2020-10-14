package com.apploidxxx.algos;

import com.apploidxxx.City;
import com.apploidxxx.path.Path;

/**
 * @author Arthur Kupriyanov on 03.10.2020
 */
public interface Algos extends Nameable{
    /**
     *
     * @param base from
     * @param target to
     * @return algorithm's path (not path from one city to another)
     */
    Path search(City base, City target);
}
