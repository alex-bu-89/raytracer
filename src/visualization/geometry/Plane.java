package visualization.geometry;

import Material.Material;
import bibliothek.Normal3;
import bibliothek.Point3;
import texture.TextCoord2;
import visualization.Ray;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Plane extends Geometry {

    public Point3 a;
    public Normal3 n;

    public Plane(final Material material) {
        super(material);
        this.a = new Point3(0,0,0);
        this.n = new Normal3(0,1,0);
    }

    /**
     * Creates a plane.
     * @param a - Point on the Plane
     * @param n - Normal of the Plane, vertical Vector
     */
    public Plane(final Point3 a, final Normal3 n, final Material material) {
        this(material);
        this.a = a;
        this.n = n;
    }

    /**
     * Calculates the smallest point of intersection between a given Ray and the Plane
     * @param ray
     * @return Point of Intersection defined with Ray and t, and the Geometry Object.
     */
    @Override
    public Hit hit(Ray ray) {
        double h = ray.d.dot(this.n);
        if(h == 0.0) {
           return null;
        }
        double t = (this.a.sub(ray.o)).dot(this.n) / h;
        if(t < Geometry.EPSILON){
            return null;
        }

        return new Hit(t, ray , this, n, new TextCoord2(ray.at(t).x, -(ray.at(t).z)));

    }

    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", n=" + n +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (a != null ? !a.equals(plane.a) : plane.a != null) return false;
        if (n != null ? !n.equals(plane.n) : plane.n != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (n != null ? n.hashCode() : 0);
        return result;
    }
}
