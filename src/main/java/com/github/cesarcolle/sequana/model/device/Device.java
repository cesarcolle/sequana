package com.github.cesarcolle.sequana.model.device;

import com.github.cesarcolle.sequana.model.Namable;

import java.util.ArrayList;
import java.util.List;

public class Device implements Namable {
    private String name;
    private List<Integer> pinRange = new ArrayList<>();
    private Integer pipe;
    private HardwareModel hardwareModel;

    @Override
    public String getName() {
        return name;
    }
}
