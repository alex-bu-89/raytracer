package Material;

import Lighting.Light;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Vector3;
import texture.Texture;
import visualization.Color;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Calculates the color of a phong material
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class PhongMaterial extends Material{

    /**
     * diffuse color
     */
    private final Texture diffuse;

    /**
     * specular color
     */
    private final Texture specular;

    /**
     * exponent
     */
    private final int exponent;
    
    public PhongMaterial(final Texture diffuse, final Texture specular, final int exponent){
        this.diffuse = diffuse;
        this.specular = specular;
        this.exponent = exponent;
    }
    /**
     * calculates the color for the given hit
     * @param hit
     * @param world
     * @param tracer
     * @return color at the point
     */
    @Override
    public Color colorFor(Hit hit, World world, Tracer tracer) {
        Normal3 n = hit.normal;
        Point3 p = hit.ray.at(hit.t);
        Color c = world.ambientLight.mul(this.diffuse.getColor(hit.tc.u, hit.tc.v));

        for(Light light : world.lightSources){
            boolean lighting = light.illuminate(p, world);
            Vector3 directFrom = light.directionFrom(p);
            if(lighting){
                Vector3 l = directFrom.normalized();
                Vector3 r = l.reflectedOn(n);
                double max = Math.max(0.0, n.dot(l));
                double max2 = Math.max(0.0,hit.ray.d.dot(r));
                c = c.add(this.diffuse.getColor(hit.tc.u, hit.tc.v).mul(light.color).mul(max)).add(specular.getColor(hit.tc.u, hit.tc.v).mul(light.color).mul(Math.pow(max2, this.exponent)));
            }
        }
        return c;
    }

    @Override
    public String toString() {
        return "PhongMaterial{" +
                "diffuse=" + diffuse +
                ", specular=" + specular +
                ", exponent=" + exponent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhongMaterial that = (PhongMaterial) o;

        if (exponent != that.exponent) return false;
        if (diffuse != null ? !diffuse.equals(that.diffuse) : that.diffuse != null) return false;
        if (specular != null ? !specular.equals(that.specular) : that.specular != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = diffuse != null ? diffuse.hashCode() : 0;
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + exponent;
        return result;
    }

}
