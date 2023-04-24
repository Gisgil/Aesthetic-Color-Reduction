package UserAndFileHandler;

import java.awt.image.BufferedImage;

public interface FileHandlerInterface {

     //Uses constant DEFAULT_PATHNAME_ORIGIN as pathname
     BufferedImage getImageFromPathname();

     //Can be used for differing constants
     void setImageFromPathname(BufferedImage image, String pathname);
}
