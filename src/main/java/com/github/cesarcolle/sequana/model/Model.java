package com.github.cesarcolle.sequana.model;

import com.github.cesarcolle.sequana.model.device.Device;
import com.github.cesarcolle.sequana.model.device.Pipe;
import com.github.cesarcolle.sequana.model.frequency.Frequency;

import java.util.Map;

public class Model {
    private Map<String, Frequency> frequencies;
    private Map<String, Device> devices;
    private Map<String, Area> areas;
    private Map<String, Pipe> pipes;


    public Model(Map<String, Frequency> frequencies, Map<String, Device> devices,
                 Map<String, Area> areas, Map<String, Pipe> pipes) {
        this.frequencies = frequencies;
        this.devices = devices;
        this.areas = areas;
        this.pipes = pipes;

    }

    public Map<String, Frequency> getFrequencies() {
        return frequencies;
    }

    public Map<String, Device> getDevices() {
        return devices;
    }

    public Map<String, Area> getAreas() {
        return areas;
    }

    public Map<String, Pipe> getPipes() {
        return pipes;
    }

}
