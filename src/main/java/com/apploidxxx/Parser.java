package com.apploidxxx;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Arthur Kupriyanov on 08.10.2020
 */
public class Parser {
    private final static String FILE_PATH = "src\\main\\resources\\cities.txt";
    private final static String HEURISTIC_PATH = "src\\main\\resources\\heuristic.txt";
    public static Map<String, City> readCities() throws FileNotFoundException {
        Map<String, City> data = new HashMap<>();
        Scanner scanner = new Scanner(new File(FILE_PATH));

        while (scanner.hasNextLine()) {
            parseLine(scanner.nextLine(), data);
        }

        return data;
    }

    public static Set<String> readCitiesNames() throws FileNotFoundException {
        Set<String> data = new HashSet<>();
        Scanner scanner = new Scanner(new File(FILE_PATH));
        while (scanner.hasNextLine()) {
            parseLine(scanner.nextLine(), data);
        }
        return data;
    }

    public static Map<String, CityASearch> readCitiesForASearch() throws FileNotFoundException {

        Map<String, City> citiesMap = readCities();
        Map<String, Integer> heuristic = readHeuristic();
        Map<String, CityASearch> dataASearch = new HashMap<>();

        for (Map.Entry<String, City> entry : citiesMap.entrySet()) {
            if (entry.getKey().equals("Житомир")) {
                dataASearch.put(entry.getKey(), new CityASearch(entry.getValue(), 0));
                continue;
            }

            CityASearch cityASearch = new CityASearch(entry.getValue(), heuristic.get(entry.getKey()));
            dataASearch.put(entry.getKey(), cityASearch);
        }

        return dataASearch;
    }

    public static Map<String, Integer> readHeuristic() throws FileNotFoundException {
        Map<String, Integer> data = new HashMap<>();
        Scanner scanner = new Scanner(new File(HEURISTIC_PATH));
        while (scanner.hasNextLine()) {
            parseHeuristicLine(scanner.nextLine(), data);
        }

        return data;
    }

    private static void parseLine(String line, Set<String> data) {
        String[] lineData = line.split(" ");
        data.add(lineData[0]);
        data.add(lineData[1]);
    }

    private static void parseLine(String line, Map<String, City> data) {
        String[] lineData = line.split(" ");
        City city1 = data.getOrDefault(lineData[0], new City(lineData[0]));
        City city2 = data.getOrDefault(lineData[1], new City(lineData[1]));
        city1.addNeighbor(city2, Integer.parseInt(lineData[2]));

        data.put(city1.getName(), city1);
        data.put(city2.getName(), city2);
    }

    // fuck this type erasure )))
    private static void parseHeuristicLine(String line, Map<String, Integer> data) {
        String[] lineData = line.split(" ");
        data.put(lineData[0], Integer.valueOf(lineData[1]));
    }
}
