
package visualization.geometry;

import Material.Material;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Transform;
import visualization.Ray;

import java.util.ArrayList;

public class AxisAlignedBox extends Geometry {

    private Point3 lbf = new Point3(0, 0, 0);
    private Point3 run = new Point3(1, 1, 1);

    private Node right;
    private Node top;
    private Node front;
    private Node left;
    private Node bottom;
    private Node back;


    public AxisAlignedBox(Material material) {

        super(material);

        ArrayList<Geometry> geometryList = new ArrayList<Geometry>();
        geometryList.add(new Plane(material));
        left = new Node(geometryList, new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateZ(Math.PI/2));
        bottom =  new Node(geometryList, new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateX(Math.PI));
        back = new Node(geometryList, new Transform().translate(this.lbf.x, this.lbf.y, this.lbf.z).rotateZ(Math.PI).rotateX(-Math.PI/2));
        right = new Node(geometryList, new Transform().translate(this.run.x, this.run.y, this.run.z).rotateZ(-Math.PI/2));
        top =  new Node(geometryList, new Transform().translate(this.run.x, this.run.y, this.run.z));
        front = new Node(geometryList, new Transform().translate(this.run.x, this.run.y, this.run.z).rotateZ(Math.PI).rotateX(Math.PI/2));

    }

    public AxisAlignedBox(Point3 lbf, Point3 run, Material material) {
        this(material);
        this.lbf = lbf;
        this.run = run;
    }


    /**
     * Defined abstract Method hit() for all Geometry-Objects.
     *
     * @param ray
     * @return
     */
    @Override
    public Hit hit(Ray ray) {

        final ArrayList<Hit> hits = new ArrayList<Hit>();
        final Hit[] planeHits1 = new Hit[] {left.hit(ray), right.hit(ray)};
        final Hit[] planeHits2 = new Hit[] {top.hit(ray), bottom.hit(ray)};
        final Hit[] planeHits3 = new Hit[] {front.hit(ray), back.hit(ray)};

        for (Hit hit : planeHits1) {
            if (hit != null) {
                Point3 p = ray.at(hit.t);
                if (p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
                    hits.add(hit);
                }
            }
        }
        for (Hit hit : planeHits2) {
            if (hit != null) {
                Point3 p = ray.at(hit.t);
                if (p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z) {
                    hits.add(hit);
                }
            }
        }
        for (Hit hit : planeHits3) {
            if (hit != null) {
                Point3 p = ray.at(hit.t);
                if (p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y) {
                    hits.add(hit);
                }
            }
        }
        Hit hit = null;
        for (final Hit h : hits) {
            if (hit == null || h.t < hit.t) {
                hit = h;
            }
        }
        return hit;

    }
}
