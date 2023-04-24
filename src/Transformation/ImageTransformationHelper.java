package Transformation;

public class ImageTransformationHelper implements ImageTransformationHelperInterface{
    @Override
    public int indexCalculator(int width, int height, int x, int y) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return 0;
        }
        return x + y * width;
    }

    @Override
    public int indexCalculatorAutomaticPixelReturn(int width, int height, int x, int y, int[] pixels) {
        if (y < 0 || x < 0 || y >= height || x >= width) {
            return 0;
        }
        return pixels[x + y * width];
    }
}
