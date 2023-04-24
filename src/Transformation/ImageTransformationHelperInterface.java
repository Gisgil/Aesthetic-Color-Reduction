package Transformation;

public interface ImageTransformationHelperInterface {
    int indexCalculator(int width, int height, int column, int row);
    int indexCalculatorAutomaticPixelReturn(int width, int height, int column, int row, int[] pixels);
}
