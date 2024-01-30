package UserAndFileHandler;

public interface UserInputHandlerInterface {
    String getImagePathname();
    String generatePathname(String suffix);
    String generatePathname(String suffix, String folderName);

    int getIntUserInput(String message);
    String getNextLine (String message);

    boolean getBooleanUserInput(String message);

    void processArgs(String[] args);
}
