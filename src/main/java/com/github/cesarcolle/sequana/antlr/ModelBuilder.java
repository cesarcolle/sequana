package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.grammar.SEQUANABaseListener;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser.*;
import com.github.cesarcolle.sequana.model.*;
import com.github.cesarcolle.sequana.model.device.Device;
import com.github.cesarcolle.sequana.model.frequency.Frequency;
import org.antlr.v4.runtime.Token;

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
        
    }


    private void addDevice(String name, Device device){
        checkAlreadyDefined(device, devices);
        devices.put(name, device);
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
