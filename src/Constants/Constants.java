package Constants;

import Transformation.Comparator.ComparatorInterface;
import UserAndFileHandler.FileHandlerInterface;
import UserAndFileHandler.Filehandler;
import Transformation.Reduction.NumberOfColors.NumberOfColorCalculator;
import Transformation.Reduction.NumberOfColors.NumberOfColorCalculatorInterface;
import Transformation.AestheticClustering.OptimisedAestheticClustering.OptimisedAestheticClustering;
import Transformation.AestheticClustering.OptimisedAestheticClustering.OptimisedAestheticClusteringInterface;
import Transformation.AestheticClustering.SimpleAestheticClustering.ImageAestheticClustering;
import Transformation.AestheticClustering.SimpleAestheticClustering.ImageAestheticClusteringInterface;
import Transformation.AestheticClustering.SimpleAestheticClustering.PixelCache;
import Transformation.AestheticClustering.SimpleAestheticClustering.PixelCacheInterface;
import Transformation.Blurs.Blurs;
import Transformation.Blurs.BlursInterface;
import Transformation.ImageTransformationHelper;
import Transformation.ImageTransformationHelperInterface;
import Transformation.OperationOrderHandler;
import Transformation.OperationOrderHandlerInterface;
import Transformation.Reduction.ImageReduction;
import Transformation.Reduction.ImageReductionInterface;
import UserAndFileHandler.UserInputHandler;
import UserAndFileHandler.UserInputHandlerInterface;

import java.util.Scanner;

public class Constants {
    //NECESSARY CLASSES
    public static final UserInputHandlerInterface USER_INPUT_HANDLER = new UserInputHandler();
    public static final FileHandlerInterface FILE_HANDLER = new Filehandler();
    public static final OperationOrderHandlerInterface OPERATION_ORDER_HANDLER = new OperationOrderHandler();
    //DEPENDING ON USER INPUT NEEDED
    public static final ImageReductionInterface IMAGE_REDUCTION = new ImageReduction();
    public static final OptimisedAestheticClusteringInterface OPTIMISED_AESTHETIC_CLUSTERING = new OptimisedAestheticClustering();
    public static final ImageAestheticClusteringInterface IMAGE_AESTHETIC_CLUSTER = new ImageAestheticClustering();
    public static final BlursInterface BLURS = new Blurs();

    //HELPER CLASSES
    public static final NumberOfColorCalculatorInterface NUMBER_CALCULATOR = new NumberOfColorCalculator();
    public static final Scanner USER_INPUT_SCANNER = new Scanner(System.in);
    public static ImageTransformationHelperInterface IMAGE_TRANSFORMATION_HELPER = new ImageTransformationHelper();
    public static PixelCacheInterface PIXEL_CACHE = new PixelCache();
    public static ComparatorInterface COMPARATOR = null;


    //INPUT OPTIONS
    public static String OPERATION_ORDER = "";
    public static boolean DEBUG_MODE = false;
    public static boolean RESULT_FOLDER = false;
    public static boolean LOGGER_MODE = true;
    public static boolean DO_REDUCED_INPUT = false;
    public static boolean ARGS_INPUT = false;

    //PATHNAMES
    public static String PATHNAME_ORIGIN = "";
    public static final String RESULT_SUFFIX = "Result";
    public static final String RESULT_FILE_TYPE = ".png";

    //EDITIING OPTIONS
    public static final String DEFAULT_EDITING_OPTION = "BRC";
    public static final String ALL_EDITING_STRING = "ALL";
    public static final String ALL_EDITING_OPTIONS = "RCB"; //equal to the chars below
    public static final char REDUCTION_CHAR = 'R';
    public static final char BLUR_CHAR = 'B';
    public static final char CLUSTER_CHAR = 'C';
    public static final char DIFFERENCE_CHAR = 'D';
    //STORING
    public static boolean STORE_DIFFERENCES = false;
    //REDUCTION
    public static boolean DO_BLACK_AND_WHITE = false;
    public static int NUMBER_OF_COLORS = 0;
    public static boolean KEEP_SAME_TRANSPARENCY_MODE = false;

    //AESTHETIC CLUSTERING
    public static int AESTHETIC_CLUSTERING_SIZE = 2;
    public static int MAX_NUMBER_OF_AESTHETIC_CLUSTERS = 128;

    //BLUR
    public static int BLUR_SIZE = 3;

}
