package com.github.cesarcolle.sequana.model;

import com.github.cesarcolle.sequana.model.device.Device;

import java.util.List;

public class Area implements Namable {
    private String name;
    private List<Device> devices;

    public Area(String name, List<Device> devices) {
        this.name = name;
        this.devices = devices;
    }

    @Override
    public String getName() {
        return name;
    }
}
