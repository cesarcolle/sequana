package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;

public class Hardware implements Namable {
    private String name;
    private HardwareModel model;

    public Hardware(String name, HardwareModel model) {
        this.name = name;
        this.model = model;
    }

    @Override
    public String getName() {
        return name;
    }
}
