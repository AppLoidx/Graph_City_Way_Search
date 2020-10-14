package com.apploidxxx.path;

import com.apploidxxx.City;

import java.util.LinkedList;

/**
 *
 * @author Arthur Kupriyanov on 03.10.2020
 */
public class Path {

    private final LinkedList<City> path;

    public Path(City base) {
        path = new LinkedList<>();
        path.add(base);
    }

    public LinkedList<City> getLinkedList() {
        return path;
    }

    public void addPath(City city){
        path.add(city);
    }

    public City getLast() {
        return path.getLast();
    }

    public City poll() {
        if (path.size() == 1) {
            throw new UnsupportedOperationException("Unable to remove base city");
        }
        return path.pollLast();
    }

    public boolean isEmpty() {
        return path.isEmpty();
    }

    public String fullPath(City target) {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        for (City city : path) {

            sb.append(String.format("[%2d] -> %s", index, city.getName()));
            sb.append("\n");

            if (target.equals(city)) break;
            index++;
        }

        return sb.toString();
    }


    public void printFullPath(City target) {
        System.out.println(fullPath(target));
    }


}
