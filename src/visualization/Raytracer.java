package visualization;

import Lighting.DirectionalLight;
import Lighting.Light;
import Lighting.PointLight;
import Lighting.SpotLight;
import Material.LambertMaterial;
import Material.Material;
import Material.PhongMaterial;
import Material.ReflectiveMaterial;
import Material.SingleColorMaterial;
import Material.Tracer;
import Material.TransparentMaterial;
import Sampling.SamplingPattern;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Transform;
import bibliothek.Vector3;
import images.basics.ImageSaver;
import java.awt.Canvas;
import java.awt.Graphics;

import texture.Texture;
import visualization.camera.Camera;
import visualization.geometry.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import texture.ImageTexture;
import texture.SingleColorTexture;
import visualization.camera.DoFCamera;
import visualization.camera.PerspectiveCamera;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Raytracer extends Canvas {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 480;
    private BufferedImage image;
    private Camera camera;
    private World world;


    public Raytracer(World world, Camera camera){
        this.world = world;
        this.camera = camera;
        createGUI();
    }

    private void createGUI() {
        final JFrame frame = new JFrame();
        frame.setTitle("Create Image");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("File");
        final JMenuItem menuItem = new JMenuItem("Speichern");

        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ImageSaver saver = new ImageSaver(image);
            }
        });


        menu.add(menuItem);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
        frame.add(this);
        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        final WritableRaster raster = image.getRaster();
        final ColorModel model = image.getColorModel();
        for (int i = 0; i < this.image.getWidth(); i++) {
            for (int j = 0; j < this.image.getHeight(); j++) {
                ArrayList<Ray> rays = camera.rayFor(this.image.getWidth(), this.image.getHeight(), i, j);
                ArrayList<Color> colors = new ArrayList<Color>();
                for(Ray r : rays){
                    Hit h = world.hit(r);
                    Color c = new Color(0,0,0);
                    if(h == null){
                        c = world.backgroundColor;
                        break;
                    }else{
                        c = h.geo.material.colorFor(h, world, new Tracer(this.world, (int) world.refractionIndex));
                    }
                    colors.add(c);
                }
                    raster.setDataElements(i, image.getHeight() - j - 1, model.getDataElements(getApproximation(colors).toFloatAr(),0, null));
            }
            
        }
        g.drawImage(this.image, 0, 0, null);
    }

    public Color getApproximation(ArrayList<Color> colors){
        double r = 0;
        double g = 0;
        double b = 0;
        for(Color c : colors){
            r+=c.r;
            g+=c.g;
            b+=c.b;
        }
        r = r/colors.size();
        g = g/colors.size();
        b = b/colors.size();
        return new Color(r,g,b);
    }
    
    /**
     * setter für dynamische Gui
     */
    
    public BufferedImage getImage() {
        return image;
    }

    public Camera getCamera() {
        return camera;
    }

    public World getWorld() {
        return world;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setWorld(World world) {
        this.world = world;
        repaint();
    }

    public static void main(String[] args) {

        //task2();

        /*
            task 3 schatten
        */
        //task3WithoutLicht();
        //task3Lambert();
        //task3Phong();
        //task3PhongDirectionalLight();
        //task3PhongSpotLight();

        /*
            task 4 schatten
        */
        //task4();

        //transformation();

        //taskReflection();

        taskRefraction();

        //testTaskTextureImage();
        //testTaskTextureImagePlane();
        //testTaskTextureImageSphere();
        
        //testTaskDepthofField();

        //dreieck();
        //aab();

    }

    /**
     * Task "Schnittberechnung"
     */
    public static void task2(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI/4, sp);
        //Camera camera = new OrthographicCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), 3);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        SingleColorMaterial materialRed = new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)));
        SingleColorMaterial materialGreen = new SingleColorMaterial(new SingleColorTexture(new Color(0,1,0)));

        Plane plane = new Plane(new Point3(0,-1,0), new Normal3(0,1,0), materialGreen);
        Sphere sphere = new Sphere(new Point3(0,0,-3), 0.5, materialRed);
        Sphere sphere2 = new Sphere(new Point3(-1,0,-3), 0.5, materialRed);
        Sphere sphere3 = new Sphere(new Point3(1,0,-6), 0.5, materialRed);

        sceneObjects.add(sphere);
        sceneObjects.add(plane);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0),1);
        
        Raytracer raytracer = new Raytracer(world, camera);
    }

    /**
     * Demo scene without lighting.
     */
    public static void task3WithoutLicht(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        SingleColorMaterial materialRed = new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)));
        SingleColorMaterial materialGreen = new SingleColorMaterial(new SingleColorTexture(new Color(0,1,0)));
        SingleColorMaterial materialYellow = new SingleColorMaterial(new SingleColorTexture(new Color(1,1,0)));

        /*Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialRed);
        Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialGreen);*/
        Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialYellow);

