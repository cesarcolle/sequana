package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;
import com.github.cesarcolle.sequana.model.frequency.Frequency;
import com.github.cesarcolle.sequana.model.misc.Interval;

import java.util.List;
import java.util.Map;

public class Device implements Namable {
    private String name;
    private Interval pinRange;
    private List<Pipe> pipe;
    private Map<Integer, Frequency> pipeNumberFrequency;
    private HardwareModel hardwareModel;

    public Device(String name, Interval pinRange, List<Pipe> pipe, Map<Integer, Frequency> pipeNumberFrequency, HardwareModel hardwareModel) {
        this.name = name;
        this.pinRange = pinRange;
        this.pipe = pipe;
        this.pipeNumberFrequency = pipeNumberFrequency;
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

    public Map<Integer, Frequency> getPipeNumberFrequency() {
        return pipeNumberFrequency;
    }
}
