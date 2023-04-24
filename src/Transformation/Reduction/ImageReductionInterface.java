package Transformation.Reduction;

import java.awt.image.BufferedImage;

public interface ImageReductionInterface {

    BufferedImage imageReduction(BufferedImage image);
    int[] imageReductionWithIntArr(int[] pixels);

    BufferedImage imageReductionWithMask(BufferedImage image, BufferedImage mask);
    int[] imageReductionWithMaskWithIntArr(int[] pixels, int[] maskPixels);
}