//        sceneObjects.add(sphere);
        sceneObjects.add(tri);
//        sceneObjects.add(plane);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0),1);
        Raytracer raytracer = new Raytracer(world, camera);
    }

    /**
     * Demo scene with Lambert-lighting.
     */
    public static void task3Lambert(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(2,2,2), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);

        PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(2, 4, 4), true);
        //DirectionalLight lighting = new DirectionalLight(new Color(1,1,1), new Vector3(-1, -1, -1), true);
        //SpotLight lighting = new SpotLight(new Color(1,1,1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        LambertMaterial materialRedLam = new LambertMaterial(new SingleColorTexture(new Color(1,0,0)));
        LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));
        LambertMaterial materialYellowLam = new LambertMaterial(new SingleColorTexture(new Color(1,1,0)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialRedLam);
        Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialGreenLam);
        Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialYellowLam);

        sceneObjects.add(sphere);
        sceneObjects.add(tri);
        sceneObjects.add(plane);

        lightSources.add(lighting);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0),1);
        Raytracer raytracer = new Raytracer(world, camera);
    }
/****
    /**
     * Demo scene with Phong-lighting.
     */
    public static void task3Phong(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);
        PointLight lighting = new PointLight(new Color(1,1,1), new Point3(4,4,4), true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        //BlinnPhongMaterial materialGreenPhong = new BlinnPhongMaterial(new Color(0,1,0), new Color(1,1,1), 64);
        PhongMaterial materialGreenPhong = new PhongMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1,1,1)), 64);

        PhongMaterial materialYellowPhong = new PhongMaterial(new SingleColorTexture(new Color(1,1,0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
        PhongMaterial materialRedPhong = new PhongMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 64);
        LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialRedPhong);
//        Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialGreenPhong);
        Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialYellowPhong);

//        sceneObjects.add(sphere);
        sceneObjects.add(tri);
        sceneObjects.add(plane);

        lightSources.add(lighting);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0),1);
        Raytracer raytracer = new Raytracer(world, camera);
    }

    /**
     * Demo scene with Phong Directional Light.
     */
    public static void task3PhongDirectionalLight(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);

        DirectionalLight lighting = new DirectionalLight(new Color(1,1,1), new Vector3(-1, -1, -1), true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        PhongMaterial materialGreenPhong = new PhongMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1,1,1)), 64);
        PhongMaterial materialYellowPhong = new PhongMaterial(new SingleColorTexture(new Color(1,1,0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
        PhongMaterial materialRedPhong = new PhongMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 64);
        LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialRedPhong);
//        Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialGreenPhong);
        Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialYellowPhong);

//        sceneObjects.add(sphere);
        sceneObjects.add(tri);
        sceneObjects.add(plane);

        lightSources.add(lighting);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0), 1);
        Raytracer raytracer = new Raytracer(world, camera);
    }

    /**
     * Demo scene with Phong Directional Light.
     */
    public static void task3PhongSpotLight(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);

        SpotLight lighting = new SpotLight(new Color(1,1,1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        PhongMaterial materialGreenPhong = new PhongMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1,1,1)), 64);
        PhongMaterial materialYellowPhong = new PhongMaterial(new SingleColorTexture(new Color(1,1,0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
        PhongMaterial materialRedPhong = new PhongMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 64);
        LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialRedPhong);
//        Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, materialGreenPhong);
        Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialYellowPhong);

