package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class contains methods for data providing
 */
public class DataProvider {

    public static int rangeCoef = 10;

    public static ArrayList<Circle> getRandomCircles(int count) {

        Random random = new Random();
        ArrayList<Circle> circles = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            circles.add(new Circle(new Point(random.nextFloat() * rangeCoef, random.nextFloat() * rangeCoef), random.nextFloat() * rangeCoef));
        }

        return circles;
    }

    public static ArrayList<CircleZone> getRandomCircleZones(int count) {

        Random random = new Random();
        ArrayList<CircleZone> circleZones = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            circleZones.add(new CircleZone(new Point(random.nextFloat() * rangeCoef, random.nextFloat() * rangeCoef), random.nextFloat() * rangeCoef));
        }

        return circleZones;
    }

    public static ArrayList<Point> getRandomPoints(int count) {

        Random random = new Random();

        ArrayList<Point> points = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            points.add(new Point(random.nextFloat() * rangeCoef, random.nextFloat() * rangeCoef));
        }

        return points;
    }
}
