package Lighting;

import bibliothek.Point3;
import bibliothek.Vector3;
import visualization.Color;
import visualization.World;

/**
 * Abstract class. Represents a light
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public abstract class Light {

    /**
     * The color of the light
     */
    public final Color color;

    /**
     * determines if light casts shadow
     */
    public final boolean castShadow;

    
    public Light(final Color color, final boolean castShadow){
        this.color = color;
        this.castShadow = castShadow;
    }
    
    public abstract boolean illuminate(final Point3 point, final World world);

    /**
     * Returns a vector for the given point, that shows the light source
     * @param point
     * @return vector
     */
    public abstract Vector3 directionFrom(final Point3 point);
    
}
