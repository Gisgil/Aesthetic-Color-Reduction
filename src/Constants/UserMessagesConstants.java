package Constants;

import static Constants.Constants.*;

public class UserMessagesConstants {
    //USER INTPUT MESSAGES
    public static final String GET_IMAGE_PATHNAME_MESSAGE = "Which image do you want to reduce?";
    public static final String GET_OPERATION_ORDER_MESSAGE = "In what order do you want to change your image? (" + REDUCTION_CHAR + "- Reduction | " + CLUSTER_CHAR + "- Clustering | " + BLUR_CHAR + " - Blur | " + ALL_EDITING_STRING + " - All Combinations | Blank - " + DEFAULT_EDITING_OPTION + ")";
    public static final String GET_BLACK_AND_WHITE_MESSAGE = "Do you want your image to become black and white or stay in color? (y for black and white, n for color)";
    public static final String GET_BLUR_SIZE_MESSAGE = "How much should your image be blurred? (default 5)";
    public static final String WRONG_INPUT_MESSAGE = "Invalid input, please try again";

    //USER INPUT MESSAGES NOT IN USE
    public static final String GET_NUMBER_OF_COLORS_MESSAGE = "To how many colors do you want to reduce your image to?";
    public static final String GET_STORE_ONLY_FINAL_RESULT = "Do you only want to store your final result? (y for yes, n for no)";
    public static final String GET_DEBUG_MODE_MESSAGE = "Do you want to activate debug mode? (y for yes, n for no)";
    public static final String GET_LOGGER_MODE_MESSAGE = "Do you want to activate logger mode? (y for yes, n for no)";
    public static final String GET_KEEP_SAME_TRANSPARENCY_MODE_MESSAGE = "Do you want your image to keep the same transparency? (y for yes, n for no)";
    public static final String GET_AESTHETIC_CLUSTERING_MESSAGE = "Do you want to aesthetically cluster your image? (y for yes, n for no)";
    public static final String GET_AESTHETIC_CLUSTERING_SIZE_MESSAGE = "How big do you want to aesthetically cluster your image? (1 to 3)";
    public static final String GET_OPTIMISED_AESTHETIC_CLUSTERING_MESSAGE = "Do you want to use optimised aesthetic clustering for your image? (y for yes, n for no)";
    public static final String GET_MAX_NUMBER_OF_AESTHETIC_CLUSTERS_MESSAGE = "How many times do you want to aesthetically cluster your image at most?";
}
