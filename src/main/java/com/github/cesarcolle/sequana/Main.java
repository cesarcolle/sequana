package com.github.cesarcolle.sequana;

import com.github.cesarcolle.sequana.antlr.ModelBuilder;
import com.github.cesarcolle.sequana.antlr.StopErrorListener;
import com.github.cesarcolle.sequana.generator.SequanaGenerator;
import com.github.cesarcolle.sequana.grammar.SEQUANALexer;
import com.github.cesarcolle.sequana.grammar.SEQUANAParser;
import com.github.cesarcolle.sequana.model.Model;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        CharStream charStream = getCharStream(args);
        Model sequanaModel = buildModel(charStream);

        SequanaGenerator generator = new SequanaGenerator(sequanaModel);
        generator.run();
    }


    public static CharStream getCharStream(String[] args) throws IOException {
        if (args.length < 1) {
            throw new RuntimeException("No path to the file");
        }
        Path input = Paths.get(new File(args[0]).toURI());
        return CharStreams.fromPath(input);
    }

    public static Model buildModel(CharStream stream) throws Exception {
        SEQUANALexer lexer = new SEQUANALexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new StopErrorListener());

        SEQUANAParser parser = new SEQUANAParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new StopErrorListener());

        ParseTreeWalker walker = new ParseTreeWalker();
        ModelBuilder builder = new ModelBuilder();

        walker.walk(builder, parser.root()); // parser.root() is the entry point of the grammar


        return builder.retrieve();
    }


}
