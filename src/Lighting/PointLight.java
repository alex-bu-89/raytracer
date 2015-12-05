package Lighting;

import bibliothek.Point3;
import bibliothek.Vector3;
import visualization.Color;
import visualization.Ray;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Represents a  point light
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class PointLight extends Light{
    /*
    position of the PointLight
    */
    private final Point3 position;

    public PointLight(final Color color, final Point3 position, final boolean castShadow) {
        super(color, castShadow);
        this.position = position;
    }
    /***
     * checks if point is illuminated
     * @param point
     * @param world
     * @return 
     */

    @Override
    public boolean illuminate(final Point3 point, final World world) {
        if (!castShadow) {
            return true;
        }
        final Ray ray = new Ray(point, directionFrom(point));
        final Hit hit = world.hit(ray);

        return hit == null || hit.t > ray.tOf(position);
    }

    /**
     * Returns a vector for the given point, that points at the light source
     * @param point
     * @return vector
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        final Vector3 vector = position.sub(point).normalized();
        return vector;
    }

    @Override
    public String toString() {
        return "PointLight{" +
                "position=" + position +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointLight that = (PointLight) o;

        if (position != null ? !position.equals(that.position) : that.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return position != null ? position.hashCode() : 0;
    }
}
