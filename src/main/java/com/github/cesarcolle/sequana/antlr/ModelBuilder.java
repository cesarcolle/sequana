package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.grammar.SEQUANABaseListener;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser.*;

public class ModelBuilder extends SEQUANABaseListener {


    @Override
    public void enterRoot(RootContext ctx) {
        super.enterRoot(ctx);
    }

    @Override
    public void exitRoot(RootContext ctx) {
        super.exitRoot(ctx);
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
}
