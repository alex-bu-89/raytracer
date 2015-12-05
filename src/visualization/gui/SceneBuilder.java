package visualization.gui;

import Lighting.DirectionalLight;
import Lighting.Light;
import Lighting.PointLight;
import Lighting.SpotLight;
import Material.LambertMaterial;
import Material.PhongMaterial;
import Material.SingleColorMaterial;
import Sampling.SamplingPattern;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Transform;
import bibliothek.Vector3;
import texture.SingleColorTexture;
import visualization.Color;
import visualization.Raytracer;
import visualization.World;
import visualization.camera.Camera;
import visualization.camera.PerspectiveCamera;
import visualization.geometry.*;

import java.util.ArrayList;

/**
 * Created by Alexander Buyanov on 08/02/15.
 */
public class SceneBuilder {

    RayMenu frame;
    private Camera camera;
    private World world;
    private Raytracer raytracer;
    private SamplingPattern sp;

    private ArrayList<Geometry> sceneObjects;
    private ArrayList<Light> lightSources;

    final SingleColorMaterial materialRed = new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)));
    final SingleColorMaterial materialGreen = new SingleColorMaterial(new SingleColorTexture(new Color(0,1,0)));
    final SingleColorMaterial materialYellow = new SingleColorMaterial(new SingleColorTexture(new Color(1,1,0)));

    final LambertMaterial materialRedLam = new LambertMaterial(new SingleColorTexture(new Color(1,0,0)));
    final LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));
    final LambertMaterial materialYellowLam = new LambertMaterial(new SingleColorTexture(new Color(1,1,0)));

    final PhongMaterial materialGreenPhong = new PhongMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
    final PhongMaterial materialYellowPhong = new PhongMaterial(new SingleColorTexture(new Color(1,1,0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
    final PhongMaterial materialRedPhong = new PhongMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 64);

    public SceneBuilder(RayMenu frame){
        this.frame = frame;
    }

    public void buildImg(String geo, String light, String material, int aliasing){

        if(aliasing < 100){
            this.sp = new SamplingPattern(aliasing);
        } else {
            this.sp = new SamplingPattern(1);
        }

        this.sp = new SamplingPattern(aliasing);
        Camera camera = new PerspectiveCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);

        this.sceneObjects = new ArrayList();
        this.lightSources = new ArrayList();

        if(light.equals("No licht")){
            System.out.println("No light");
        } else if(light.equals("Point")){
            PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(2, 4, 4), true);
            lightSources.add(lighting);
        } else if(light.equals("Directional")){
            DirectionalLight lighting = new DirectionalLight(new Color(1,1,1), new Vector3(-1, -1, -1), true);
            lightSources.add(lighting);
        } else if(light.equals("Spot")){
            SpotLight lighting = new SpotLight(new Color(1,1,1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, true);
            lightSources.add(lighting);
        }

        if(material.equals("SingleMaterial")){
            Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialGreen);
            sceneObjects.add(plane);
            if(geo.equals("Sphere")){
                Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialRed);
                sceneObjects.add(sphere);
            } else if(geo.equals("Triangle")){
                Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialRed);
                sceneObjects.add(tri);
            } else if(geo.equals("Box")){
                AxisAlignedBox aab = new AxisAlignedBox(materialRed);
                sceneObjects.add(aab);
            } else if(geo.equals("All")){
                Sphere sphere = new Sphere(new Point3(-0.5,1,1), 0.5, materialRed);
                Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialRed);
                AxisAlignedBox aab = new AxisAlignedBox(materialRed);
                sceneObjects.add(sphere);
                sceneObjects.add(tri);
                sceneObjects.add(aab);
            }
        } else if(material.equals("Lambert") && !light.equals("No licht")){
            Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialGreenLam);
            sceneObjects.add(plane);
            if(geo.equals("Sphere")){
                Sphere sphere = new Sphere(new Point3(-0.5,1,1), 0.5, materialRedLam);
                sceneObjects.add(sphere);
            } else if(geo.equals("Triangle")){
                Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialRedLam);
                sceneObjects.add(tri);
            } else if(geo.equals("Box")){
                AxisAlignedBox aab = new AxisAlignedBox(materialRedLam);
                sceneObjects.add(aab);
            } else if(geo.equals("All")){
                Sphere sphere = new Sphere(new Point3(-0.5,1,1), 0.5, materialRedLam);
                Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialRedLam);
                AxisAlignedBox aab = new AxisAlignedBox(materialRedLam);
                sceneObjects.add(sphere);
                sceneObjects.add(tri);
                sceneObjects.add(aab);
            }
        } else if(material.equals("Phong") && !light.equals("No licht")){
            Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialGreenPhong);
            sceneObjects.add(plane);
            if(geo.equals("Sphere")){
                Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialRedPhong);
                sceneObjects.add(sphere);
            } else if(geo.equals("Triangle")){
                Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialRedPhong);
                sceneObjects.add(tri);
            } else if(geo.equals("Box")){
                AxisAlignedBox aab = new AxisAlignedBox(materialRedPhong);
                sceneObjects.add(aab);
            } else if(geo.equals("All")){
                Sphere sphere = new Sphere(new Point3(-0.5,1,1), 0.5, materialRedPhong);
                Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialRedPhong);
                AxisAlignedBox aab = new AxisAlignedBox(materialRedPhong);
                sceneObjects.add(sphere);
                sceneObjects.add(tri);
                sceneObjects.add(aab);
            }
        }

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0), 1);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public Raytracer getRaytracer(){
        return this.raytracer;
    }





}
