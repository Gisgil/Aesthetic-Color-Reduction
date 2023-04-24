package Transformation.AestheticClustering.OptimisedAestheticClustering;

import java.awt.image.BufferedImage;

public interface OptimisedAestheticClusteringInterface {
    void optimisedClustering(BufferedImage image);
    int[] optimisedClustering(int[] pixels, int width, int height);
}
