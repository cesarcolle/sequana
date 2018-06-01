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

    private final String path = "src/test/resources/test-model.seq";

    @Test
    public void testBuildingSimpleModel() throws IOException {
        String[] in = {path};
        CharStream cstream = getCharStream(in);
        Model model = buildModel(cstream);

        assertEquals(1, model.getAreas().size());
        assertTrue(model.getAreas().containsKey("potager"));

        assertEquals(HardwareModel.ARDUINOUNO, model.getDevices().get("potatoes").getHardwareModel());
        assertEquals(4, model.getPipes().size());
    }

}