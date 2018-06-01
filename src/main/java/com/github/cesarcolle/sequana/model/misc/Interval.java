package com.github.cesarcolle.sequana.model.misc;

public class Interval {

    Integer min;
    Integer max;

    public Interval(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }


    public static Interval intervalFromString(String min, String max) {
        return new Interval(Integer.parseInt(min), Integer.parseInt(max));

    }
}
