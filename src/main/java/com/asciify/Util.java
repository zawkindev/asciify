package com.asciify;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Util {

  // Higher density map for better detail (dark to light)
  private static final String DENSITY_MAP = "@#W$9876543210?!abc+=;:-_,. ";

  public static String convertToAscii(BufferedImage image, int outputWidth) {
    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();

    // Calculate aspect ratio and adjust for character height
    double aspectRatio = (double) originalHeight / originalWidth;
    int outputHeight = (int) (outputWidth * aspectRatio * 0.55); // Tweaked for better proportions

    // Resize the image
    Image scaledImage = image.getScaledInstance(outputWidth, outputHeight, Image.SCALE_SMOOTH);
    BufferedImage grayscaleImage = toGrayscale(toBufferedImage(scaledImage));

    StringBuilder asciiArt = new StringBuilder();

    // Convert each pixel to ASCII character
    for (int y = 0; y < grayscaleImage.getHeight(); y++) {
      for (int x = 0; x < grayscaleImage.getWidth(); x++) {
        int pixel = grayscaleImage.getRGB(x, y);
        int brightness = pixel & 0xFF; // Extract brightness (0–255)
        char asciiChar = brightnessToAscii(brightness);
        asciiArt.append(asciiChar);
      }
      asciiArt.append(System.lineSeparator());
    }
    return asciiArt.toString();
  }

  private static char brightnessToAscii(int brightness) {
    int index = (int) ((brightness / 255.0) * (DENSITY_MAP.length() - 1));
    return DENSITY_MAP.charAt(index);
  }

  private static BufferedImage toGrayscale(BufferedImage image) {
    BufferedImage grayscale = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
    Graphics2D g = grayscale.createGraphics();
    g.drawImage(image, 0, 0, null);
    g.dispose();
    return grayscale;
  }

  private static BufferedImage toBufferedImage(Image img) {
    if (img instanceof BufferedImage) {
      return (BufferedImage) img;
    }

    BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null),
        BufferedImage.TYPE_INT_ARGB);
    Graphics g = bufferedImage.getGraphics();
    g.drawImage(img, 0, 0, null);
    g.dispose();
    return bufferedImage;
  }

  public static String convertToAsciiAsHtml(BufferedImage image, int outputWidth) {
    int originalWidth = image.getWidth();
    int originalHeight = image.getHeight();
    double aspectRatio = (double) originalHeight / originalWidth;
    int outputHeight = (int) (outputWidth * aspectRatio * 0.55);

    Image scaledImage = image.getScaledInstance(outputWidth, outputHeight, Image.SCALE_SMOOTH);
    BufferedImage grayscaleImage = toGrayscale(toBufferedImage(scaledImage));

    StringBuilder html = new StringBuilder(
        "<!DOCTYPE html><html><body><pre style=\"font-size: 6px; line-height: 6px;\">\n");

    for (int y = 0; y < grayscaleImage.getHeight(); y++) {
      for (int x = 0; x < grayscaleImage.getWidth(); x++) {
        int pixel = grayscaleImage.getRGB(x, y);
        int brightness = pixel & 0xFF;
        char asciiChar = brightnessToAscii(brightness);
        html.append(asciiChar);
      }
      html.append("\n");
    }

    html.append("</pre></body></html>");
    return html.toString();
  }
}
