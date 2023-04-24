package Transformation.Reduction;

import java.awt.image.BufferedImage;

import static Constants.ANSIConstants.*;
import static Constants.Constants.*;

public class ImageReduction implements ImageReductionInterface {

    @Override
    public BufferedImage imageReduction(BufferedImage image) {
        if (LOGGER_MODE) {
            System.out.println("Reducing Image in " + (DO_BLACK_AND_WHITE ? ANSI_BLACK + "black " + ANSI_RESET + "and" + ANSI_WHITE + " white" + ANSI_RESET : ANSI_RED + "c" + ANSI_GREEN + "o" + ANSI_YELLOW + "l" + ANSI_BLUE + "o" + ANSI_PURPLE + "r" + ANSI_RESET));
        }

        if (DEBUG_MODE) {
            System.out.println("Pixel Type: " + image.getType() + "\n");
        }

        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        pixels = imageReductionWithIntArr(pixels);

        image.setRGB(0, 0, width, height, pixels, 0, width);

        if (LOGGER_MODE) {
            System.out.println("Reduced Image successfully");
        }
        return image;
    }

    @Override
    public int[] imageReductionWithIntArr(int[] pixels) {
        int pixelOffset = NUMBER_CALCULATOR.offsetCalculator(NUMBER_OF_COLORS);

        int pixel;
        for (int i = 0; i < pixels.length; i++) {
            pixel = pixels[i];
            if (DO_BLACK_AND_WHITE) {
                pixel = adaptPixelBlackWhite(pixel, pixelOffset);
            } else {
                pixel = adaptPixelColor(pixel, pixelOffset);
            }
            pixels[i] = pixel;
        }

        return pixels;
    }

    private int adaptPixelBlackWhite(int pixel, int offset) {
        int pixelA = pixel >> 24;

        if (!KEEP_SAME_TRANSPARENCY_MODE) {
            pixelA = 0xff << 24;
        }

        int pixelR = (pixel >> 16) & 255;
        int pixelG = (pixel >> 8) & 255;
        int pixelB = (pixel) & 255;
        int pixelAverage = (pixelR + pixelG + pixelB) / 3;

        int mod = pixelAverage % offset;
        int revMod = offset - mod;

        int pixelResultTemp;
        if (revMod > mod) {
            pixelResultTemp = pixelAverage - mod;
        } else {
            //assuming that it can't be bigger than 255
            pixelResultTemp = pixelAverage + revMod;
        }


        int pixelResult = pixelA + ((pixelResultTemp & 255) << 16) + ((pixelResultTemp & 255) << 8) + ((pixelResultTemp & 255));

        if (DEBUG_MODE) {
            System.out.println(Integer.toHexString(pixel) + "\t" + "RGB: " + pixelR + "\t" + pixelG + "\t" + pixelB + "\t" + "Average RGB: " + pixelAverage + "\t\t" +
                    "Modulo: " + mod + "\t" + revMod + "\t\t" +
                    "Results: " + pixelResultTemp + " " + Integer.toHexString(pixelResultTemp) + "\t" + Integer.toHexString(pixelResult));
        }
        return pixelResult;
    }

    private int adaptPixelColor(int pixel, int offset) {
        int pixelA = pixel >> 24;

        if (!KEEP_SAME_TRANSPARENCY_MODE) pixelA = 0xff;

        int pixelR = (pixel >> 16) & 255;
        int pixelG = (pixel >> 8) & 255;
        int pixelB = pixel & 255;
        int pixelAverage = (pixelR + pixelG + pixelB) / 3;

        int diff = pixelAverage % offset;

        if (diff > offset - diff) {
            diff = diff - offset;
        }

        pixelR = pixelR - diff;
        pixelG = pixelG - diff;
        pixelB = pixelB - diff;

        if (pixelR < 0) pixelR = 0;
        if (pixelG < 0) pixelG = 0;
        if (pixelB < 0) pixelB = 0;

        if (pixelR > 255) pixelR = 255;
        if (pixelG > 255) pixelG = 255;
        if (pixelB > 255) pixelB = 255;

        int pixelResult = (pixelA << 24) + (pixelR << 16) + (pixelG << 8) + pixelB;

        if (DEBUG_MODE) {
            System.out.println(Integer.toHexString(pixel) + "\t" + "RGB: " + pixelR + "\t" + pixelG + "\t" + pixelB + "\t" + "Average RGB: " + pixelAverage + "\t\t" +
                    "Diff: " + diff + "\t\t" +
                    "Result:\t" + Integer.toHexString(pixelResult));
        }
        return pixelResult;
    }

    @Override
    public BufferedImage imageReductionWithMask(BufferedImage image, BufferedImage mask) {
        //if (LOGGER_MODE) {
        System.out.println("Reduction with mask not yet implemented, reducing normally");
        //}
        return imageReduction(image);
    }

    @Override
    public int[] imageReductionWithMaskWithIntArr(int[] pixels, int[] maskPixels) {
        //if (LOGGER_MODE) {
        System.out.println("Reduction with mask not yet implemented, reducing normally");
        //}
        return imageReductionWithIntArr(pixels);
    }
}
