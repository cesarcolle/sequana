package com.github.cesarcolle.sequana.antlr;

import com.github.cesarcolle.sequana.model.Model;
import org.antlr.v4.runtime.CharStream;
import org.junit.Before;

import java.io.IOException;
import java.util.Collections;

import static com.github.cesarcolle.sequana.Main.buildModel;
import static com.github.cesarcolle.sequana.Main.getCharStream;
import static org.junit.Assert.*;

public class ModelBuilderTest {

    private final String path = "src/test/resources/test-model.seq";


    public void testBuildingSimpleModel() throws IOException {
        CharStream cstream = getCharStream((String[]) Collections.singleton(path).toArray());
        Model model = buildModel(cstream);

        System.out.println(model);
    }

}