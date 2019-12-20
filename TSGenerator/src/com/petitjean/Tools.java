package com.petitjean;

public class Tools {

    public static double Min3(double val1, double val2, double val3) {
        double minval = Math.min(val1, val2);
        minval = Math.min(minval, val3);
        return minval;
    }
}
