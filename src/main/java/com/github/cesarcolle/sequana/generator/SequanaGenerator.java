package com.github.cesarcolle.sequana.generator;

import com.github.cesarcolle.sequana.model.Model;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SequanaGenerator implements Runnable {
    private static String DIR_OUT_ARDUINO = "device/";
    private static String DIR_OUT_SERVER = "server/";
    private static String TREE_SCALA_APP =  "src/main/scala/";

    private static String SEQUANA_SERVER_PATH = "src/main/resources/sequana-server/";
    private static String SEQUANA_SERVER_BUILD = SEQUANA_SERVER_PATH + "build.sbt";
    private static String SEQUANA_SERVER_WEB = SEQUANA_SERVER_PATH + "WebServer.sm";

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


    private void createTree(File newExec) {
        newExec.mkdirs();
    }

    private void createServerTemplate() throws IOException {
        // generate server tree.
        System.out.println("create tree");
        String webServerPath = DIR_OUT_SERVER + TREE_SCALA_APP;
        createTree(new File(webServerPath));

        System.out.println("copy sbt file");
        File serverBuildFile = new File(SEQUANA_SERVER_BUILD);
        Files.copy(serverBuildFile.toPath(), new File(DIR_OUT_SERVER + "build.sbt").toPath(), REPLACE_EXISTING);

        // get the
        Template webServerTemplate = engine.getTemplate(SEQUANA_SERVER_WEB);

        File webServer = new File(webServerPath + "out");
        FileWriter fw = new FileWriter(webServer);
        webServerTemplate.merge(context, fw);
        fw.close();
    }


    @Override
    public void run() {
        try {
            createServerTemplate();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
