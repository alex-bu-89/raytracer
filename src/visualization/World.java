package visualization;

import Lighting.Light;
import java.util.ArrayList;

import visualization.camera.Camera;
import visualization.geometry.Geometry;
import visualization.geometry.Hit;

/**
 * Implements scene of a ray tracer
 *
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class World {

    /**
     * camera of a scene
     */
    public Camera camera;

    /**
     * geometry objects list
     */
    public ArrayList<Geometry> sceneObjects;

    /**
     * light source list
     */
    public ArrayList<Light> lightSources;

    /**
     * The color if the scene
     */
    public Color backgroundColor = new Color(0.1,0.1,0.1);

    /**
     * ambient light
     */
    public Color ambientLight;
    
    public double refractionIndex;

    public World(ArrayList<Geometry> sceneObjects, ArrayList<Light> lightSources, Color ambientLight, double refractionIndex){
        this.sceneObjects = sceneObjects;
        this.lightSources = lightSources;
        this.ambientLight = ambientLight;
        this.refractionIndex = refractionIndex;
    }

    public Hit hit(Ray ray){
        Hit minHit = null;
        double min_t = Double.MAX_VALUE;
        for (final Geometry element : sceneObjects) {
            final Hit hit = element.hit(ray);
            if ((hit != null) && 0.0001 < hit.t && hit.t < min_t){
                minHit = hit;
                min_t = hit.t;
            }
        }
        return minHit;
    }

    public ArrayList<Hit> hitList(Ray ray){
        ArrayList<Hit> hitList = new ArrayList();
            hitList.add(this.hit(ray));
        return hitList;
    }

}
