package texture;

import visualization.Color;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public interface Texture {
    
    public Color getColor(final double u, final double v);
    
}
