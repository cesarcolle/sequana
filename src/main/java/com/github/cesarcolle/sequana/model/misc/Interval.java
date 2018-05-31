package com.github.cesarcolle.sequana.model.misc;

import com.github.cesarcolle.sequana.model.Namable;

public class Interval {

    Integer min;
    Integer max;

    public Interval(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }


    public static Interval intervalFromString(String min, String max){
        return new Interval(Integer.parseInt(min), Integer.parseInt(max));

    }
}
