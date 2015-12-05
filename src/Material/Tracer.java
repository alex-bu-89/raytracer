/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Material;

import visualization.Color;
import visualization.Ray;
import visualization.World;
import visualization.geometry.Hit;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Tracer {

    /**
     * the world working on
     */
    public final World world;
    /**
     * an internal refraction index
     */
    public int refractionIndex;

    /**
     * instanciates a tracer
     *
     * @param world the world working on
     * @param refractionIndex an internal refraction index
     */
    public Tracer(final World world, final int refractionIndex) {
        super();
        this.world = world;
        this.refractionIndex = refractionIndex;
    }

    /**
     * color for method of the ray
     *
     * @param r the ray
     * @return the calculated color
     */
    public Color colorFor(final Ray r) {
        if (r == null) {
            throw new IllegalArgumentException("The ray cannot be null!");
        }
        if (refractionIndex <= 0) {
            return this.world.backgroundColor;
        } else {
            Hit hit = world.hit(r);
            if (hit != null) {
                return hit.geo.material.colorFor(hit, world, new Tracer(world, refractionIndex - 1));
            } else {
                return this.world.backgroundColor;
            }
        }
    }

    /**
     * getter for refraction index
     *
     * @return the current refractionIndex
     */
    public int getRefractionIndex() {
        return this.refractionIndex;
    }

    /**
     * setter for refraction index
     *
     * @param refractionIndex the index which to set depth to
     */
    public void setRefractionIndex(final int refractionIndex) {
        this.refractionIndex = refractionIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tracer tracer = (Tracer) o;
        if (refractionIndex != tracer.refractionIndex) {
            return false;
        }
        if (world != null ? !world.equals(tracer.world) : tracer.world != null) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tracer{"
                + "world=" + world
                + ", refractionIndex=" + refractionIndex
                + '}';
    }
    
}
