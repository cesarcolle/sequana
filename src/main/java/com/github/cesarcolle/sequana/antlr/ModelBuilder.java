package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.grammar.SEQUANABaseListener;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser.*;
import com.github.cesarcolle.sequana.model.Area;
import com.github.cesarcolle.sequana.model.Model;
import com.github.cesarcolle.sequana.model.Namable;
import com.github.cesarcolle.sequana.model.device.Device;
import com.github.cesarcolle.sequana.model.device.HardwareModel;
import com.github.cesarcolle.sequana.model.device.Pipe;
import com.github.cesarcolle.sequana.model.frequency.Day;
import com.github.cesarcolle.sequana.model.frequency.Duration;
import com.github.cesarcolle.sequana.model.frequency.Frequency;
import com.github.cesarcolle.sequana.model.misc.Interval;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ModelBuilder extends SEQUANABaseListener {

    private Model model;
    private boolean built = false;
    private Map<String, Frequency> frequencies = new HashMap<>();
    private Map<String, Device> devices = new HashMap<>();
    private Map<String, Area> areas = new HashMap<>();
    private Map<String, Pipe> pipes = new HashMap<>();

    private static void checkAlreadyDefined(Namable namable, Map elements) {
        if (elements.containsKey(namable.getName())) {
            throw new IllegalArgumentException(namable.getName() + " is already defined...");
        }
    }

    ///////////
    // Utils //
    ///////////
    private static String toString(Token token) {
        return token.getText();
    }

    private static Integer toInteger(Token token) {
        return Integer.parseInt(token.getText());
    }

    private static Boolean toBoolean(Token token) {
        return Boolean.parseBoolean(token.getText());
    }

    public Model retrieve() throws Exception {
        if (built) {
            model.checkHygieneModel();
            return model;
        }
        throw new RuntimeException("cannot retrieve a model that was not created");
    }

    @Override
    public void enterRoot(RootContext ctx) {
        this.built = false;
    }

    @Override
    public void exitRoot(RootContext ctx) {
        checkProperties();
        this.model = new Model(frequencies, devices, areas, pipes);
        this.built = true;
    }

    @Override
    public void enterArea(AreaContext ctx) {
        String nameArea = toString(ctx.name);
        List<Device> devArea = ctx.area_def().list_devices().elem.stream().map(ModelBuilder::toString).map(devices::get).collect(Collectors.toList());
        addArea(nameArea, new Area(nameArea, devArea));
    }

    private void addArea(String name, Area area) {
        checkAlreadyDefined(area, areas);
        areas.put(name, area);
    }

    @Override
    public void enterDevice(DeviceContext ctx) {
        String nameDevice = toString(ctx.nameDevice);
        Device_defContext dCtx = ctx.device_def();
        String hardWare = toString(dCtx.hardware().model);

        IntervalContext pinRangeIntervalCtx = dCtx.device_pin_range().interval();
        Interval interval = Interval.intervalFromString(toString(pinRangeIntervalCtx.min), toString(pinRangeIntervalCtx.max));

        Map<Integer, Frequency> pinsConfiguration = dCtx.pins_configuration().stream()
                .collect(Collectors.toMap(ctxPins -> toInteger(ctxPins.number),
                        ctxPins -> frequencies.get(toString(ctxPins.frequencie_name))));

        List<Pipe> pipeDef = dCtx.pipe_list().elem.stream().map(token -> pipes.get(toString(token))).collect(Collectors.toList());

        addDevice(nameDevice, new Device(nameDevice, interval, pipeDef,pinsConfiguration, HardwareModel.valueOf(hardWare)));
    }

    private void addDevice(String name, Device device) {
        checkAlreadyDefined(device, devices);
        devices.put(name, device);
    }

    @Override
    public void enterFrequency(FrequencyContext ctx) {
        String nameFrequency = toString(ctx.name);

        List<Frequency_defContext> freqCtx = ctx.frequency_def();
        List<Day> days = freqCtx.stream()
                .map(freqDef -> new Day(toString(freqDef.day), toString(freqDef.hours), Duration.stringToDuration(toString(freqDef.period))))
                .collect(Collectors.toList());
        addFrequency(nameFrequency, new Frequency(nameFrequency, days));
    }

    private void addFrequency(String name, Frequency frequency) {
        checkAlreadyDefined(frequency, frequencies);
        frequencies.put(name, frequency);
    }

    @Override
    public void enterPipe(PipeContext ctx) {
        String pipeDef = toString(ctx.name);
        Integer pins = toInteger(ctx.pip_def().pipe_pin().amount);
        Pipe pipe = new Pipe(pipeDef, pins);
        Optional<Flow_meterContext> optionFlow = Optional.ofNullable(ctx.pip_def().flow_meter());
        optionFlow.ifPresent(flow -> pipe.isFlow(toBoolean(flow.status)));
        addPipe(pipeDef, pipe);
    }

    private void addPipe(String name, Pipe pipe) {
        checkAlreadyDefined(pipe, pipes);
        pipes.put(name, pipe);
    }

    // Check properties
    private void checkProperties() {
        if (frequencies.isEmpty() || devices.isEmpty() || areas.isEmpty())
            throw new IllegalArgumentException("Not all the entity have been given");
    }


}
