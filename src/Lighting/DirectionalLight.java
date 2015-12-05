package Lighting;

import bibliothek.Point3;
import bibliothek.Vector3;
import visualization.Color;
import visualization.Ray;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Represents a directional light
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class DirectionalLight extends Light {

    /**
     * direction of directional light
     */
    private final Vector3 direction;

    public DirectionalLight(final Color color, final Vector3 direction, final boolean castShadow) {
        super(color, castShadow);
        this.direction = direction;
    }
    
    /***
     * checks if given point ist illuminated
     * @param point
     * @param world
     * @return 
     */

    @Override
    public boolean illuminate(final Point3 point, final World world) {
        if (!castShadow) {
            return true;
        }
        //Ray from point to light
        Ray r = new Ray(point, directionFrom(point));
        Hit hit = world.hit(r);
        if (hit == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a vector for the given point, that shows the light source
     *
     * @param point
     * @return vector
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        return direction.mul(-1).normalized();
    }

    @Override
    public String toString() {
        return "DirectionalLight{"
                + "direction=" + direction
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DirectionalLight that = (DirectionalLight) o;

        if (direction != null ? !direction.equals(that.direction) : that.direction != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return direction != null ? direction.hashCode() : 0;
    }
}
