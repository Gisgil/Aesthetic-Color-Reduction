package Transformation;

import java.awt.image.BufferedImage;

public interface OperationOrderHandlerInterface {
    void autoColorReductionAnyOrder(BufferedImage image, String order);
    void autoColorReductionEveryCombination(BufferedImage image);
}
