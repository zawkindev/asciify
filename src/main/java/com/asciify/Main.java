package com.asciify;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "asciify", mixinStandardHelpOptions = true, version = "asciify 1.0",
        description = "Make images ASCII art.")
public class Main implements Runnable {

    @Option(names = {"-p", "--path"}, description = "Path to the image.")
    private String path;

    @Option(names = {"-r", "--resolution"}, description = "Resolution of render frame.", defaultValue = "1")
    private int resolution;

    @Option(names = {"-w", "--width"}, description = "Height of render frame.", defaultValue = "1")
    private int width;

    @Option(names = {"--height"}, description = "Width of render frame.", defaultValue = "1")
    private int height;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("running");
    }
}
