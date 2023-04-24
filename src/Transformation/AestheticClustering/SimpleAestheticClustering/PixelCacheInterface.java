package Transformation.AestheticClustering.SimpleAestheticClustering;

public interface PixelCacheInterface {

    void addPixel(int[] pixel);

    void clearPixels();

    int returnSelectedPixel();

}
