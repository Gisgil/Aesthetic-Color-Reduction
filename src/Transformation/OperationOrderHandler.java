package Transformation;

import Constants.Constants;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Arrays;
import java.util.Stack;

import static Constants.ANSIConstants.*;
import static Constants.Constants.*;

public class OperationOrderHandler implements OperationOrderHandlerInterface {
    int stackSize = 0;
    Stack<String> stringStack = null;

    @Override
    public void autoColorReductionEveryCombination(BufferedImage image) {
        stringStack = new Stack<>();
        stackSize = 0;

        recursiveStringImplementations("", ALL_EDITING_OPTIONS.length());

        String[] stringArr = stringStack.toArray(new String[stackSize]);
        for (String string : stringArr) {
            if (string == null || string.equals("")) {
                continue;
            }
            autoColorReductionAnyOrder(BufferedImageCopy(image), string);
        }
    }

    private void recursiveStringImplementations(String prefix, int maxLength) {
        if (prefix == null) {
            return;
        }

        if (!prefix.equals("")) {
            stringStack.push(prefix);
        }
        stackSize++;

        if (prefix.length() >= maxLength) {
            return;
        }

        for (char option : Constants.ALL_EDITING_OPTIONS.toCharArray()) {
            if (prefix.contains("" + option)) {
                continue;
            }

            recursiveStringImplementations(prefix + option, maxLength);
        }

    }

    private BufferedImage BufferedImageCopy(BufferedImage image) {
        ColorModel cm = image.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = image.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    @Override
    //to begin without difference implementation
    public void autoColorReductionAnyOrder(BufferedImage image, String order) {
        if (order == null || order.length() == 0) {
            return;
        }

        OPERATION_ORDER = order;

        int width = image.getWidth();
        int height = image.getHeight();

        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        if (STORE_DIFFERENCES) {
            COMPARATOR.clearPixels();
            COMPARATOR.addPixels(Arrays.copyOf(pixels, pixels.length));
        }

        OPERATION_ORDER = OPERATION_ORDER.toUpperCase();
        char[] options = OPERATION_ORDER.toCharArray();
        for (char a : options) {
            pixels = switchCaseTransformationOptions(pixels, a, width, height);
        }

        if (STORE_DIFFERENCES) {
            COMPARATOR.addPixels(pixels);
        }

        image.setRGB(0, 0, width, height, pixels, 0, width);

        String pathname = USER_INPUT_HANDLER.generatePathname(RESULT_SUFFIX + OPERATION_ORDER, OPERATION_ORDER);
        FILE_HANDLER.setImageFromPathname(image, pathname);

        if (STORE_DIFFERENCES) {
            image = COMPARATOR.overlayDifferences(image);
            pathname = USER_INPUT_HANDLER.generatePathname(RESULT_SUFFIX + OPERATION_ORDER + DIFFERENCE_CHAR, OPERATION_ORDER);
            FILE_HANDLER.setImageFromPathname(image, pathname);
        }
    }

    private int[] switchCaseTransformationOptions(int[] pixels, char option, int width, int height) {
        switch (option) {
            case REDUCTION_CHAR -> {
                System.out.println(ANSI_HIGHLIGHT_COLOR + "Reduction in " + (DO_BLACK_AND_WHITE ? ANSI_BLACK_AND_WHITE : ANSI_COLOR) + ANSI_RESET);
                return IMAGE_REDUCTION.imageReductionWithIntArr(pixels);
            }
            case CLUSTER_CHAR -> {
                System.out.println(ANSI_HIGHLIGHT_COLOR + "Clustering" + ANSI_RESET);
                return OPTIMISED_AESTHETIC_CLUSTERING.optimisedClustering(pixels, width, height);
            }
            case BLUR_CHAR -> {
                System.out.println(ANSI_HIGHLIGHT_COLOR + "Blurring" + ANSI_RESET);
                return BLURS.smoothingIntArr(width, height, pixels);
            }
        }
        return pixels;
    }

}
