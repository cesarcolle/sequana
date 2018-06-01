package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;

public class Pipe implements Namable {
    private String name;
    private boolean flow = false;
    private Integer pins;


    public Pipe(String name, Integer pins) {
        this.name = name;
        this.pins = pins;
    }

    public void isFlow(Boolean status) {
        this.flow = status;
    }

    @Override
    public String getName() {
        return name;
    }
}
