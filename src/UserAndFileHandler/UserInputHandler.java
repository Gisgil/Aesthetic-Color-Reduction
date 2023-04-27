package UserAndFileHandler;

import Constants.ANSIConstants;

import java.io.File;

import static Constants.Constants.*;
import static Constants.UserMessagesConstants.*;

public class UserInputHandler implements UserInputHandlerInterface {
    @Override
    public String getImagePathname() {
        return getStringUserInput(GET_IMAGE_PATHNAME_MESSAGE);
    }

    @Override
    public String generatePathname(String suffix) {
        return generatePathname(suffix, "/Result/");
    }

    @Override
    public String generatePathname(String suffix, String folderName) {
        folderName = "/Result" + folderName + "/";

        String pathname = PATHNAME_ORIGIN;
        String fileName;
        int index;
        if (OPERATION_ORDER.length() != 0) {
            index = findLastChar(pathname, '/');
            indexCheck(index);
            fileName = pathname.substring(index + 1);
            pathname = pathname.substring(0, index) + folderName;
            createResultFolder(pathname);
            pathname += fileName;
        }


        index = findLastChar(pathname, '.');
        pathname = pathname.substring(0, index);
        pathname += suffix + RESULT_FILE_TYPE;
        if (DEBUG_MODE) {
            System.out.println(ANSIConstants.ANSI_HIGHLIGHT_COLOR + pathname + ANSIConstants.ANSI_RESET);
        }
        return pathname;
    }

    private void indexCheck(int index) {
        if (index < 0) {
            System.out.println(ANSIConstants.ANSI_ERROR + "Unable to create Result file pathname" + ANSIConstants.ANSI_RESET);
            System.exit(-1);
        }
    }

    private void createResultFolder(String pathname) {
        File resultDir = new File(pathname);
        if (!resultDir.exists()) {
            resultDir.mkdirs();
        }
    }

    private int findLastChar(String pathname, char toFind) {
        char[] arr = pathname.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == toFind) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getIntUserInput(String message) {
        System.out.println(message);
        boolean a = true;
        int x = 0;
        while (a) {
            try {
                x = USER_INPUT_SCANNER.nextInt();
                a = false;
            } catch (Exception e) {
                System.out.println(WRONG_INPUT_MESSAGE);
            }
        }
        return x;
    }

    @Override
    public boolean getBooleanUserInput(String message) {
        String x = null;

        while (x == null || x.length() == 0) {
            x = getStringUserInput(message);
        }
        char c = x.toCharArray()[0];
        return c == 'y';
    }

    @Override
    public String getStringUserInput(String message) {
        System.out.println(message);
        boolean a = true;
        String x = null;
        while (a) {
            try {
                x = USER_INPUT_SCANNER.nextLine();
                a = false;
            } catch (Exception e) {
                System.out.println(WRONG_INPUT_MESSAGE);
            }
        }
        return x;
    }

    @Override
    public void processArgs(String[] args) {
        int firstSlash = -1;
        int lastSlash = -1;
        boolean toMakeFirst = true;

        String argument;
        for (int i = 0; i < args.length; i++) {
            argument = args[i];
            if (argument.contains("/")) {
                if (toMakeFirst) {
                    toMakeFirst = false;
                    firstSlash = i;
                }
                lastSlash = i;
            }
        }

        StringBuilder pathnameBuilder = new StringBuilder();
        StringBuilder operationBuilder = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            argument = args[i];

            if (i >= firstSlash && i <= lastSlash) {
                pathnameBuilder.append(" ").append(args[i]);
            } else if (argument.charAt(0) == '-') {
                switchStatementForArgs(argument.toLowerCase());
            } else {
                operationBuilder.append(argument);
            }
        }

        PATHNAME_ORIGIN = pathnameBuilder.toString().strip();
        if (!PATHNAME_ORIGIN.equals("")) ARGS_INPUT = true;

        OPERATION_ORDER = operationBuilder.toString();
    }

    private void switchStatementForArgs(String argument) {
        switch (argument) {
            case "-help", "-h" -> {
                System.out.println(HELP_MESSAGE);
                System.exit(0);
            }
            case "-reduced-input", "-r", "-reduced", "-ri", "reduced_input" -> DO_EXPANDED_INPUT = true;
            case "-debug_mode", "-debug-mode", "-debugmode", "-debug", "-d" -> DEBUG_MODE = true;
            case "-black-and-white", "-blackandwhite", "black&white", "-baw", "-bw" -> DO_BLACK_AND_WHITE = true;
        }
    }
}
