package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.model.Model;
import com.github.cesarcolle.sequana.model.device.HardwareModel;
import org.antlr.v4.runtime.CharStream;
import org.junit.Test;

import java.io.IOException;

import static com.github.cesarcolle.sequana.Main.buildModel;
import static com.github.cesarcolle.sequana.Main.getCharStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelBuilderTest {

    private final String pathSimpleSample = "src/test/resources/test-model.seq";
    private final String device2pinsConfiguration = "src/test/resources/test-model-devices-2-pins.seq";

    @Test
    public void testBuildingSimpleModel() throws IOException {
        String[] in = {pathSimpleSample};
        CharStream cstream = getCharStream(in);
        Model model = buildModel(cstream);

        assertEquals(1, model.getAreas().size());
        assertTrue(model.getAreas().containsKey("potager"));

        assertEquals(1, model.getDevices().get("potatoes").getPipeNumberFrequency().size());
        assertEquals(HardwareModel.ARDUINOUNO, model.getDevices().get("potatoes").getHardwareModel());

        assertEquals(4, model.getPipes().size());
        assertTrue(model.getFrequencies().get("daybyday").getDays().stream().filter(d -> d.day.equals("saturday")).anyMatch(d -> d.duration.getTime() == 3600));
    }

    @Test
    public void testBuilding2PinsDevices() throws IOException {
        String[] in = {device2pinsConfiguration};
        CharStream cstream = getCharStream(in);
        Model model = buildModel(cstream);

        assertEquals(1, model.getAreas().size());
        assertTrue(model.getAreas().containsKey("potager"));

        assertEquals(2, model.getDevices().size());
        assertEquals(2, model.getDevices().get("salade").getPipeNumberFrequency().size());
        assertTrue(model.getFrequencies().get("daybyday").getDays().stream().filter(d -> d.day.equals("saturday")).anyMatch(d -> d.duration.getTime() == 3600));
    }

}