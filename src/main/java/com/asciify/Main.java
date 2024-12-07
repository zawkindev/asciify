package com.asciify;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Command(name = "asciify", mixinStandardHelpOptions = true, version = "asciify 1.0", description = "Make images ASCII art.")
public class Main implements Runnable {
  @Option(names = { "-w", "--width" }, description = "Height of render frame.", defaultValue = "150")
  private int width;

  @Option(names = { "-o", "--output" }, description = "Name of the html file.", arity = "0..1")
  private String output;

  @Parameters(index = "0", description = "The path to the image file to convert.")
  private String imagePath;

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Usage: java ImageToAscii <image-path>");
      return;
    }

    int exitCode = new CommandLine(new Main()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public void run() {
    try {
      BufferedImage image = ImageIO.read(new File(imagePath));
      if (image == null) {
        System.err.println("Error: Unable to read the image. Please check the file path and format.");
        return;
      }

      if (output != null) {
        if (output.isBlank()) {
          output = "output.html"; // Set default name if no value is specified
        }
        String htmlContent = Util.convertToAsciiAsHtml(image, width);
        Files.writeString(Paths.get(output), htmlContent);
        System.out.println("HTML file generated: " + output);
        return;
      }

      String asciiArt = Util.convertToAscii(image, width);
      System.out.println(asciiArt);

    } catch (IOException e) {
      System.err.println("Error reading the image file: " + e.getMessage());
    }
  }
}
