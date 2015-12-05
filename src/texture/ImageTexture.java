package texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import visualization.Color;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class ImageTexture implements Texture{
    
    public static final String path = "texture/";
    /**
     * the color used for the texture
     */
    public final Color color;
    /**
     * the image used for the texture
     */
    public BufferedImage image;

    /**
     * instanciates an image texture
     *
     * @param color the color for the texture
     * @param pathToImage the image path used for the texture
     */
    public ImageTexture(final Color color, final String pathToImage) {
        this.color = color;
        this.image = null;
        try {
            this.image = ImageIO.read(new File(path + pathToImage));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * method for finding the right Color at position
     *
     * @param u position mapped to texture
     * @param v position mapped to texture
     * @return the Color for the current u, v
     */
    @Override
    public Color getColor(final double u, final double v) {
        double uCoord = u % 1.0;
        double vCoord = v % 1.0;
        if(uCoord < 0.0){
            uCoord += 1.0;
        }
        if(vCoord < 0.0 ){
            vCoord += 1.0;
        }
        final double x = (this.image.getWidth() - 1) * uCoord;
        final double y = (this.image.getHeight() - 1) - ((image.getHeight() - 1) * vCoord);
        return ImageTexture.getColorOfPosition(this.image, (int) Math.round(x), (int) Math.round(y));
    }

    public static Color getColorOfPosition(final BufferedImage image, final int x, final int y) {
        final java.awt.Color c = new java.awt.Color(image.getRGB(x, y));
        return new Color(c);
    }

}
