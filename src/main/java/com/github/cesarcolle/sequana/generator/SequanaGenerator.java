package com.github.cesarcolle.sequana.generator;

import com.github.cesarcolle.sequana.model.Model;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;

public class SequanaGenerator implements Runnable {
    private static String DIR_OUT_ARDUINO = "";
    private static String DIR_OUT_SERVER = "";

    private static String SEQUANA_SERVER_PATH = "src/main/resources/sequana-server/";
    private static String SEQUANA_SERVER_BUILD = SEQUANA_SERVER_PATH + "build.sbt";
    private static String SEQUANA_SERVER_WEB = SEQUANA_SERVER_PATH + "WebServer.scala";

    // model and engine
    private final VelocityEngine engine;
    private final VelocityContext context;
    private final  Model model;

    public SequanaGenerator(Model model){
        this.engine = new VelocityEngine();
        this.context = new VelocityContext();
        this.model = model;

        createContext();
    }

    private void createContext(){
        context.put("frequency", model.getFrequencies());
        context.put("area", model.getAreas());
        context.put("pipes", model.getPipes());
        context.put("devices", model.getDevices());
    }


    @Override
    public void run() {
        Template templateServer = engine.getTemplate(SEQUANA_SERVER_WEB);
        try {
            templateServer.merge(context, new FileWriter("out.out"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
