package com.company.model;

/**
 * Class describes circle in 2D space.
 */
public class Circle extends CircleZone {

    private Circle() {
        super(10);
    }

    public Circle(Point center, float radius) {
        super(center, radius);
    }

    public Circle(float radius) {
        super(radius);
    }
}
