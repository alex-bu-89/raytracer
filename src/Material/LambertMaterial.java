package Material;

import Lighting.Light;
import bibliothek.Normal3;
import bibliothek.Point3;
import texture.Texture;
import visualization.Color;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Calculates the color of a lambert material
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class LambertMaterial extends Material{

    /**
     * The color of the material.
     */
    public final Texture color;
    
    public LambertMaterial(final Texture color){
        this.color = color;
    }

    @Override
    public Color colorFor(final Hit hit, final World world, Tracer tracer) {
        Normal3 n = hit.normal;
        Point3 p = hit.ray.at(hit.t);
        Color newCol = world.ambientLight.mul(this.color.getColor(hit.tc.u, hit.tc.v));
        for(Light light : world.lightSources){
            boolean lighting = light.illuminate(p, world);
            Color c2 = newCol;
            if(lighting){
                c2 = c2.add(light.color.mul(this.color.getColor(hit.tc.u, hit.tc.v)).mul(Math.max(0, n.dot(light.directionFrom(p)))));
            }
            newCol = c2;
        }
        return newCol;

    }

    @Override
    public String toString() {
        return "LambertMaterial{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LambertMaterial that = (LambertMaterial) o;

        if (color != null ? !color.equals(that.color) : that.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
