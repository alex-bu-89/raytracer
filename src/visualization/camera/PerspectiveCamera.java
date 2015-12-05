package visualization.camera;

import Sampling.SamplingPattern;
import bibliothek.Point2;
import bibliothek.Point3;
import bibliothek.Vector3;
import java.util.ArrayList;
import visualization.Ray;

public class PerspectiveCamera extends Camera {
    /**
     * angle for the Perspective Camera
     */
    final double angle;
    
    public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, double angle, SamplingPattern sp) {
        super(e , g , t, sp);
        this.angle = angle;
    }
    /**
     * Defines a Ray for a given Point x, y on a Canvas
     * @param width Width of the Image
     * @param height Height of the Image
     * @param x Coordinate x of the Point
     * @param y Coordinate y of the Point
     * @return Ray for the Point
     */
    @Override
    public ArrayList<Ray> rayFor(int width, int height, int x, int y) {
        ArrayList<Ray> rays = new ArrayList();
        for(Point2 p : sp.pointList){
            Vector3 r = this.w.mul(-1).mul((height/2)/ Math.tan(angle/2)).add(this.u.mul(x - (width-1)/2)).add(this.v.mul(y - (height-1)/2)).add(this.u.mul(p.x).add(this.v.mul(p.y)));
            Vector3 d = r.mul(1 / r.magnitude);
            rays.add(new Ray(this.e, r.normalized()));
        }
        return rays;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerspectiveCamera that = (PerspectiveCamera) o;

        if (Double.compare(that.angle, angle) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(angle);
        return (int) (temp ^ (temp >>> 32));
    }
}
