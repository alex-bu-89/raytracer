package visualization;

import bibliothek.Point3;
import bibliothek.Vector3;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Ray {
    /**
     * Point o Point of Origin of the Ray
     */
    public final Point3 o;
    /**
     * Vector d Direction Vector of the Ray
     */
    public final Vector3 d;

    public Ray(Point3 o, Vector3 d) {
        this.o = o;
        this.d = d;
    }
    /**
     * Calculates the Point on the Ray at the distance of t * the Direction Vector d.
     * @param t
     * @return Point at t * Vector d
     */
    public Point3 at(double t){
        Point3 point = this.o.add(d.mul(t));
        return point;
    }
    /**
     * Calculates the distance(multiple of d) t of the Point on the Ray.
     * @param p
     * @return t :multiple of Vector d
     */
    public double tOf(Point3 p){
        return p.sub(o).magnitude / d.magnitude;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "o=" + o +
                ", d=" + d +
                '}';
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;

        Ray ray = (Ray) o1;

        if (d != null ? !d.equals(ray.d) : ray.d != null) return false;
        if (o != null ? !o.equals(ray.o) : ray.o != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = o != null ? o.hashCode() : 0;
        result = 31 * result + (d != null ? d.hashCode() : 0);
        return result;
    }

}

