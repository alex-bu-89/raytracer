package visualization.geometry;

import Material.SingleColorMaterial;
import bibliothek.Transform;
import visualization.Color;
import visualization.Ray;

import java.util.ArrayList;
import texture.SingleColorTexture;
import texture.Texture;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Node extends Geometry {

    public ArrayList<Geometry> geometryList;
    private final Transform transform;

    public Node(ArrayList<Geometry> geometryList, Transform transform) {
        super(new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0))));
        this.geometryList = geometryList;
        this.transform = transform;
    }

    /**
     * Defined abstract Method hit() for all Geometry-Objects.
     *
     * @param ray
     * @return
     */
    @Override
    public Hit hit(Ray ray) {
        final Ray toTransformRay = transform.mul(ray);
        double t = Double.POSITIVE_INFINITY;
        Hit minHit = null;
        for (Geometry geo : geometryList) {
            final Hit hit = geo.hit(toTransformRay);
            if (hit != null && hit.t < t) {
                minHit = hit;
                t = hit.t;
            }
        }
        if (minHit == null) {
            return null;
        }
        return new Hit(t, ray, minHit.geo, transform.mul(minHit.normal), minHit.tc);
    }

}
