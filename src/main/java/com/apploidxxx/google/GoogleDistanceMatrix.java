package com.apploidxxx.google;

import com.apploidxxx.Parser;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;

import java.util.Set;

/**
 * Prints distance from city to target_city
 * <p>
 * Use it for generate heuristic data (A* search algos)
 * <p>
 * For example,
 * <ul>
 *      <li>Каунас 799526</li>
 *      <li>Волгоград 1430447</li>
 *      <li>Рига 1000524</li>
 *      <li>С.Петербург 1256784</li>
 *      <li>Москва 1003585</li>
 *      <li>Вильнюс 702919</li>
 * </ul>
 *
 * </code>
 *
 * @author Arthur Kupriyanov on 15.10.2020
 */
public class GoogleDistanceMatrix {
    /**
     * API Key from Google Distance Matrix API
     */
    private static final String googleDistanceApiKey = System.getenv("DISTANCE_MATRIX_KEY");
    private static final String targetCity = "Житомир";

    private static final GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(googleDistanceApiKey)
            .build();

    public static void main(String[] args) throws Exception {
        Set<String> citiesNames = Parser.readCitiesNames();

        for (String city : citiesNames) {
            if (!city.equals(targetCity)) {
                System.out.println(city + " " + calcDistanceToTargetCity(city));
            }
        }


    }

    private static long calcDistanceToTargetCity(String city1) throws Exception {
        DistanceMatrixApiRequest distanceMatrix = DistanceMatrixApi.getDistanceMatrix(context,
                new String[]{city1},
                new String[]{targetCity});

        distanceMatrix.custom("region", "ru");
        distanceMatrix.units(Unit.METRIC);
        distanceMatrix.mode(TravelMode.DRIVING);

        DistanceMatrix result;
        result = distanceMatrix.await();

        if (result.rows.length > 0) {
            if (result.rows[0].elements.length > 0) {
                DistanceMatrixElement dmElem = result.rows[0].elements[0];
                if (dmElem.status == DistanceMatrixElementStatus.OK) {
                    return dmElem.distance.inMeters;
                }
            }
        }

        return -1;
    }


}
