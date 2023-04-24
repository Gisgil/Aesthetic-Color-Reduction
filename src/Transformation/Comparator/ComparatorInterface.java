package Transformation.Comparator;

import java.awt.image.BufferedImage;

public interface ComparatorInterface {

    void addPixels(int[] pixels);
    void clearPixels();

    void addPixels(BufferedImage image);

    boolean containsDifferences();

    void findDifferences();

    BufferedImage visualizeDifferences(int width, int height, int type);

    BufferedImage overlayDifferences(BufferedImage image);

    int[] getDifferencesIntArr();
}
