package Transformation.AestheticClustering.SimpleAestheticClustering;

import static Constants.Constants.DEBUG_MODE;

public class PixelCache implements PixelCacheInterface {
    //private static final boolean DEBUG_MODE = false;
    private int[] pixelCache = new int[5];
    private int[] pixelCounter = new int[5];
    private int arrayIndex = 0;

    @Override
    public void addPixel(int[] pixel) {
        for (int p : pixel) {
            addPixelToCache(p);
        }
    }

    private void addPixelToCache(int pixel) {
        if (pixel == 0) {
            return;
        }

        if (DEBUG_MODE) {
            System.out.print(Integer.toHexString(pixel) + "\t");
        }

        //goes through the already filled indexes
        for (int i = 0; i < arrayIndex; i++) {
            if (pixelCache[i] == pixel) {
                pixelCounter[i]++;
                return;
            }
        }

        //if it is not yet present, it goes to the next field and fills it
        pixelCache[arrayIndex] = pixel;
        pixelCounter[arrayIndex] = 1;
        arrayIndex++;
    }

    @Override
    public void clearPixels() {
        arrayIndex = 0;
    }

    @Override
    public int returnSelectedPixel() {
        int maxCount = 0;
        int maxIndex = 0;

        for (int i = 0; i < arrayIndex; i++) {
            if (pixelCounter[i] > maxCount) {
                maxCount = pixelCounter[i];
                maxIndex = i;
            }
        }

        int selectedPixel = pixelCache[maxIndex];

        if (DEBUG_MODE)
            System.out.println("\t" + Integer.toHexString(selectedPixel));

        return selectedPixel;
    }
}
