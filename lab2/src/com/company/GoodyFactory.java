package com.company;

import com.company.goodies.Biscuit;
import com.company.goodies.Candy;
import com.company.goodies.Chocolate;
import com.company.goodies.Goody;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * Class provides different goodies.
 */
public class GoodyFactory {

    /**
     * Generates random goody
     * @return
     */
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

    /**
     * Generates random candy
     * @param random used for candy generation
     * @return random candy
     */
    public static Candy getRandomCandy(Random random) {
        return new Candy(random.nextFloat() * 100,
                random.nextFloat() * 10,
                random.nextInt(4)+1,
                "Candy" + random.nextInt(),
                Candy.Type.values()[random.nextInt(Candy.Type.values().length - 1)],
                random.nextBoolean());
    }

    /**
     * Generates random candy
     * @return random candy
     */
    public static Candy getRandomCandy() {
        return getRandomCandy(new Random());
    }

    /**
     * Generates random biscuit
     * @param random used for biscuit generation
     * @return random biscuit
     */
    public static Biscuit getRandomBiscuit(Random random) {
        return new Biscuit(random.nextFloat() * 100,
                random.nextFloat() * 10,
                random.nextInt(9)+1,
                "Biscuit" + random.nextInt(),
                Biscuit.Form.values()[random.nextInt(Biscuit.Form.values().length - 1)]);
    }

    /**
     * Generates random biscuit
     * @return random biscuit
     */
    public static Biscuit getRandomBiscuit() {
        return getRandomBiscuit(new Random());
    }

    /**
     * Generates random chocolate
     * @param random used for chocolate generation
     * @return random chocolate
     */
    public static Chocolate getRandomChocolate(Random random) {
        return new Chocolate(random.nextFloat() * 100,
                random.nextFloat() * 10,
                random.nextInt(6)+1,
                "Chocolate" + random.nextInt(),
                random.nextFloat() * 100);
    }

    /**
     * Generates random chocolate
     * @return random chocolate
     */
    public static Chocolate getRandomChocolate() {
        return getRandomChocolate(new Random());
    }

}
