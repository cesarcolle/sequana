package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.grammar.SEQUANABaseListener;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser.*;
import com.github.cesarcolle.sequana.model.*;
import com.github.cesarcolle.sequana.model.device.Device;
import com.github.cesarcolle.sequana.model.frequency.Frequency;

import java.util.Map;

public class ModelBuilder extends SEQUANABaseListener {

    private Model model;
    private boolean built = false;


    public Model retrieve() {
        if(built){
            return model;
        }
        throw new RuntimeException("cannot retrieve a model that was not created");
    }


    private Map<String, Frequency> frequencies ;
    private Map<String, Device> devices;
    private Map<String, Area> areas;


    @Override
    public void enterRoot(RootContext ctx) {
        this.built = false;
    }

    @Override
    public void exitRoot(RootContext ctx) {
        this.model = new Model(frequencies, devices, areas);
    }

    @Override
    public void enterHardware(HardwareContext ctx) {
        super.enterHardware(ctx);
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
        super.enterDevice(ctx);
    }

    @Override
    public void enterDevice_def(Device_defContext ctx) {
        super.enterDevice_def(ctx);
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



}
