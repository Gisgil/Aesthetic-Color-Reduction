package UserAndFileHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static Constants.Constants.*;
import static Constants.ANSIConstants.*;

public class Filehandler implements FileHandlerInterface {
    @Override
    public BufferedImage getImageFromPathname() {
        if (LOGGER_MODE) {
            System.out.println("Reading Image");
        }
        // For storing image in RAM
        BufferedImage image = null;
        try {
            File input_file = new File(PATHNAME_ORIGIN);
            image = ImageIO.read(input_file);

        } catch (Exception e) {
            System.out.println(ANSI_ERROR + "ERROR when reading from pathname " + PATHNAME_ORIGIN + " " + e + ANSI_RESET);
            System.exit(-1);
        }

        if (LOGGER_MODE) {
            System.out.println("Reading of Image successful");
        }
        return image;
    }

    @Override
    public void setImageFromPathname(BufferedImage image, String pathname) {
        if (LOGGER_MODE) {
            System.out.println("Writing Image");
        }
        try {
            File output_file = new File(pathname);
            ImageIO.write(image, "png", output_file);
        } catch (Exception e) {
            System.out.println(ANSI_ERROR + "ERROR when writing on pathname: " + pathname + " " + e + ANSI_RESET);
        }
        if (LOGGER_MODE) {
            System.out.println("Writing of Image successful\n");
        }
    }
}
