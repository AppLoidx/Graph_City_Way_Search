package com.apploidxxx;

import com.apploidxxx.algos.*;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;

/**
 * @author Arthur Kupriyanov on 03.10.2020
 */
@SuppressWarnings("SameParameterValue")
public class Main {
    private static final String DECORATOR = "------------------------------";
    private static final PrintStream OUT = System.out;

    public static void main(String[] args) throws FileNotFoundException {
        Map<String, City> data = Parser.readCities();
        City base = data.get("С.Петербург");
        City target = data.get("Житомир");

        applyAlgo(base, target, new BFS());
        applyAlgo(base, target, new DFS());
        applyAlgo(base, target, new DFS(), 5);
        applyAlgo(base, target, new DFSIterative());
        applyAlgo(base, target, new DoubleSearch());
        applyAlgo(base, target, new BestFirstSearch());

        Map<String, CityASearch> aSearchMap = Parser.readCitiesForASearch();
        applyAlgo(aSearchMap.get("С.Петербург"), aSearchMap.get("Житомир"), new ASearch());

    }

    private static void applyAlgo(City from, City to, Algos algos) {
        decorate(algos, () -> algos.search(from, to).printFullPath(to));
    }

    private static void applyAlgo(City from, City to, AlgosWithLimit algosWithLimit, int limit) {
        decorate(algosWithLimit, () -> algosWithLimit.search(from, to, limit).printFullPath(to));
    }

    private static void decorate(Nameable nameable, Runnable runnable) {
        decorate(() -> OUT.println(nameable.name()));
        runnable.run();
    }

    private static void decorate(Runnable runnable) {
        OUT.println(DECORATOR);
        runnable.run();
        OUT.println(DECORATOR);
    }
}
