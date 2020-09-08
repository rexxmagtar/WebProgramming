package com.company.model;

/**
 * Class describes circle zone in 2D space
 */
public class CircleZone {

    protected Point center;
    protected float radius;

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        if(radius>=0)
        this.radius = radius;
    }

    public CircleZone(Point center, float radius) {
        this.center = center;
        if(radius>=0)
        this.radius = radius;
    }

    public CircleZone(float radius) {
        this(new Point(0, 0), radius);
    }

    @Override
    public String toString() {
        return "CircleZone{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
