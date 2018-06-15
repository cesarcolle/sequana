package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;
import com.github.cesarcolle.sequana.model.misc.Hygiene;
import com.github.cesarcolle.sequana.model.misc.Interval;

import java.util.List;

public class Device implements Namable, Hygiene {
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

    @Override
    public void checkHygiene() throws Exception {
        Integer countPins = pipe.stream().map(Pipe::getPins).reduce(0, (acc, pins) -> acc + pins );
        if (pinRange.max < countPins) {
            throw new IllegalArgumentException("there is not enough available pins in your device");
        }
    }
}
