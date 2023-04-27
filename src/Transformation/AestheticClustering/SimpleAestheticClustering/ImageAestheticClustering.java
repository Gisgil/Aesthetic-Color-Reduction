package Transformation.AestheticClustering.SimpleAestheticClustering;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import static Constants.ANSIConstants.*;
import static Constants.Constants.*;


public class ImageAestheticClustering implements ImageAestheticClusteringInterface {
    @Override
    public BufferedImage clustering(BufferedImage image) {
        if (DEBUG_MODE) {
            System.out.println("Starting Aesthetic Clustering from Image");
            System.out.println("Pixel Type: " + image.getType() + "\n");
        }

        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        pixels = simpleBlurSize1(pixels, width, height);

        image.setRGB(0, 0, width, height, pixels, 0, width);

        if (DEBUG_MODE) {
            System.out.println("Finished Aesthetic Clustering from Image, returning Image");
        }
        return image;
    }

    @Override
    public int[] clustering(int[] pixels, int width, int height) {
        if (AESTHETIC_CLUSTERING_SIZE == 2) {
            return simpleBlurSize2(pixels, width, height);
        }
        if (AESTHETIC_CLUSTERING_SIZE >= 3) {
            return simpleBlurSize3(pixels, width, height);
        }
        return simpleBlurSize1(pixels, width, height);
    }

    public int[] simpleBlurSize1(int[] pixels, int width, int height) {
        if (DEBUG_MODE) {
            System.out.println("Starting Aesthetic Clustering " + ANSI_HIGHLIGHT_COLOR + "size 1 " + ANSI_RESET + "from Array");
        }
        if (width * height != pixels.length) {
            System.out.println(ANSI_ERROR + "ERROR Differing lengths of array, width and height" + ANSI_RESET);
            return pixels;
        }
        //0 pixel, 1 top, 2, right, 3 down, 4 left
        int[] p = new int[5];

        /*
          1
        4 0 2
          3
         */

        PIXEL_CACHE.clearPixels();

        int index;
        for (int x = 0; x < width; x++) { //y
            for (int y = 0; y < height; y++) { //x

                index = IMAGE_TRANSFORMATION_HELPER.indexCalculator(width, height, x, y);
                //pixel
                p[0] = pixels[index];
                //pTOP
                p[1] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y - 1, pixels);
                //pRight
                p[2] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y, pixels);
                //pDown
                p[3] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y + 1, pixels);
                //pLeft
                p[4] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y, pixels);

                PIXEL_CACHE.addPixel(p);

                pixels[index] = PIXEL_CACHE.returnSelectedPixel();
                PIXEL_CACHE.clearPixels();
            }
        }

        if (DEBUG_MODE) {
            System.out.println("Fishined Aesthetic Clustering from Array, returning Array");
        }

        return pixels;
    }


    public int[] simpleBlurSize2(int[] pixels, int width, int height) {
        if (DEBUG_MODE) {
            System.out.println("Starting Aesthetic Clustering " + ANSI_HIGHLIGHT_COLOR + " size 2 " + ANSI_RESET + "from Array");
        }
        if (width * height != pixels.length) {
            System.out.println(ANSI_ERROR + "ERROR Differing lengths of array, width and height" + ANSI_RESET);
            return pixels;
        }

        int[] result = Arrays.copyOf(pixels, pixels.length);

        int[] p = new int[9];

        /*
        8 1 2
        7 0 3
        6 5 4
        */

        PIXEL_CACHE.clearPixels();

        int index;
        for (int x = 0; x < width; x++) { //x
            for (int y = 0; y < height; y++) { //y

                index = IMAGE_TRANSFORMATION_HELPER.indexCalculator(width, height, x, y);
                //pixel
                p[0] = pixels[index];
                //pTOP
                p[1] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y - 1, pixels);
                //pTopRight
                p[2] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y - 1, pixels);
                //pRight
                p[3] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y, pixels);
                //pRightDown
                p[4] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y + 1, pixels);
                //pDown
                p[5] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y + 1, pixels);
                //pDownLeft
                p[6] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y + 1, pixels);
                //pLeft
                p[7] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y, pixels);
                //pLeftUP
                p[8] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y - 1, pixels);

                PIXEL_CACHE.addPixel(p);

                result[index] = PIXEL_CACHE.returnSelectedPixel();
                PIXEL_CACHE.clearPixels();
            }
        }
        if (DEBUG_MODE) {
            System.out.println("Fishined Aesthetic Clustering from Array, returning Array");
        }

        return result;
    }


    public int[] simpleBlurSize3(int[] pixels, int width, int height) {
        if (DEBUG_MODE) {
            System.out.println("Starting Aesthetic Clustering " + ANSI_HIGHLIGHT_COLOR + " size 3 " + ANSI_RESET + "from Array");
        }
        if (width * height != pixels.length) {
            System.out.println(ANSI_ERROR + "ERROR Differing lengths of array, width and height" + ANSI_RESET);
            return pixels;
        }
        int[] result = Arrays.copyOf(pixels, pixels.length);

        int[] p = new int[21];

        /*
           20 2  3
        18 19 1  4  5
        16 17 0  7  6
        15 14 12 9  8
           13 11 10
         */

        PIXEL_CACHE.clearPixels();

        int index;
        for (int x = 0; x < width; x++) { //y
            for (int y = 0; y < height; y++) { //x

                index = IMAGE_TRANSFORMATION_HELPER.indexCalculator(width, height, x, y);
                //pixel
                p[0] = pixels[index];
                //pTOP
                p[1] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y - 1, pixels);
                //pTopTop
                p[2] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y - 2, pixels);
                //pTopTopRight
                p[3] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y - 2, pixels);
                //pTopRight
                p[4] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y - 1, pixels);
                //pTopRightRight
                p[5] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 2, y - 1, pixels);
                //pRightRight
                p[6] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 2, y, pixels);
                //pRight
                p[7] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y, pixels);
                //pRightRightDown
                p[8] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 2, y + 1, pixels);
                //pRightDown
                p[9] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y + 1, pixels);
                //pRightDownDown
                p[10] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x + 1, y + 2, pixels);
                //pDownDown
                p[11] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y + 2, pixels);
                //pDown
                p[12] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x, y + 1, pixels);
                //pDownDownLeft
                p[13] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y + 2, pixels);
                //pDownLeft
                p[14] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y + 1, pixels);
                //pDownLeftLeft
                p[15] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 2, y + 1, pixels);
                //pLeftLeft
                p[16] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 2, y, pixels);
                //pLeft
                p[17] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y, pixels);
                //pLeftLeftUP
                p[18] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 2, y + 1, pixels);
                //pLeftUP
                p[19] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y + 1, pixels);
                //pLeftUPUP
                p[20] = IMAGE_TRANSFORMATION_HELPER.indexCalculatorAutomaticPixelReturn(width, height, x - 1, y + 2, pixels);

                PIXEL_CACHE.addPixel(p);

                result[index] = PIXEL_CACHE.returnSelectedPixel();
                PIXEL_CACHE.clearPixels();
            }
        }
        if (DEBUG_MODE) {
            System.out.println("Fishined Aesthetic Clustering from Array, returning Array");
        }

        return result;
    }
}
