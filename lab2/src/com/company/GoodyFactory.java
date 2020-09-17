package com.company;

import com.company.goodies.Biscuit;
import com.company.goodies.Candy;
import com.company.goodies.Chocolate;
import com.company.goodies.Goody;

import java.time.LocalDateTime;
import java.util.Random;

public class GoodyFactory {

    public static Goody getRandomGoody() {
        Random random = new Random();

        int randomType = random.nextInt(3);

        switch (randomType) {
            case 0: {
                return getRandomBiscuit(random);
            }
            case 1: {
                return getRandomChocolate(random);
            }
            default: {
                return getRandomCandy(random);
            }
        }
    }

    public static Candy getRandomCandy(Random random) {
        return new Candy(random.nextFloat() * 100,
                random.nextFloat() * 10,
                random.nextInt(4)+1,
                "Candy" + random.nextInt(),
                Candy.Type.values()[random.nextInt(Candy.Type.values().length - 1)],
                random.nextBoolean());
    }

    public static Candy getRandomCandy() {
        return getRandomCandy(new Random());
    }

    public static Biscuit getRandomBiscuit(Random random) {
        return new Biscuit(random.nextFloat() * 100,
                random.nextFloat() * 10,
                random.nextInt(9)+1,
                "Biscuit" + random.nextInt(),
                Biscuit.Form.values()[random.nextInt(Biscuit.Form.values().length - 1)]);
    }

    public static Biscuit getRandomBiscuit() {
        return getRandomBiscuit(new Random());
    }

    public static Chocolate getRandomChocolate(Random random) {
        return new Chocolate(random.nextFloat() * 100,
                random.nextFloat() * 10,
                random.nextInt(6)+1,
                "Chocolate" + random.nextInt(),
                random.nextFloat() * 100);
    }

    public static Chocolate getRandomChocolate() {
        return getRandomChocolate(new Random());
    }

}
