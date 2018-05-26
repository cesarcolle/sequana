package com.github.cesarcolle.sequana;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("hello world");

        CharStream charStream = getCharStream(args);
    }


    private static CharStream getCharStream(String[] args) throws IOException {
        if (args.length < 1){
            throw new RuntimeException("No path to the file");
        }
        Path input = Paths.get(new File(args[0]).toURI());
        return CharStreams.fromPath(input);
    }


    private static void buildModel(){}



}
