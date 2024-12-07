package com.asciify;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Command(name = "asciify", mixinStandardHelpOptions = true, version = "asciify 1.0", description = "Make images ASCII art.")
public class Main implements Runnable {
  @Option(names = { "-w", "--width" }, description = "Height of render frame.", defaultValue = "150")
  private int width;

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
      String asciiArt = Util.convertToAscii(image, width);
      System.out.println(asciiArt);
    } catch (IOException e) {
      System.err.println("Error reading the image file: " + e.getMessage());
    }
  }
}
