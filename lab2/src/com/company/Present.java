package com.company;

import com.company.goodies.Goody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Present {
    private ArrayList<Goody> goodies = new ArrayList<>();

    public ArrayList<Goody> getGoodiesCopy(){
        return  (ArrayList<Goody> )goodies.clone();
    }

    public Present(ArrayList<Goody> goodies) {
        this.goodies = goodies;
    }

    public void sortGoodiesByWeight() {
        goodies.sort(new Comparator<Goody>() {
            @Override
            public int compare(Goody t, Goody t1) {
                if (t.getWeight() > t1.getWeight()) {
                    return 1;
                }
                return -1;
            }

            ;

        });
    }

}

