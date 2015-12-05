package visualization.geometry;

import Material.Material;
import bibliothek.Mat3x3;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Vector3;
import texture.TextCoord2;
import visualization.Ray;

/**
 * Represents a triangle object in ray tracer
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Triangle extends Geometry{

    /**
     * first point of triangle
     */
    private final Point3 a;

    /**
     * second point of triangle
     */
    private final Point3 b;

    /**
     * third point of triangle
     */
    private final Point3 c;

    /**
     * normal to triangle plane
     */
    private Normal3 normal1;

    /**
     * normal to triangle plane
     */
    private Normal3 normal2;

    /**
     * normal to triangle plane
     */
    private Normal3 normal3;
    
    private final TextCoord2 at;
    
    private final TextCoord2 bt;
    
    private final TextCoord2 ct;

    public Triangle(final Point3 a, final Point3 b , final Point3 c, final Material material){
        super(material);
        this.a = a;
        this.b = b;
        this.c = c;
        normal1 = b.sub(a).x(c.sub(a)).asNormal();
        /*normal2 = c.sub(a).x(b.sub(a)).asNormal();
        normal3 = normal1.mul(-1);*/
        this.at = new TextCoord2(0,0);
        this.bt = new TextCoord2(0,0);
        this.ct = new TextCoord2(0,0);
    }

    /**
     * returns a hit object if ray goes through triangle
     * r - ray
     * return hit
     */
    @Override
    public Hit hit(final Ray r) {
        Mat3x3 mat = new Mat3x3(a.x - b.x, a.x - c.x, r.d.x,
                                a.y - b.y, a.y - c.y, r.d.y,
                                a.z - b.z, a.z - c.z, r.d.z);
        Vector3 vec = a.sub(r.o);
        if (mat.determinant == 0) {
            return null;
        }
        final double beta = mat.changeCol1(vec).determinant / mat.determinant;
        final double gamma = mat.changeCol2(vec).determinant / mat.determinant;
        final double t = mat.changeCol3(vec).determinant / mat.determinant;
        final double alpha = 1.0 - beta - gamma;

        final Normal3 normal = normal1.mul(alpha).add(normal1.mul(beta)).add(normal1.mul(gamma));

        if (beta < 0.0 || gamma < 0.0 || beta + gamma > 1.0 || t < Geometry.EPSILON ) {
            return null;
        } else {
            final TextCoord2 tc = at.mul(alpha).add(bt).mul(beta).add(ct).mul(gamma);
            return new Hit(t, r, this, normal, tc);
            //return new Hit(t, r, this, normal1.mul(alpha).add(normal1).mul(beta).add(normal1).mul(gamma).mul(-1));
        }
    }

}
