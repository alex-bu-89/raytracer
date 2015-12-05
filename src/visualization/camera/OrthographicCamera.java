package visualization.camera;

import Sampling.SamplingPattern;
import bibliothek.Point2;
import bibliothek.Point3;
import bibliothek.Vector3;
import java.util.ArrayList;
import visualization.Ray;

public class OrthographicCamera extends Camera {
    /**
     * s scale factor of the Orthographic Camera
     */
    public final double s;

    public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s, SamplingPattern pattern){
        super(e, g, t, pattern);
        this.s = s;
    }
    /**
     * Defines a Ray for a given Point x, y on a Canvas
     * @param w Width of the Image
     * @param h Height of the Image
     * @param x Coordinate x of the Point
     * @param y Coordinate y of the Point
     * @return Ray for the Point
     */
    @Override
    public ArrayList<Ray> rayFor(int w, int h, int x, int y) {
        ArrayList<Ray> set = new ArrayList<Ray>();
        double a = (double) w / h;
        double rec1 = (double) (x - (w - 1) / 2) / (w - 1) * a * s;
        double rec2 = (double) (y - (h - 1) / 2) / (h - 1) * s;
        Vector3 d = this.w.mul(-1);
        for(Point2 point : this.sp.pointList){
            Point3 o = e.add(u.mul(rec1)).add(v.mul(rec2)).add(u.mul(point.x * a * s/w).add(v.mul(a * s/w)));
            Ray r = new Ray(o , d);
            set.add(r);
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrthographicCamera that = (OrthographicCamera) o;

        if (Double.compare(that.s, s) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(s);
        return (int) (temp ^ (temp >>> 32));
    }
}