//        sceneObjects.add(sphere);
        sceneObjects.add(tri);
        sceneObjects.add(plane);

        lightSources.add(lighting);

        //World world = new World(sceneObjects, lightSources, new Color(0, 0, 0));
        World world = new World(sceneObjects, lightSources, new Color(0.25, 0.25, 0.25), 1);

        Raytracer raytracer = new Raytracer(world, camera);
    }


    /**
     * Demo scene with shadow. Task 4
     */
    public static void task4(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4, sp);

        PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(8, 8, 0), true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        LambertMaterial materialRedLam = new LambertMaterial(new SingleColorTexture(new Color(1,0,0)));
        LambertMaterial material08 = new LambertMaterial(new SingleColorTexture(new Color(0.8,0.8,0.8)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), material08);
//        Sphere sphere = new Sphere(new Point3(-0.5,1,-0.5), 1, materialRedLam);

//        sceneObjects.add(sphere);
        sceneObjects.add(plane);

        lightSources.add(lighting);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0), 1);
        Raytracer raytracer = new Raytracer(world, camera);
    }

    /**
     * Task 5
     */
    public static void transformation(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(2,2,4), new Vector3(-0.5, -0.5,-1), new Vector3(0,1,0), Math.PI/4, sp);
        PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(3, 3, 4), true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();
        ArrayList<Geometry> nodeList = new ArrayList<Geometry>();


        LambertMaterial materialRedLam = new LambertMaterial(new SingleColorTexture(new Color(1,0,0)));
        LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));
        LambertMaterial materialYellowLam = new LambertMaterial(new SingleColorTexture(new Color(1,1,0)));

        PhongMaterial materialGreenPhong = new PhongMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1,1,1)), 64);
        PhongMaterial materialYellowPhong = new PhongMaterial(new SingleColorTexture(new Color(1,1,0)), new SingleColorTexture(new Color(1, 1, 1)), 64);
        PhongMaterial materialRedPhong = new PhongMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 64);

        Plane plane = new Plane(new Point3(0,-0.5,0), new Normal3(0,1,0), materialGreenLam);
        Sphere sphere = new Sphere(new Point3(0, 0, 0), 0.6, materialRedPhong);
        AxisAlignedBox aab = new AxisAlignedBox(materialYellowLam);

        sceneObjects.add(sphere);
        //sceneObjects.add(aab);
        //sceneObjects.add(plane);
        lightSources.add(lighting);

        Transform transform = new Transform();

        transform = transform.rotateZ(Math.PI/2).scale(2, 0.5, 2);

        Node node = new Node(sceneObjects, transform);
        nodeList.add(node);

        World world = new World(nodeList, lightSources, new Color(0, 0, 0), 1);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public static void taskReflection(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1, -1,-1), new Vector3(0,1,0), Math.PI/4, sp);
        PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(8, 8, 8), true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();
        ArrayList<Geometry> nodeList = new ArrayList<Geometry>();


        ReflectiveMaterial materialSphere1 = new ReflectiveMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 64, new SingleColorTexture(new Color(0.5,0.5,0.5)));
        ReflectiveMaterial materialSphere2 = new ReflectiveMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1,1,1)), 64, new SingleColorTexture(new Color(0.5,0.5,0.5)));
        ReflectiveMaterial materialSphere3 = new ReflectiveMaterial(new SingleColorTexture(new Color(0,0,1)), new SingleColorTexture(new Color(1,1,1)), 64, new SingleColorTexture(new Color(0.5,0.5,0.5)));
        
        ReflectiveMaterial materialPlane = new ReflectiveMaterial(new SingleColorTexture(new Color(0.1,0.1,0.1)), new SingleColorTexture(new Color(0,0,0)), 64, new SingleColorTexture(new Color(0.5,0.5,0.5)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialPlane);
        
        Sphere sphere1 = new Sphere(new Point3(-3, 1, 0), 1, materialSphere1);
        Sphere sphere2 = new Sphere(new Point3(0, 1, 0), 1, materialSphere2);
        Sphere sphere3 = new Sphere(new Point3(3, 1, 0), 1, materialSphere3);

        sceneObjects.add(plane);
        sceneObjects.add(sphere1);
        sceneObjects.add(sphere2);
        sceneObjects.add(sphere3);
        lightSources.add(lighting);

        Transform transform = new Transform();

        //transform = transform.scale(3, 0.2, 1).translate(0, 0, 0);

        Node node = new Node(sceneObjects, transform);
        nodeList.add(node);

        World world = new World(nodeList, lightSources, new Color(0.25, 0.25, 0.25), 12);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public static void taskRefraction(){
        SamplingPattern sp = new SamplingPattern(3);
        Camera camera = new PerspectiveCamera(new Point3(8,8,8), new Vector3(-1, -1,-1), new Vector3(0,1,0), Math.PI/4, sp);
        
        PointLight lighting1 = new PointLight(new Color(0.3, 0.3, 0.3), new Point3(5, 5, -10), true);
        SpotLight lighting2 = new SpotLight(new Color(0.3,0.3,0.3), new Point3(0, 5, -10), new Vector3(0, -1, 0), Math.PI/8, true);
        DirectionalLight lighting3 = new DirectionalLight(new Color(0.3,0.3,0.3), new Vector3(1, -1, 0), true);
        
        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();
        ArrayList<Geometry> nodeList = new ArrayList<Geometry>();

        TransparentMaterial materialRedLam = new TransparentMaterial(12);
        
        ReflectiveMaterial reflectPlane = new ReflectiveMaterial(new SingleColorTexture(new Color(1,1,1)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,1,1)));
        
        ReflectiveMaterial reflectSphere1 = new ReflectiveMaterial(new SingleColorTexture(new Color(1,0,0)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,0.5,0.5)));
        ReflectiveMaterial reflectSphere2 = new ReflectiveMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,0.5,0.5)));
        ReflectiveMaterial reflectSphere3 = new ReflectiveMaterial(new SingleColorTexture(new Color(0,0,1)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,0.5,0.5)));
        ReflectiveMaterial reflectSphere4 = new ReflectiveMaterial(new SingleColorTexture(new Color(0,1,1)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,0.5,0.5)));
        ReflectiveMaterial reflectSphere5 = new ReflectiveMaterial(new SingleColorTexture(new Color(1,0,1)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,0.5,0.5)));
        ReflectiveMaterial reflectSphere6 = new ReflectiveMaterial(new SingleColorTexture(new Color(1,1,0)), new SingleColorTexture(new Color(1,1,1)), 10, new SingleColorTexture(new Color(1,0.5,0.5)));
        
        TransparentMaterial transparentSphere = new TransparentMaterial(12);
        
        PhongMaterial materialTri = new PhongMaterial(new SingleColorTexture(new Color(0,1,0)), new SingleColorTexture(new Color(0, 1, 0)), 20);

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0),reflectPlane);
        
        Sphere sphere1 = new Sphere(new Point3(0, 1, 0), 0.5, reflectSphere1);
        Sphere sphere2 = new Sphere(new Point3(-1.5, 1, 0), 0.5, reflectSphere2);
        Sphere sphere3 = new Sphere(new Point3(1.5, 1, 0), 0.5, reflectSphere3);
        Sphere sphere4 = new Sphere(new Point3(0, 1, -1.5), 0.5, reflectSphere4);
        Sphere sphere5 = new Sphere(new Point3(-1.5, 1, -1.5), 0.5, reflectSphere5);
        Sphere sphere6 = new Sphere(new Point3(1.5, 1, -1.5), 0.5, reflectSphere6);
        
        Sphere sphere7 = new Sphere(new Point3(0, 2, 1.5), 0.5, transparentSphere);
        Sphere sphere8 = new Sphere(new Point3(-1.5, 2, 1.5), 0.5, transparentSphere);
        Sphere sphere9 = new Sphere(new Point3(1.5, 2, 1.5), 0.5, transparentSphere);
        
        AxisAlignedBox aab = new AxisAlignedBox(new Point3(-0.5,0,3), new Point3(0.5,1,4), transparentSphere);

        Triangle tri = new Triangle(new Point3(0.7,0.5,3), new Point3(1.3,0.5,3), new Point3(0.7,0.5,4), materialTri);
        
        sceneObjects.add(sphere1);
        sceneObjects.add(sphere2);
        sceneObjects.add(sphere3);
        sceneObjects.add(sphere4);
        sceneObjects.add(sphere5);
        sceneObjects.add(sphere6);
        sceneObjects.add(sphere7);
        sceneObjects.add(sphere8);
        sceneObjects.add(sphere9);
        
        sceneObjects.add(aab);
        sceneObjects.add(tri);
        sceneObjects.add(plane);
        
        lightSources.add(lighting1);
        lightSources.add(lighting2);
        lightSources.add(lighting3);

        Transform transform = new Transform();

        //transform = transform.scale(3, 0.2, 1).translate(0, 0, 0);

        Node node = new Node(sceneObjects, transform);
        nodeList.add(node);

        World world = new World(nodeList, lightSources, new Color(0.1, 0.1, 0.1),16);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public static void testTaskTextureImage(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new PerspectiveCamera(new Point3(2,4,4), new Vector3(-0.5, -0.5,-1), new Vector3(0,1,0), Math.PI/4, sp);
        PointLight lighting = new PointLight(new Color(1, 0.2, 0.2), new Point3(3, 3, 4), true);
        PointLight lighting1 = new PointLight(new Color(1, 1, 1), new Point3(1, 2, 4), true);
        SpotLight lighting2 = new SpotLight(new Color(1,0.2,0.2), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, true);
        SpotLight lighting3 = new SpotLight(new Color(1,0,1), new Point3(10, 3, 1), new Vector3(-1, -1, -1), Math.PI/14, true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();
        ArrayList<Geometry> nodeList = new ArrayList<Geometry>();


        TransparentMaterial materialRedLam = new TransparentMaterial(12);
        ReflectiveMaterial materialGreenLam = new ReflectiveMaterial(new SingleColorTexture(new Color(1,1,1)), new SingleColorTexture(new Color(1,1,1)), 64, new SingleColorTexture(new Color(1,1,1)));
        LambertMaterial materialYellowLam = new LambertMaterial(new SingleColorTexture(new Color(1,1,0)));

        Material worldMat = new SingleColorMaterial(new ImageTexture(new Color(0,0,0), "texture2.jpg"));

        Plane plane = new Plane(new Point3(0,-0.5,0), new Normal3(0,1,0), worldMat);
        Plane plane1 = new Plane(new Point3(0,10,0), new Normal3(0,1,0), materialGreenLam);
        Sphere sphere = new Sphere(new Point3(0, 2, 0), 0.5, materialGreenLam);
        AxisAlignedBox aab = new AxisAlignedBox(materialYellowLam);

        sceneObjects.add(sphere);
        //sceneObjects.add(aab);
        sceneObjects.add(plane);
        //sceneObjects.add(plane1);
        lightSources.add(lighting);


        Point3 s = new Point3(4, 4, 4);
        Point3 p = new Point3(0, 0, 0);
        double ry = 5;

        Transform transform = new Transform();

        //transform = transform.scale(3, 0.2, 1).translate(0, 0, 0);

        Node node = new Node(sceneObjects, transform);
        nodeList.add(node);

        World world = new World(nodeList, lightSources, new Color(0.3, 0.3, 0.3), 16);
        Raytracer raytracer = new Raytracer(world, camera);


    }

    public static void testTaskTextureImagePlane(){
        SamplingPattern sp = new SamplingPattern(1);
        Camera camera = new PerspectiveCamera(new Point3(2,4,4), new Vector3(-0.5, -0.5,-1), new Vector3(0,1,0), Math.PI/4, sp);
        PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(3, 3, 4), true);
        PointLight lighting1 = new PointLight(new Color(1, 1, 1), new Point3(1, 2, 4), true);
        SpotLight lighting2 = new SpotLight(new Color(1,0.2,0.2), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, true);
        SpotLight lighting3 = new SpotLight(new Color(1,0,1), new Point3(10, 3, 1), new Vector3(-1, -1, -1), Math.PI/14, true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();
        ArrayList<Geometry> nodeList = new ArrayList<Geometry>();

        TransparentMaterial materialRedLam = new TransparentMaterial(4);
        ReflectiveMaterial materialGreenLam = new ReflectiveMaterial(new SingleColorTexture(new Color(1,1,1)), new SingleColorTexture(new Color(1,1,1)), 64, new SingleColorTexture(new Color(1,1,1)));
        LambertMaterial materialYellowLam = new LambertMaterial(new SingleColorTexture(new Color(1,1,0)));

        Material worldMat = new SingleColorMaterial(new ImageTexture(new Color(0.1,0.1,0.1), "texture2.jpg"));

        Plane plane = new Plane(new Point3(0,-0.5,0), new Normal3(0,1,0), worldMat);
        Plane plane1 = new Plane(new Point3(0,10,0), new Normal3(0,1,0), materialYellowLam);
        Sphere sphere = new Sphere(new Point3(0, 2, 0), 0.5, worldMat);
        AxisAlignedBox aab = new AxisAlignedBox(materialYellowLam);

        //sceneObjects.add(sphere);
        //sceneObjects.add(aab);
        sceneObjects.add(plane);
        //sceneObjects.add(plane1);
        lightSources.add(lighting);

        World world = new World(sceneObjects, lightSources, new Color(0.3, 0.3, 0.3), 3);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public static void testTaskTextureImageSphere(){
        final String path = "earth.jpg";
        final Texture imgTexture = new ImageTexture(new Color(0, 0, 0), path);
        final Camera camera = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0, new SamplingPattern(1));

        Material worldMat = new SingleColorMaterial(imgTexture);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        lightSources.add(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), true));
        Sphere sphere = new Sphere(new Point3(1,1,1), 0.5, worldMat);

        sceneObjects.add(sphere);

        final World world = new World(sceneObjects, lightSources, new Color(0.0, 0.0, 0.0), 1);

        Raytracer raytracer = new Raytracer(world, camera);

    }
    
    public static void testTaskDepthofField(){
        SamplingPattern sp = new SamplingPattern(10);
        Camera camera = new DoFCamera(new Point3(4,4,4), new Vector3(-1,-1,-1), new Vector3(0,1,0), Math.PI/4,1,0.3, sp);

        PointLight lighting = new PointLight(new Color(1, 1, 1), new Point3(2, 4, 4), true);
        //DirectionalLight lighting = new DirectionalLight(new Color(1,1,1), new Vector3(-1, -1, -1), true);
        //SpotLight lighting = new SpotLight(new Color(1,1,1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI/14, true);

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        LambertMaterial materialRedLam = new LambertMaterial(new SingleColorTexture(new Color(1,0,0)));
        LambertMaterial materialGreenLam = new LambertMaterial(new SingleColorTexture(new Color(0,1,0)));
        LambertMaterial materialYellowLam = new LambertMaterial(new SingleColorTexture(new Color(1,1,0)));

        Plane plane = new Plane(new Point3(0,0,0), new Normal3(0,1,0), materialRedLam);
        Sphere sphere = new Sphere(new Point3(0,1,1), 0.5, materialGreenLam);
        Triangle tri = new Triangle(new Point3(0,0,-1), new Point3(1,0,-1), new Point3(1,1,-1), materialYellowLam);

        sceneObjects.add(sphere);
        sceneObjects.add(tri);
        sceneObjects.add(plane);

        lightSources.add(lighting);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0),1);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public static void dreieck(){

        Camera camera = new PerspectiveCamera(new Point3(0,0,0), new Vector3(0,0,-1), new Vector3(0,1,0), Math.PI/4, new SamplingPattern(1));

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        SingleColorMaterial material = new SingleColorMaterial(new SingleColorTexture(new Color(1,0,1)));

        Triangle tri = new Triangle(new Point3(-0.5,0.5,-3), new Point3(0.5,0.5,-3), new Point3(0.5,-0.5,-3), material);

        sceneObjects.add(tri);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0), 1);
        Raytracer raytracer = new Raytracer(world, camera);

    }

    public static void aab(){

        Camera camera = new PerspectiveCamera(new Point3(3,3,3), new Vector3(-3,-3,-3), new Vector3(0,1,0), Math.PI/4, new SamplingPattern(1));

        ArrayList<Geometry> sceneObjects = new ArrayList();
        ArrayList<Light> lightSources = new ArrayList();

        SingleColorMaterial material = new SingleColorMaterial(new SingleColorTexture(new Color(0,0,1)));

        AxisAlignedBox aab = new AxisAlignedBox(material);

        sceneObjects.add(aab);

        World world = new World(sceneObjects, lightSources, new Color(0, 0, 0), 1);
        Raytracer raytracer = new Raytracer(world, camera);

    }

}
