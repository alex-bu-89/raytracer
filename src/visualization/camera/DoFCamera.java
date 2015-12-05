package visualization.camera;

import Sampling.SamplingPattern;
import bibliothek.Point2;
import bibliothek.Point3;
import bibliothek.Vector3;
import java.util.ArrayList;
import visualization.Ray;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class DoFCamera extends Camera {

    /**
     * angle for the Camera
     */
    final private double angle;

    /**
     * focalLength
     */
    final private double focalLength;

    /**
     * radius
     */
    final private double lensRadius;

    public DoFCamera(Point3 e, Vector3 g, Vector3 t, double angle, double focalLength, double lensRadius, SamplingPattern sp) {
        super(e, g, t, sp);
        this.angle = angle;
        this.focalLength = focalLength;
        this.lensRadius = lensRadius;
    }

    @Override
    public ArrayList<Ray> rayFor(int width, int height, int x, int y) {
        ArrayList<Ray> rays = new ArrayList();
        Vector3 mw = this.w.mul(-1);
        double one = height/2/Math.tan(angle/2);
        double two = (width-1)/2;
        double three = (height -1)/2;
        for(Point2 p : sp.pointList){
            double px = x - two + p.x;
            double py = y-three + p.y;
            
            double fx = px * focalLength/one;
            double fy = py * focalLength/one;
            
            Point3 fp = this.e.add(mw.mul(focalLength)).add(this.u.mul(fx).add(this.v.mul(fy)));
            for(Point2 lp : sp.asDisk()){
                Point3 lo = this.e.add(this.u.mul(lp.x * lensRadius)).add(this.v.mul(lp.y * lensRadius));
                Vector3 d = fp.sub(lo).normalized();
                rays.add(new Ray(lo, d));
            }
        }
        return rays;
    }

}
