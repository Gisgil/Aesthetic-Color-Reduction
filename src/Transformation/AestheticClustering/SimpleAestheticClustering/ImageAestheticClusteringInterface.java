package Transformation.AestheticClustering.SimpleAestheticClustering;

import java.awt.image.BufferedImage;

public interface ImageAestheticClusteringInterface {

    BufferedImage clustering(BufferedImage image);
    int[] clustering(int[] image, int width, int height);
}
