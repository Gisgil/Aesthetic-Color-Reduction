package Transformation.Blurs;

import java.awt.image.BufferedImage;

public interface BlursInterface {
    BufferedImage smoothing(BufferedImage image);
    int[] smoothingIntArr(int width, int height, int[] pixels);
}
