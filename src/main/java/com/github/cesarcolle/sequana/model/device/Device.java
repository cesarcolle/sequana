package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;
import com.github.cesarcolle.sequana.model.misc.Hygiene;
import com.github.cesarcolle.sequana.model.frequency.Frequency;
import com.github.cesarcolle.sequana.model.misc.Interval;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Device implements Namable, Hygiene {
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

    @Override
    public void checkHygiene() throws Exception {
        Integer countPins = pipe.size();
        Integer intervalSize = pinRange.max - pinRange.min +1;

        if ( countPins > intervalSize) {
            throw new IllegalArgumentException("there is not enough available pins in your device : " + name);
        }

        Boolean pipeInRange = pipeNumberFrequency.keySet().stream().anyMatch(pin -> pin - pinRange.max >= 0);
        if (pipeInRange){
            throw  new IllegalArgumentException("your pin number don't belong to the pin_range given for device "+ name);
        }

        Integer countPipePinUsed = pipe.stream().map(Pipe::getPins).reduce(0, (acc, curr) -> acc + curr);

        if (countPipePinUsed > pinRange.max){
            throw new IllegalArgumentException("The pipes declares used too much pins for device " + name);
        }

        if (pinRange.max - pinRange.min != pipeNumberFrequency.size()){
            throw new IllegalArgumentException("you declare " + pipeNumberFrequency.size() + " for " + pinRange + " interval" );
        }

    }

    public Map<Integer, Frequency> getPipeNumberFrequency() {
        return pipeNumberFrequency;
    }
}
