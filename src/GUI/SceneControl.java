/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Lighting.Light;
import Lighting.PointLight;
import Material.Material;
import Material.SingleColorMaterial;
import Sampling.SamplingPattern;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Vector3;
import java.util.ArrayList;
import texture.SingleColorTexture;
import visualization.Color;
import visualization.Raytracer;
import visualization.World;
import visualization.camera.Camera;
import visualization.camera.PerspectiveCamera;
import visualization.geometry.Geometry;
import visualization.geometry.Plane;

/**
 *
 * @author Besitzer
 */
public class SceneControl {
    
    private Raytracer raytracer;
    
    private Camera camera;
    
    private ArrayList<Geometry> geoList;
    
    private ArrayList<Light> lights;
    
    private World world;
    
    private Material currentMaterial;

    public SceneControl() {
        SamplingPattern sp = new SamplingPattern(10);
        this.currentMaterial = new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)));
        this.camera = new PerspectiveCamera(new Point3(2,2,4), new Vector3(-0.5, -0.5,-1), new Vector3(0,1,0), Math.PI/4, sp);
        this.geoList = new ArrayList<Geometry>();
        this.lights = new ArrayList<Light>();
        this.lights.add(new PointLight(new Color(1,1,1), new Point3(4,4,4), true));
        this.world = new World(this.geoList, this.lights, new Color(0.1,0.1,0.1), 2);
        this.raytracer = new Raytracer(world, this.camera);
    }

    public Material getCurrentMaterial() {
        return currentMaterial;
    }

    public Raytracer getRaytracer() {
        return raytracer;
    }
    

    public void setCurrentMaterial(Material currentMaterial) {
        this.currentMaterial = currentMaterial;
    }
    

    public ArrayList<Geometry> getGeoList() {
        return geoList;
    }

    public void addGeo(Geometry geo) {
        this.geoList.add(geo);
    }

    public ArrayList<Light> getLights() {
        return lights;
    }

    public void addLight(Light light) {
        this.lights.add(light);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
    public void create(){
        this.world = new World(this.geoList, this.lights, new Color(0.1,0.1,0.1), 2);
        this.raytracer.repaint();
    }
    
}
