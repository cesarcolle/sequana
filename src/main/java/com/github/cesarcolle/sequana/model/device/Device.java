package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;
import com.github.cesarcolle.sequana.model.misc.Interval;

import java.util.ArrayList;
import java.util.List;

public class Device implements Namable {
    private String name;
    private Interval pinRange;
    private List<Pipe> pipe;
    private HardwareModel hardwareModel;

    public Device(String name, Interval pinRange, List<Pipe> pipe, HardwareModel hardwareModel) {
        this.name = name;
        this.pinRange = pinRange;
        this.pipe = pipe;
        this.hardwareModel = hardwareModel;
    }

    @Override
    public String getName() {
        return name;
    }

    public Interval getPinRange() {
        return pinRange;
    }

    public List<Pipe> getPipe() {
        return pipe;
    }

    public HardwareModel getHardwareModel() {
        return hardwareModel;
    }
}
