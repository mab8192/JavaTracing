package com.mab8192.rt;

import java.util.Random;

public class Utils {
    private static Random rand = new Random();

    public static final double infinity = Double.POSITIVE_INFINITY;

    public static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180.0;
    }

    public static double randomDouble() {
        return rand.nextDouble();
    }
    public static double randomDouble(double min, double max) {
        return rand.nextDouble(min, max);
    }
}
