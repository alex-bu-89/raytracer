package Lighting;

import bibliothek.Point3;
import bibliothek.Vector3;
import visualization.Color;
import visualization.Ray;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Represents a spot light
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class SpotLight extends Light{

    /**
     * Position of the light source
     */
    private final Point3 position;

    /**
     * Direction of the light source
     */
    private final Vector3 direction;

    /**
     * Angle of the light source
     */
    private final double halfAngle;

    public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle, final boolean castShadow) {
        super(color, castShadow);
        this.position = position;
        this.direction = direction;
        this.halfAngle = halfAngle;
    }
   
    @Override
    public boolean illuminate(final Point3 point, final World world ) {
        if (!castShadow) {
            return true;
        }
        final Vector3 from = directionFrom(point);
        // calculates if point lies within the angle
        if (Math.acos(direction.normalized().dot(from.mul(-1))) > halfAngle){
            return false;
        }
        final Ray ray = new Ray(point, from);
        final Hit hit = world.hit(ray);
        // checks if a hit is between light and point -> shadow
        return hit == null || hit.t >= ray.tOf(position);
    }
    /**
     * returns the vector pointing to the light source
     * @param point
     * @return 
     */
    @Override
    public Vector3 directionFrom(final Point3 point) {
        return position.sub(point).normalized();
    }

    @Override
    public String toString() {
        return "SpotLight{" +
                "position=" + position +
                ", direction=" + direction +
                ", halfAngle=" + halfAngle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotLight spotLight = (SpotLight) o;

        if (Double.compare(spotLight.halfAngle, halfAngle) != 0) return false;
        if (direction != null ? !direction.equals(spotLight.direction) : spotLight.direction != null) return false;
        if (position != null ? !position.equals(spotLight.position) : spotLight.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = position != null ? position.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        temp = Double.doubleToLongBits(halfAngle);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
