package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.grammar.SEQUANABaseListener;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser.*;
import com.github.cesarcolle.sequana.model.*;
import com.github.cesarcolle.sequana.model.device.Device;
import com.github.cesarcolle.sequana.model.device.HardwareModel;
import com.github.cesarcolle.sequana.model.device.Pipe;
import com.github.cesarcolle.sequana.model.frequency.Frequency;
import com.github.cesarcolle.sequana.model.misc.Interval;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelBuilder extends SEQUANABaseListener {

    private Model model;
    private boolean built = false;


    public Model retrieve() {
        if(built){
            return model;
        }
        throw new RuntimeException("cannot retrieve a model that was not created");
    }


    private Map<String, Frequency> frequencies = new HashMap<>();
    private Map<String, Device> devices = new HashMap<>();
    private Map<String, Area> areas  = new HashMap<>();
    private Map<String, Pipe> pipes = new HashMap<>();

    @Override
    public void enterRoot(RootContext ctx) {
        this.built = false;
    }

    @Override
    public void exitRoot(RootContext ctx) {
        this.model = new Model(frequencies, devices, areas);
    }

    @Override
    public void enterArea(AreaContext ctx) {
        super.enterArea(ctx);
    }

    @Override
    public void enterArea_def(Area_defContext ctx) {
        super.enterArea_def(ctx);
    }

    @Override
    public void enterDevice(DeviceContext ctx) {
        String nameDevice = toString(ctx.nameDevice);
        Device_defContext dCtx = ctx.device_def();
        String hardWare = toString(dCtx.hardware().model);
        IntervalContext pinRangeIntervalCtx = dCtx.device_pin_range().interval();
        Interval interval = Interval.intervalFromString(toString(pinRangeIntervalCtx.min), toString(pinRangeIntervalCtx.max));
        List<Pipe> pipeDef = dCtx.pipe_list().elem.stream().map(token -> pipes.get(toString(token)) ).collect(Collectors.toList());

        addDevice(nameDevice, new Device(nameDevice,interval, pipeDef, HardwareModel.valueOf(hardWare)));
    }


    private void addDevice(String name, Device device){
        checkAlreadyDefined(device, devices);
        devices.put(name, device);
    }


    @Override
    public void enterFrequencie(FrequencieContext ctx) {
        String frequencyName = toString(ctx.name);
        
    }

    private void checkAlreadyDefined(Namable namable, Map elements){
        if (elements.containsKey(namable.getName())){
            throw new IllegalArgumentException(namable.getName() + " is already defined...");
        }
    }


    // Check properties
    private void checkProperties(){
        if (frequencies.isEmpty() || devices.isEmpty() || areas.isEmpty())
            throw new IllegalArgumentException("Not all the entity have been given");
    }


    //////////
    // Utils//
    //////////
    private static String toString(Token token) {
        return token.getText();
    }


}
