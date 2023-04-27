import Transformation.Comparator.Comparator;

import java.awt.image.BufferedImage;

import static Constants.Constants.*;
import static Constants.UserMessagesConstants.*;
public class AestheticColorReduction {

    private static BufferedImage image;

    public static void main(String[] args) {
        USER_INPUT_HANDLER.processArgs(args);
        if (ARGS_INPUT) {
            image = FILE_HANDLER.getImageFromPathname();
            OPERATION_ORDER = validateOperationOrder();
        } else {
            getUserInputConstants();
        }

        if (STORE_DIFFERENCES) {
            COMPARATOR = new Comparator();
        }

        if (OPERATION_ORDER.equals(ALL_EDITING_STRING)) {
            OPERATION_ORDER_HANDLER.autoColorReductionEveryCombination(image);
        } else {
            OPERATION_ORDER_HANDLER.autoColorReductionAnyOrder(image, OPERATION_ORDER);
        }
    }

    private static void getUserInputConstants() {
        PATHNAME_ORIGIN = USER_INPUT_HANDLER.getImagePathname();

        //Getting the image
        image = FILE_HANDLER.getImageFromPathname();
        //doing it now, as if the pathname is wrong, it is useless to do the rest of the inputs

        OPERATION_ORDER = USER_INPUT_HANDLER.getStringUserInput(GET_OPERATION_ORDER_MESSAGE);

        if (!DO_EXPANDED_INPUT) return;

        DO_BLACK_AND_WHITE = USER_INPUT_HANDLER.getBooleanUserInput(GET_BLACK_AND_WHITE_MESSAGE);

        OPERATION_ORDER = validateOperationOrder();

        if (OPERATION_ORDER.contains("B")) {
            BLUR_SIZE = USER_INPUT_HANDLER.getIntUserInput(GET_BLUR_SIZE_MESSAGE);
        }
    }

    private static String validateOperationOrder() {
        if (OPERATION_ORDER.length() == 0) {
            return DEFAULT_EDITING_OPTION;
        }

        OPERATION_ORDER = OPERATION_ORDER.toUpperCase().replaceAll(" ", "");
        if (OPERATION_ORDER.contains(ALL_EDITING_STRING)) {
            return ALL_EDITING_STRING;
        }

        char i;
        for (int x = 0; x <= 127; x++) {
            i = (char) x;
            if (containsChar(i)) {
                continue;
            }
            OPERATION_ORDER = OPERATION_ORDER.replaceAll(String.valueOf(i), "");
        }
        return OPERATION_ORDER;
    }

    private static boolean containsChar(char x) {
        for (char y : ALL_EDITING_OPTIONS) {
            if (x == y) {
                return true;
            }
        }
        return false;
    }
}