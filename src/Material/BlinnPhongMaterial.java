package Material;

import Lighting.Light;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Vector3;
import texture.SingleColorTexture;
import texture.Texture;
import visualization.Color;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Calculates the color of a Blinn Phong material
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class BlinnPhongMaterial extends Material {

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
    
    public BlinnPhongMaterial(final SingleColorTexture diffuse, final SingleColorTexture specular, final int exponent){
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
    public Color colorFor(final Hit hit, final World world, Tracer tracer) {
        Normal3 n = hit.normal;
        //point on the surface of the geometry object
        Point3 p = hit.ray.at(hit.t);
        Color c = world.ambientLight.mul(this.diffuse.getColor(hit.tc.u, hit.tc.v));
        //loops through all lights
        for(Light light : world.lightSources){
            boolean lighting = light.illuminate(p, world);
            Vector3 directFrom = light.directionFrom(p);
            if(lighting){
                Vector3 l = directFrom.normalized();
                Vector3 h = l.add((hit.ray.d.mul(-1)).normalized()).normalized();
                double max = Math.max(0.0, l.dot(n));
                double max2 = Math.max(0.0,h.dot(n));
                c = c.add(light.color.mul(this.diffuse.getColor(hit.tc.u, hit.tc.v)).mul(max)).add(light.color.mul(this.specular.getColor(hit.tc.u, hit.tc.v)).mul(Math.pow(max2, this.exponent)));
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
    public int hashCode() {
        int result = diffuse != null ? diffuse.hashCode() : 0;
        result = 31 * result + (specular != null ? specular.hashCode() : 0);
        result = 31 * result + exponent;
        return result;
    }
    
}
