package Transformation.Reduction.NumberOfColors;

import static Constants.Constants.*;
import static Constants.ANSIConstants.*;

public class NumberOfColorCalculator implements NumberOfColorCalculatorInterface {
    @Override
    public int offsetCalculator(int numberOfColors) {
        if (LOGGER_MODE) {
            System.out.println("Calculating the color offset for " + ANSI_HIGHLIGHT_COLOR + numberOfColors + ANSI_RESET + " colors");
        }
        int colorOffset = 17;
        if (LOGGER_MODE) {
            System.out.println("Color offset: " + ANSI_HIGHLIGHT_COLOR + colorOffset + ANSI_RESET);
        }
        return colorOffset;
    }
}
