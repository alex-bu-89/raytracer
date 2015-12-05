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
public class InterpolatedImageTexture implements Texture{

    /**
     * the path to the folder containning source
     */
    public static final String baseURL = "texture/";
    /**
     * the color used for the texture
     */
    public final Color color;
    /**
     * the texture image
     */
    public BufferedImage image;

    /**
     * instanciates an image texture
     *
     * @param color the color for the texture
     * @param pathToImage the name of the texture image
     */
    public InterpolatedImageTexture(final Color color, final String pathToImage) {
        this.color = color;
        this.image = null;
        try {
            this.image = ImageIO.read(new File(baseURL + pathToImage));
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
        final double xInterpolated = x - Math.floor(x);
        final double yInterpolated = y - Math.floor(y);
        final Color colorFF = ImageTexture.getColorOfPosition(this.image, (int) Math.floor(x), (int) Math.floor(y));
        final Color colorFC = ImageTexture.getColorOfPosition(this.image, (int) Math.ceil(x), (int) Math.floor(y));
        final Color colorCF = ImageTexture.getColorOfPosition(this.image, (int) Math.floor(x), (int) Math.ceil(y));
        final Color colorCC = ImageTexture.getColorOfPosition(this.image, (int) Math.ceil(x), (int) Math.ceil(y));
        final Color colorFFFC = colorFF.mul(1.0 - xInterpolated).add(colorFC.mul(xInterpolated));
        final Color colorCFCC = colorCF.mul(1.0 - xInterpolated).add(colorCC.mul(xInterpolated));
        return colorFFFC.mul(1.0 - yInterpolated).add(colorCFCC.mul(yInterpolated));
    }

    @Override
    public String toString() {
        return "SingleColorTexture{"
                + "color=" + color
                + '}';
    }

}
