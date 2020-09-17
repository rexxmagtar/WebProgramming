package com.company;

import com.company.goodies.Goody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PresentManager {

    public ArrayList<Goody> createPresent() {

        ArrayList<Goody> goodies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            goodies.add(GoodyFactory.getRandomGoody());
        }

        return goodies;
    }

    public float getGoodiesWight(ArrayList<Goody> goodies) {
        float sum = 0;

        for (var good :
                goodies) {
            sum += good.getWeight();
        }

        return sum;
    }

    public void sortGoodiesByWeight(ArrayList<Goody> goodies) {
        goodies.sort(new Comparator<Goody>() {
            @Override
            public int compare(Goody t, Goody t1) {
                if (t.getWeight() > t1.getWeight()) {
                    return 1;
                }
                return -1;
            }

        });
    }

    public void printPresentInfo(ArrayList<Goody> goodies) {
        for (var good :
                goodies) {
            System.out.println(good.toString());
        }
    }

    public ArrayList<Goody> findGoodyByName(ArrayList<Goody> goodies, String name) {
        return new ArrayList<Goody>(goodies.stream().filter(new Predicate<Goody>() {
            @Override
            public boolean test(Goody goody) {
                return goody.getName().equals(name);
            }
        }).collect(Collectors.toList()));
    }

    public ArrayList<Goody> findGoodyByWeight(ArrayList<Goody> goodies, float minWeight, float maxWeight) {
        return new ArrayList<Goody>(goodies.stream().filter(new Predicate<Goody>() {
            @Override
            public boolean test(Goody goody) {
                return goody.getWeight() >= minWeight && goody.getWeight() <= maxWeight;
            }
        }).collect(Collectors.toList()));
    }

    public ArrayList<Goody> findGoodies(ArrayList<Goody> goodies, float minWeight, float maxWeight, float minCalories, float maxCalories, float minShelfLife, float maxShelfLife, String name) {
        return new ArrayList<Goody>(goodies.stream().filter(new Predicate<Goody>() {
            @Override
            public boolean test(Goody goody) {
                return goody.getWeight() >= minWeight && goody.getWeight() <= maxWeight
                        && goody.getCalories() >= minCalories && goody.getCalories() <= maxCalories
                        && goody.getShelfLife() >= minShelfLife && goody.getShelfLife() <= maxShelfLife
                        && (name == "" || goody.getName().equals(name));
            }
        }).collect(Collectors.toList()));
    }


}
