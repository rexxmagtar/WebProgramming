package com.company.controller;

import com.company.model.Circle;
import com.company.model.CircleZone;
import com.company.model.DataProvider;
import com.company.model.Point;
import com.company.view.FigureView;

import javax.xml.crypto.Data;
import java.util.ArrayList;

/**
 * Controller class of the MVC pattern. Works with model and calls View's methods directly.
 */
public class Controller {

    /**
     * figure view of the MVC pattern. Used to display all info about figures.
     */
    private FigureView figureView;

    /**
     *
     * @param figureView
     */
    public Controller(FigureView figureView) {
        this.figureView = figureView;
    }

    /**
     * starts controller's activity
     */
    public void Start() {

        int count = 4;

        ArrayList<Circle> circles = DataProvider.getRandomCircles(count);
        ArrayList<Point> points = DataProvider.getRandomPoints(count);
        ArrayList<CircleZone> circleZones = DataProvider.getRandomCircleZones(count);

        figureView.printCirclesInfo(circles);
        figureView.printCircleZonesInfo(circleZones);
        figureView.printPointsInfo(points);

        for (var circle : circles
        ) {
            for (var point : points
            ) {

                figureView.printContainsMessage(circle, point, CircleContains(circle, point));
            }
        }

    }

    /**
     * Checks if circle contains point
     * @param circle
     * @param point
     * @return
     */
    public static boolean CircleContains(Circle circle, Point point) {
        return Math.sqrt(Math.pow(point.getX() - circle.getCenter().getX(), 2) + Math.pow(point.getY() - circle.getCenter().getY(), 2)) <= circle.getRadius();
    }
}
