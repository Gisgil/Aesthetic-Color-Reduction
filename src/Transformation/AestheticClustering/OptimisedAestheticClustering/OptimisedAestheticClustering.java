package Transformation.AestheticClustering.OptimisedAestheticClustering;

import Transformation.Comparator.Comparator;
import Transformation.Comparator.ComparatorInterface;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import static Constants.ANSIConstants.ANSI_HIGHLIGHT_COLOR;
import static Constants.ANSIConstants.ANSI_RESET;
import static Constants.Constants.*;

public class OptimisedAestheticClustering implements OptimisedAestheticClusteringInterface {
    ComparatorInterface COMPARATOR_FOR_OPTIMISING = new Comparator();


    @Override
    public void optimisedClustering(BufferedImage image) {
        if (LOGGER_MODE) {
            System.out.println("Optimising Aesthetic Clustering");
        }

        int width = image.getWidth();
        int height = image.getHeight();
        int[] pixels = image.getRGB(0, 0, width, height, null, 0, width);

        pixels = optimisedClustering(pixels, width, height);

        image.setRGB(0, 0, width, height, pixels, 0, width);
    }

    @Override
    public int[] optimisedClustering(int[] pixels, int width, int height) {
        int clusteringCounter = 0;
        COMPARATOR_FOR_OPTIMISING.addPixels(pixels);

        for (int i = 0; i < MAX_NUMBER_OF_AESTHETIC_CLUSTERS; i++) {
            pixels = IMAGE_AESTHETIC_CLUSTER.clustering(Arrays.copyOf(pixels, pixels.length), width, height);
            COMPARATOR_FOR_OPTIMISING.addPixels(pixels);
            clusteringCounter++;
            if (!COMPARATOR_FOR_OPTIMISING.containsDifferences()) {
                break;
            }
        }

        if (LOGGER_MODE) {
            System.out.println("Optimised Aesthetic Clustering with " + ANSI_HIGHLIGHT_COLOR + clusteringCounter + ANSI_RESET + " runs");
        }

        return pixels;
    }
}
