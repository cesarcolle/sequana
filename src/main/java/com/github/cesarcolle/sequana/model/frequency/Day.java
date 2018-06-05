package com.github.cesarcolle.sequana.model.frequency;

public class Day {
    public String day;
    public String hour;
    public Duration duration;

    public Day(String day, String hour, Duration duration) {
        this.day = day;
        this.hour = hour;
        this.duration = duration;
    }

}
