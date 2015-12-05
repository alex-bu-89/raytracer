package visualization.geometry;

import Material.Material;
import bibliothek.Point3;
import bibliothek.Vector3;
import texture.TextCoord2;
import visualization.Ray;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Sphere extends Geometry {

    /**
     * Point c center of the Sphere.
     */
    public Point3 c = new Point3(0, 0, 0);

    /**
     * r radius of the Sphere
     */
    public double r = 1;

    public Sphere(final Material material) {
        super(material);
    }

    public Sphere(Point3 c, double r, final Material material) {
        this(material);
        this.c = c;
        this.r = r;
    }

    /**
     * Calculates the Intersection Point between the Sphere and a given Ray.
     *
     * @param ray
     * @return
     */
    public Hit hit(final Ray ray) {
        double a = ray.d.dot(ray.d);
        double b = ray.d.dot(ray.o.sub(this.c).mul(2));
        double c = (ray.o.sub(this.c)).dot(ray.o.sub(this.c)) - Math.pow(this.r, 2);
        double d = Math.pow(b, 2) - 4 * a * c;

        if (d < 0.0) {
            return null;
            // } else if(d == 0.0){
            // double t = -b / (2 * a);
            //return new Hit(t, ray, this, ray.at(t).sub(this.c).normalized().asNormal());
        } else {
            double t1 = (-b + Math.sqrt(d)) / (2 * a);
            double t2 = (-b - Math.sqrt(d)) / (2 * a);
            double t = Geometry.EPSILON;

            //calculate the minimum positive t
            if (t1 < 0 && t2 < 0 && t2 < Geometry.EPSILON) {
                return null;
            }
            if (t1 > 0 && t2 >= 0 && t2 >= Geometry.EPSILON) {
                t = Math.min(t1, t2);
            }
            if (t2 > 0 && t1 < 0 && t1 < Geometry.EPSILON) {
                t = t2;
            }
            if (t2 < 0 && t1 > 0 && t1 > Geometry.EPSILON) {
                t = t1;
            }
            if (t > Geometry.EPSILON) {
                return new Hit(t, ray, this, ray.at(t).sub(this.c).normalized().asNormal(), textFor(ray.at(t)));
            }
            return null;
        }
    }

    public TextCoord2 textFor(final Point3 point) {
        Vector3 d = point.sub(c);
        double theta = Math.acos(d.y);
        double phi = Math.atan2(d.x, d.z);
        return new TextCoord2(phi / (Math.PI * 2), -(theta / Math.PI));
    }

    @Override
    public String toString() {
        return "Sphere{"
                + "c=" + c
                + ", r=" + r
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

        Sphere sphere = (Sphere) o;

        if (Double.compare(sphere.r, r) != 0) {
            return false;
        }
        if (c != null ? !c.equals(sphere.c) : sphere.c != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = c != null ? c.hashCode() : 0;
        temp = Double.doubleToLongBits(r);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

}
