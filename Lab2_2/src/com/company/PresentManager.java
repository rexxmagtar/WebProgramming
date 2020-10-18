package com.company;

import com.company.goodies.Goody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class does different operations with present.
 */
public class PresentManager {

    /**
     * Generate random present.
     * @return random present
     */
    public ArrayList<Goody> createPresent() {

        ArrayList<Goody> goodies = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            goodies.add(GoodyFactory.getRandomGoody());
        }

        return goodies;
    }

    /**
     * gets goodies weight
     * @param goodies
     * @return weight of goodies
     */
    public float getGoodiesWight(ArrayList<Goody> goodies) {
        float sum = 0;

        for (var good :
                goodies) {
            sum += good.getWeight();
        }

        return sum;
    }

    /**
     * sorts goodies by weight
     * @param goodies
     */
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

    /**
     * Print in console information about present
     * @param goodies
     */
    public String getPresentInfoString(ArrayList<Goody> goodies) {
        String result = "";
        for (var good :
                goodies) {
            result+=good.toString() + "\n";
        }

        return result;
    }

    /**
     * Finds goody by name
     * @param goodies
     * @param name
     * @return
     */
    public ArrayList<Goody> findGoodyByName(ArrayList<Goody> goodies, String name) {
        return new ArrayList<Goody>(goodies.stream().filter(new Predicate<Goody>() {
            @Override
            public boolean test(Goody goody) {
                return goody.getName().equals(name);
            }
        }).collect(Collectors.toList()));
    }

    /**
     * Finds goody by weight range
     * @param goodies
     * @param minWeight
     * @param maxWeight
     * @return
     */
    public ArrayList<Goody> findGoodyByWeight(ArrayList<Goody> goodies, float minWeight, float maxWeight) {
        return new ArrayList<Goody>(goodies.stream().filter(new Predicate<Goody>() {
            @Override
            public boolean test(Goody goody) {
                return goody.getWeight() >= minWeight && goody.getWeight() <= maxWeight;
            }
        }).collect(Collectors.toList()));
    }

    /**
     * Finds all goodies by specified criteria
     * @param goodies
     * @param minWeight
     * @param maxWeight
     * @param minCalories
     * @param maxCalories
     * @param minShelfLife
     * @param maxShelfLife
     * @param name
     * @return
     */
    public ArrayList<Goody> findGoodies(ArrayList<Goody> goodies, float minWeight, float maxWeight, float minCalories, float maxCalories, float minShelfLife, float maxShelfLife, String name) {
        System.out.println("Searching goodies:");
        System.out.println(minWeight);
        System.out.println(maxWeight);
        System.out.println(minCalories);
        System.out.println(maxCalories);
        System.out.println(minShelfLife);
        System.out.println(maxShelfLife);
        System.out.println(name);

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
