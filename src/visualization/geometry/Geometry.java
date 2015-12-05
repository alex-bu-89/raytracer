package visualization.geometry;

import Material.Material;
import visualization.Ray;
import visualization.Color;

/**
 * Abstract class of a geometry objects
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public abstract class Geometry{

    /**
     * Color color defines the color of the Geometry-Object
     */
    public final Material material;
    
    public final static double EPSILON = 0.0001;

    public Geometry(final Material material) {
        this.material = material;
    }

    /**
     * Defined abstract Method hit() for all Geometry-Objects.
     * @param ray
     * @return 
     */
    public abstract Hit hit(final Ray ray);

}
