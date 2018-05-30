package com.github.cesarcolle.sequana.model.frequency;

import com.github.cesarcolle.sequana.model.Namable;

import java.util.List;

public class Frequency implements Namable{

    private String name;
    private List<Day> days;


    @Override
    public String getName() {
        return name;
    }



}
