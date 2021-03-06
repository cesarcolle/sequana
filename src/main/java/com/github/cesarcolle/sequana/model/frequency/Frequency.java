package com.github.cesarcolle.sequana.model.frequency;

import com.github.cesarcolle.sequana.model.Namable;

import java.util.List;

public class Frequency implements Namable {

    private String name;
    private List<Day> days;

    public Frequency(String name, List<Day> days) {
        this.name = name;
        this.days = days;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
