package Material;

import visualization.Color;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Inheritors calculate the color of a material
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public abstract class Material {
    
    public abstract Color colorFor(final Hit hit, final World world, Tracer tracer);
    
}
