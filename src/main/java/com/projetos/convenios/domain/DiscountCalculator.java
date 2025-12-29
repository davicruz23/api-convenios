package com.projetos.convenios.domain;

import java.util.Random;

public class DiscountCalculator {

    private static final Random RANDOM = new Random();

    public static int calculate(int maxDiscount) {

        int chance = RANDOM.nextInt(100) + 1;

        if (chance <= 85) {
            return randomBetween(15, Math.min(30, maxDiscount));
        }

        if (chance <= 98) {
            return randomBetween(31, Math.min(50, maxDiscount));
        }

        return randomBetween(51, maxDiscount);
    }

    private static int randomBetween(int min, int max) {
        if (min > max) return min;
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}

