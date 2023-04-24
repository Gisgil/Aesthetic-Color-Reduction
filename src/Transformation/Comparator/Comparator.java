package Transformation.Comparator;

import java.awt.image.BufferedImage;

import static Constants.ANSIConstants.*;
import static Constants.Constants.DEBUG_MODE;
import static Constants.Constants.LOGGER_MODE;

public class Comparator implements ComparatorInterface {
    private int[] pixels1;
    private int[] pixels2;

    private int[] differences;

    private boolean pointingTo1 = true;

    @Override
    public void addPixels(int[] pixels) {
        if (pointingTo1) {
            pixels1 = pixels;
        } else {
            pixels2 = pixels;
        }
        pointingTo1 = !pointingTo1;
        if (DEBUG_MODE) {
            System.out.println("Added pixels to Cache");
        }
    }

    @Override
    public void clearPixels() {
        pixels1 = null;
        pixels2 = null;
        differences = null;
        pointingTo1 = true;
    }

    @Override
    public void addPixels(BufferedImage image) {
        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        addPixels(pixels);
    }

    @Override
    public boolean containsDifferences() {
        if (DEBUG_MODE) {
            System.out.println("Checking for Difference");
        }

        intEquals();

        int minLength = Math.min(pixels1.length, pixels2.length);

        for (int i = 0; i < minLength; i++) {
            if (pixels1[i] != pixels2[i]) {
                if (DEBUG_MODE) {
                    System.out.println("Found differences in pixels");
                }
                return true;
            }
        }
        if (DEBUG_MODE) {
            System.out.println("Found no differences in pixels");
        }
        return false;
    }

    @Override
    public void findDifferences() {
        if (LOGGER_MODE) {
            System.out.println("Searching differences in pixels");
        }

        intEquals();

        int minLength = Math.min(pixels1.length, pixels2.length);

        differences = new int[minLength];

        int differencesCounter = 0;

        for (int i = 0; i < minLength; i++) {
            if (pixels1[i] != pixels2[i]) {
                differences[i] = 0xffff0000;
                differencesCounter++;
            }
        }
        if (LOGGER_MODE) {
            System.out.println("Found " + ANSI_HIGHLIGHT_COLOR + differencesCounter + ANSI_RESET + " differences in pixels");
        }
    }

    @Override
    public BufferedImage overlayDifferences(BufferedImage image) {
        if (LOGGER_MODE) {
            System.out.println("Starting Overlaying differences");
        }

        if (differences == null) {
            findDifferences();
        }
        int imageSize = image.getHeight() * image.getWidth();
        int min = Math.min(imageSize, differences.length);

        int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        for (int i = 0; i < min; i++) {
            if (differences[i] != 0) {
                pixels[i] = differences[i];
            }
        }

        image.setRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        if (LOGGER_MODE) {
            System.out.println("Overlayed differences");
        }
        return image;
    }

    @Override
    public BufferedImage visualizeDifferences(int width, int height, int type) {
        if (LOGGER_MODE) {
            System.out.println("Starting Visualization of differences");
        }
        if (differences == null) {
            findDifferences();
        }
        BufferedImage image = new BufferedImage(width, height, type);
        if (LOGGER_MODE) {
            System.out.println("Created new Image");
        }
        image.setRGB(0, 0, image.getWidth(), image.getHeight(), differences, 0, image.getWidth());
        if (LOGGER_MODE) {
            System.out.println("Visualized differences");
        }
        return image;
    }

    @Override
    public int[] getDifferencesIntArr() {
        if (LOGGER_MODE) {
            System.out.println("Getting differences");
        }
        if (differences == null) {
            findDifferences();
        }
        if (LOGGER_MODE) {
            System.out.println("Got differences");
        }
        return differences;
    }

    private void intEquals() {
        if (pixels1 == null || pixels2 == null) {
            System.out.println(ANSI_ERROR + "Please first add two arrays of pixels" + ANSI_RESET);
            return;
        }

        if (pixels1 == pixels2) {
            System.out.println(ANSI_ERROR + "Please first add two differing arrays of pixels" + ANSI_RESET);
        }
    }
}
