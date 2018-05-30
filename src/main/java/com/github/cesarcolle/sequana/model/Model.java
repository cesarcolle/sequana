package com.github.cesarcolle.sequana.model;

import com.github.cesarcolle.sequana.model.device.Device;
import com.github.cesarcolle.sequana.model.frequency.Frequency;

import java.util.Map;

public class Model {
    private Map<String, Frequency> frequencies ;
    private Map<String, Device> devices;
    private Map<String, Area> areas;

    public Model(Map<String, Frequency> frequencies, Map<String, Device> devices, Map<String, Area> areas) {
        this.frequencies = frequencies;
        this.devices = devices;
        this.areas = areas;
    }

}
