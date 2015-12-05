/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visualization.geometry;

import bibliothek.Normal3;
import texture.TextCoord2;
import visualization.Ray;

/**
 * Defines the hit to the object
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Hit {

    /**
     * Distance t of the Ray from the point of origin to the Hit
     */
    public final double t;

    /**
     * the ray, that hitted the object
     */
    public final Ray ray;

    /**
     * Geometry object, that was hitted
     */
    public final Geometry geo;

    /**
     *  Normal
     */
    public final Normal3 normal;
    
    public final TextCoord2 tc;

    public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 normal, final TextCoord2 tc) {
        this.t = t;
        this.ray = ray;
        this.geo = geo;
        this.normal = normal;
        this.tc = tc;
    }

    @Override
    public String toString() {
        return "Hit{" +
                "t=" + t +
                ", ray=" + ray +
                ", geo=" + geo +
                ", normal=" + normal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hit hit = (Hit) o;

        if (Double.compare(hit.t, t) != 0) return false;
        if (geo != null ? !geo.equals(hit.geo) : hit.geo != null) return false;
        if (normal != null ? !normal.equals(hit.normal) : hit.normal != null) return false;
        if (ray != null ? !ray.equals(hit.ray) : hit.ray != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(t);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ray != null ? ray.hashCode() : 0);
        result = 31 * result + (geo != null ? geo.hashCode() : 0);
        result = 31 * result + (normal != null ? normal.hashCode() : 0);
        return result;
    }
}
