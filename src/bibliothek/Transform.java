package bibliothek;

import visualization.Ray;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Transform {

    /**
     * transformation matrix
     */
    public final Mat4x4 m;

    /**
     * inverse matrix
     */
    public final Mat4x4 i;


    public Transform(){

        m = new Mat4x4( 1.0, 0.0, 0.0, 0.0,
                        0.0, 1.0, 0.0, 0.0,
                        0.0, 0.0, 1.0, 0.0,
                        0.0, 0.0, 0.0, 1.0
        );

        i = new Mat4x4( 1.0, 0.0, 0.0, 0.0,
                        0.0, 1.0, 0.0, 0.0,
                        0.0, 0.0, 1.0, 0.0,
                        0.0, 0.0, 0.0, 1.0
        );

    }

    private Transform(final Mat4x4 m, final Mat4x4 i){
        this.m = m;
        this.i = i;
    }

    /**
     * translate object
     * @param x - transformation along the x axis
     * @param y - transformation along the y axis
     * @param z - transformation along the z axis
     * @return - new transform object
     */
    public Transform translate(final double x, final double y, final double z) {

        final Mat4x4 tm = new Mat4x4(   1.0, 0.0, 0.0, x,
                                        0.0, 1.0, 0.0, y,
                                        0.0, 0.0, 1.0, z,
                                        0.0, 0.0, 0.0, 1.0
        );

        final Mat4x4 ti = new Mat4x4(   1.0, 0.0, 0.0, -x,
                                        0.0, 1.0, 0.0, -y,
                                        0.0, 0.0, 1.0, -z,
                                        0.0, 0.0, 0.0,  1.0
        );

        return new Transform(m.mul(tm), ti.mul(i));

    }

    /**
     * translate object
     * @param point - the point to transform
     * @return - new transform object
     */
    public Transform translate(final Point3 point) {
        return translate(point.x, point.y, point.z);
    }

    public Transform scale(final double x, final double y, final double z) {

        final Mat4x4 tm = new Mat4x4(   x, 0.0, 0.0, 0.0,
                                        0.0, y, 0.0, 0.0,
                                        0.0, 0.0, z, 0.0,
                                        0.0, 0.0, 0.0, 1.0
        );

        final Mat4x4 ti = new Mat4x4(   1.0/x, 0.0, 0.0, 0.0,
                                        0.0, 1.0/y, 0.0, 0.0,
                                        0.0, 0.0, 1.0/z, 0.0,
                                        0.0, 0.0, 0.0, 1.0
        );

        return new Transform(m.mul(tm), ti.mul(i));

    }

    /**
     * rotate object along x axis
     * @param angle
     * @return - new transform object
     */
    public Transform rotateX(final double angle) {
        
        final Mat4x4 tm = new Mat4x4(   1.0,   0.0,             0.0,            0.0,
                                        0.0, Math.cos(angle), -Math.sin(angle), 0.0,
                                        0.0, Math.sin(angle), Math.cos(angle),  0.0,
                                        0.0, 0.0,             0.0,              1.0
        );
        
        final Mat4x4 ti = new Mat4x4(   1.0, 0.0,              0.0,             0.0,
                                        0.0, Math.cos(angle),  Math.sin(angle), 0.0,
                                        0.0, -Math.sin(angle), Math.cos(angle), 0.0,
                                        0.0, 0.0,              0.0,             1.0);

        return new Transform(m.mul(tm), ti.mul(i));

    }

    /**
     * rotate object along y axis
     * @param angle
     * @return - new transform object
     */
    public Transform rotateY(final double angle) {

        final Mat4x4 tm = new Mat4x4(   Math.cos(angle), 0.0, -Math.sin(angle), 0.0,
                                        0.0, 		     1.0, 0.0,              0.0,
                                        Math.sin(angle), 0.0, Math.cos(angle),  0.0,
                                        0.0,             0.0, 0.0,              1.0
        );

        final Mat4x4 ti = new Mat4x4(   Math.cos(angle),  0.0, Math.sin(angle), 0.0,
                                        0.0,     		  1.0, 0.0,             0.0,
                                        -Math.sin(angle), 0.0, Math.cos(angle), 0.0,
                                        0.0,              0.0, 0.0,             1.0
        );

        return new Transform(m.mul(tm), ti.mul(i));

    }

    /**
     * rotate object along z axis
     * @param angle
     * @return - new transform object
     */
    public Transform rotateZ(final double angle) {

        final Mat4x4 tm = new Mat4x4(   Math.cos(angle), -Math.sin(angle), 0.0, 0.0,
                                        Math.sin(angle),  Math.cos(angle), 0.0, 0.0,
                                        0.0, 		      0.0,             1.0, 0.0,
                                        0.0,              0.0,             0.0, 1.0);

        final Mat4x4 ti = new Mat4x4(   Math.cos(angle),  Math.sin(angle), 0.0, 0.0,
                                        -Math.sin(angle), Math.cos(angle), 0.0, 0.0,
                                        0.0, 			  0.0,             1.0, 0.0,
                                        0.0,              0.0,             0.0, 1.0);

        return new Transform(m.mul(tm), ti.mul(i));

    }

    /**
     * transforms a ray
     * @param ray
     * @return new Ray
     */
    public Ray mul(final Ray ray) {
        return new Ray(i.mul(ray.o), i.mul(ray.d));
    }

    /**
     * ransforms a normal
     * @param normal
     * @return new Normal3
     */
    public Normal3 mul(final Normal3 normal) {
        return (i.transposed().mul(new Vector3(normal.x, normal.y, normal.z))).normalized().asNormal();
    }

    @Override
    public String toString() {
        return "Transform{" +
                "m=" + m +
                ", i=" + i +
                '}';
    }
}
