package com.company.view;

import com.company.model.Circle;
import com.company.model.CircleZone;
import com.company.model.Point;

import java.util.ArrayList;

/**
 * View components of MVC pattern. Displays all info about figures.
 */
public class FigureView {

    public void printCircleInfo(Circle circle) {
        System.out.println(circle.toString());
    }

    public void printCircleZoneInfo(CircleZone circleZone) {
        System.out.println(circleZone.toString());
    }

    public void printPointInfo(Point point) {
        System.out.println(point.toString());
    }

    public void printCirclesInfo(ArrayList<Circle> circles) {
        for (var circle : circles
        ) {
            printCircleInfo(circle);
        }
    }

    public void printCircleZonesInfo(ArrayList<CircleZone> circleZones) {
        for (var circleZone : circleZones
        ) {
            printCircleZoneInfo(circleZone);
        }
    }

    public void printPointsInfo(ArrayList<Point> points) {
        for (var point : points
        ) {
            printPointInfo(point);
        }
    }

    public void printContainsMessage(Circle circle, Point point, boolean contains) {

        if (contains) {
            System.out.println(circle.toString() + " contains " + point.toString());
        }
        else {
            System.out.println(circle.toString() + " does not contain " + point.toString());
        }
    }

}
