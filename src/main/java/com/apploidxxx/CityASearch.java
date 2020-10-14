package com.apploidxxx;

import java.util.Objects;

/**
 * Special decorator class used in A* search algos
 *
 * @author Arthur Kupriyanov on 15.10.2020
 */
public class CityASearch extends City {
    public final int distanceToEndCity;

    public CityASearch(City city, int distanceToEndCity) {
        super(city);
        this.distanceToEndCity = distanceToEndCity;
    }

}
