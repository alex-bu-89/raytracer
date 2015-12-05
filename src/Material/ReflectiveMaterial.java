package Material;

import Lighting.Light;
import bibliothek.Point3;
import bibliothek.Vector3;
import texture.Texture;
import visualization.Color;
import visualization.Ray;
import visualization.World;
import visualization.geometry.Hit;


 /**
  *
  * @author Alexander Buyanov (806984)
  * @author Phillip Redlich (791806)
  */
public class ReflectiveMaterial extends Material {

    /**
     * the diffusing color
     */
    public final Texture diffuse;
    /**
     * the specular color
     */
    public final Texture specular;
    /**
     * the exponent
     */
    public final int exponent;
    /**
     * the reflection color
     */
    public final Texture reflection;

    public ReflectiveMaterial(Texture diffuse, Texture specular, int exponent, Texture reflection) {
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
        this.reflection = reflection;
    }

    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        final Point3 hitPoint = hit.ray.at(hit.t);
        final double cosinusPhi = hit.normal.dot(hit.ray.d.mul(-1.0)) * 2;
        final Vector3 v = hit.ray.d.mul(-1).normalized();
        Color c = world.ambientLight.mul(this.diffuse.getColor(hit.tc.u, hit.tc.v));
        // check each light in scene
        for (final Light light : world.lightSources) {
            // check if light illuminates this point
            boolean lighting = light.illuminate(hitPoint, world);
            if (lighting) {
                Vector3 normalVector = light.directionFrom(hitPoint);
                // the opposite vector
                Vector3 reflectedVector = normalVector.reflectedOn(hit.normal);
                // if light illuminates hit point, there is need to calculate new xolor
                double firstMaximum = Math.max(0.0, hit.normal.dot(normalVector));
                double secondMaximum = Math.pow(Math.max(0.0, reflectedVector.dot(v)), this.exponent);
                c = c.add(light.color.mul(this.diffuse.getColor(hit.tc.u, hit.tc.v)).mul(firstMaximum)).add(light.color.mul(this.specular.getColor(hit.tc.u, hit.tc.v)).mul(secondMaximum));
            }
        }
        Color reflectedColor = tracer.colorFor(new Ray(hitPoint, hit.ray.d.add(hit.normal.mul(cosinusPhi))));
        return c.add(reflection.getColor(hit.tc.u, hit.tc.v).mul((reflectedColor)));
    }

}
