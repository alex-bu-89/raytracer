package visualization.camera;


import Sampling.SamplingPattern;
import bibliothek.Point3;
import bibliothek.Vector3;
import java.util.ArrayList;
import visualization.Ray;

public abstract class Camera {
    /**
     * e 
     */
    public final Point3 e;
    public final Vector3 g;
    public final Vector3 t;
    public final Vector3 u;
    public final Vector3 v;
    public final Vector3 w;
    public final SamplingPattern sp;

    public Camera(final Point3 e, final Vector3 g, final Vector3 t, SamplingPattern sp) {
        this.e = e;
        this.g = g;
        this.t = t;
        this.w = g.normalized().mul(- 1 );
        this.u = t.x(w).normalized();
        this.v = w.x(u) ;
        this.sp = sp;
    }

    public abstract ArrayList<Ray> rayFor(int w, int h, int x, int y);
}

