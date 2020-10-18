package com.company.goodies;

import java.util.Objects;

/**
 * All goodies derive from this class.
 */
public abstract class Goody {
    protected float weight;
    protected float calories;
    protected float shelfLife;
    protected String name;

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public float getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(float shelfLife) {
        this.shelfLife = shelfLife;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Goody(float weight, float calories, float shelfLife, String name) {
        this.weight = weight;
        this.calories = calories;
        this.shelfLife = shelfLife;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goody goody = (Goody) o;
        return Float.compare(goody.weight, weight) == 0 &&
                Float.compare(goody.calories, calories) == 0 &&
                Float.compare(goody.shelfLife, shelfLife) == 0 &&
                Objects.equals(name, goody.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, calories, shelfLife, name);
    }
}
