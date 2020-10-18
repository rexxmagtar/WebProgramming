package com.company.goodies;

import java.util.Objects;

/**
 * Class describes Candy goodies
 */
public class Candy extends Goody {

    public enum Type{
        Chocolate,
        Lollipop,
        Jelly
    }

    private Type type;

    private boolean ContainsLiquor;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean ContainsLiquor() {
        return ContainsLiquor;
    }

    public void setContainsLiquor(boolean containsLiquor) {
        ContainsLiquor = containsLiquor;
    }

    public Candy(float wight, float calories, float shelfLife, String name, Type type, boolean containsLiquor) {
        super(wight, calories, shelfLife, name);
        this.type = type;
        ContainsLiquor = containsLiquor;
    }

    @Override
    public String toString() {
        return "Candy{" +
                "type=" + type +
                ", ContainsLiquor=" + ContainsLiquor +
                ", weight=" + weight +
                ", calories=" + calories +
                ", shelfLife=" + shelfLife +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Candy candy = (Candy) o;
        return ContainsLiquor == candy.ContainsLiquor &&
                type == candy.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, ContainsLiquor);
    }
}
