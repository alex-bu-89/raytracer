package Material;

import texture.Texture;
import visualization.Color;
import visualization.World;
import visualization.geometry.Hit;

/**
 * Implements the color
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class SingleColorMaterial extends Material{

    /**
     * The color of the material
     */
    private final Texture color;

    public SingleColorMaterial(final Texture color) {
        this.color = color;
    }

    @Override
    public Color colorFor(final Hit hit, final World world, Tracer tracer) {
        return this.color.getColor(hit.tc.u, hit.tc.v);
    }

    @Override
    public String toString() {
        return "SingleColorMaterial{" +
                "color=" + color +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SingleColorMaterial that = (SingleColorMaterial) o;

        if (color != null ? !color.equals(that.color) : that.color != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return color != null ? color.hashCode() : 0;
    }
}
