package Material;

import bibliothek.Normal3;
import bibliothek.Vector3;
import visualization.Color;
import visualization.Ray;
import visualization.World;
import visualization.geometry.Geometry;
import visualization.geometry.Hit;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class TransparentMaterial extends Material {

    /**
     * the index of refraction
     */
    public double refractionIndex;

    /**
     * instanciates a transparent material
     *
     * @param refractionIndex the index of refraction
     */
    public TransparentMaterial(double refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    /**
     * this method returns the color for one Hit-Object
     *
     * @param hit the hit with an object
     * @param world the world is needed to find the lights
     * @param tracer function for raytracing
     * @return color for hit
     */
    public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
        Normal3 normal = hit.normal;
        // rd reflected vector
        final Vector3 rd = hit.ray.d.mul(-1).normalized().reflectedOn(normal).normalized();
        // rate of refraction
        double Nqout;
        if (rd.dot(normal) < 0) {
            Nqout = refractionIndex / world.refractionIndex;
            normal = normal.mul(-1);
        } else {
            Nqout = world.refractionIndex / refractionIndex;
        }
        final double alpha = normal.dot(rd);
        if ((1 - (Math.pow(Nqout, 2.0) * (1 - Math.pow(alpha, 2.0)))) < 0) {
            return tracer.colorFor(new Ray(hit.ray.at(hit.t - Geometry.EPSILON), rd));
        } else {
            final double beta = Math.sqrt(1 - (Math.pow(Nqout, 2.0) * (1 - Math.pow(alpha, 2.0))));
            final Vector3 t = hit.ray.d.mul(Nqout).sub(normal.mul(beta - Nqout * alpha));
            // Schlick's approximation
            final double R0 = Math.pow((world.refractionIndex - refractionIndex) / (world.refractionIndex + refractionIndex), 2);
            // R the rate of reflection
            final double R = R0 + (1 - R0) * Math.pow(1 - alpha, 5);
            // T the rate of transmission
            final double T = 1 - R;
            // R and T are multiplied with the colors of the raytraced rays
            return tracer.colorFor(new Ray(hit.ray.at(hit.t - Geometry.EPSILON), rd)).mul(R)
                    .add(tracer.colorFor(new Ray(hit.ray.at(hit.t + Geometry.EPSILON), t)).mul(T));
        }
    }

}
