package com.company.goodies;

public class Chocolate extends Goody {

    private float bitterness;

    public float getBitterness() {
        return bitterness;
    }

    public void setBitterness(float bitterness) {
        trySetBitterness(bitterness);
    }

    private Boolean trySetBitterness(float bitterness){
        if(bitterness<=100 && bitterness>=0){
            this.bitterness = bitterness;
            return true;
        }

        return false;
    }

    public Chocolate(float wight, float calories, float shelfLife, String name, float bitterness) {
        super(wight, calories, shelfLife, name);
        trySetBitterness(bitterness);
    }

    @Override
    public String toString() {
        return "Chocolate{" +
                "bitterness=" + bitterness +
                ", weight=" + weight +
                ", calories=" + calories +
                ", shelfLife=" + shelfLife +
                ", name='" + name + '\'' +
                '}';
    }
}
