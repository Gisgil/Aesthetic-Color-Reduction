package Transformation.Blurs;

import Constants.Constants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import static Constants.Constants.BLUR_SIZE;
import static Constants.Constants.DEBUG_MODE;

// Main class
public class Blurs implements BlursInterface {
    //taken and adapted from https://www.geeksforgeeks.org/java-program-to-blur-image-using-smoothing/;
    @Override
    public BufferedImage smoothing(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        pixels = smoothingIntArr(width, height, pixels);

        image.setRGB(0, 0, width, height, pixels, 0, width);
        return image;
    }

    @Override
    public int[] smoothingIntArr(int width, int height, int[] pixels) {
        if (Constants.LOGGER_MODE) {
            System.out.println("Starting blur");
        }

        int[] result = Arrays.copyOf(pixels, pixels.length);

        Color[] color;

        // Setting dimensions for the image to be processed
        int colorIndex = 0;
        int max = 400;
        int alpha = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        color = new Color[max];

        // Now this core section of code is responsible for
        // blurring of an image

        int y = 1;
        int x = 1;
        int ex = 5;
        int d = 0;
        int yRotating, xRotating;

        int pixel;
        int index;


        // Running nested for loops for each pixel
        // and blurring it
        for (x = BLUR_SIZE; x < height - BLUR_SIZE; x++) {
            for (y = BLUR_SIZE; y < width - BLUR_SIZE; y++) {
                colorIndex = 0;

                for (xRotating = x - BLUR_SIZE; xRotating < x + BLUR_SIZE; xRotating++) {
                    for (yRotating = y - BLUR_SIZE; yRotating < y + BLUR_SIZE; yRotating++) {
                        index = Constants.IMAGE_TRANSFORMATION_HELPER.indexCalculator(width, height, yRotating, xRotating);
                        if (DEBUG_MODE) {
                            System.out.print(index + "\t");
                        }
                        color[colorIndex++] = new Color(pixels[index]);
                    }
                }

                // Smoothing colors of image
                for (d = 0; d < colorIndex; d++) {
                    alpha = alpha + color[d].getAlpha();
                    red = red + color[d].getRed();
                    green = green + color[d].getGreen();
                    blue = blue + color[d].getBlue();
                }
                alpha = alpha / (colorIndex);
                red = red / (colorIndex);
                green = green / (colorIndex);
                blue = blue / (colorIndex);

                if (red > 255) red = 255;
                if (green > 255) green = 255;
                if (blue > 255) blue = 255;

                if (red < 0) red = 0;
                if (green < 0) green = 0;
                if (blue < 0) blue = 0;

                index = Constants.IMAGE_TRANSFORMATION_HELPER.indexCalculator(width, height, y, x);
                int colorResult = (alpha << 24) + (red << 16) + (green << 8) + blue;
                result[index] = colorResult;
            }
        }

        if (Constants.LOGGER_MODE) {
            System.out.println("Image blurred successfully!");
        }
        return result;
    }
}
