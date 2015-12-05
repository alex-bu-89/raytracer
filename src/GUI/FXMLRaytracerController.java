/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Lighting.DirectionalLight;
import Lighting.PointLight;
import Lighting.SpotLight;
import bibliothek.Normal3;
import bibliothek.Point3;
import bibliothek.Vector3;
import java.io.IOException;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import visualization.Color;
import visualization.geometry.Plane;
import visualization.geometry.Sphere;
import visualization.geometry.Triangle;

/**
 * FXML Controller class
 *
 * @author Besitzer
 */
public class FXMLRaytracerController implements Initializable {

    private SceneControl control;
    @FXML
    private TextField centerSphereX;
    @FXML
    private TextField centerSphereY;
    @FXML
    private TextField centerSphereZ;
    @FXML
    private TextField sphereRadius;
    @FXML
    private Button addSphere;

    @FXML
    private TextField pointPlaneX;
    @FXML
    private TextField pointPlaneY;
    @FXML
    private TextField pointPlaneZ;

    @FXML
    private TextField normalPlaneX;
    @FXML
    private TextField normalPlaneY;
    @FXML
    private TextField normalPlaneZ;
    @FXML
    private Button addPlane;
    

    @FXML
    private TextField p1TriangleX;
    @FXML
    private TextField p1TriangleY;
    @FXML
    private TextField p1TriangleZ;

    @FXML
    private TextField p2TriangleX;
    @FXML
    private TextField p2TriangleY;
    @FXML
    private TextField p2TriangleZ;

    @FXML
    private TextField p3TriangleX;
    @FXML
    private TextField p3TriangleY;
    @FXML
    private TextField p3TriangleZ;
    @FXML
    private Button addTriangle;
    

    @FXML
    private CheckBox pointLightCheck;
    @FXML
    private CheckBox spotLightCheck;
    @FXML
    private CheckBox directionalLightCheck;

    @FXML
    private TextField pointLightR;
    @FXML
    private TextField pointLightG;
    @FXML
    private TextField pointLightB;

    @FXML
    private TextField pointLightX;
    @FXML
    private TextField pointLightY;
    @FXML
    private TextField pointLightZ;

    @FXML
    private TextField directionLightX;
    @FXML
    private TextField directionLightY;
    @FXML
    private TextField directionLightZ;

    @FXML
    private TextField lightAngle;

    @FXML
    private Button addLight;
    
    @FXML
    private Button clear;

    @FXML
    private Button createGUI;
    @FXML
    private ListView geoListView;
    @FXML
    private ListView lightListView;
    
    @FXML
    private Button materialSphere;
    @FXML
    private Button materialPlane;
    @FXML
    private Button materialTri;
    @FXML
    private Button materialAAB;
    
    @FXML
    private ImageView preview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeSimpleAdd();
    }

    public FXMLRaytracerController() {
        this.control = new SceneControl();
    }

    public void initializeSimpleAdd() {
        addSphere.setOnAction((e) -> handleAddSphere());
        addPlane.setOnAction((e) -> handleAddPlane());
        addTriangle.setOnAction((e) -> handleAddTri());
        materialSphere.setOnAction((e) -> handleMaterial());
        materialPlane.setOnAction((e) -> handleMaterial());
        materialTri.setOnAction((e) -> handleMaterial());
        addLight.setOnAction((e) -> handleAddLight());
        //materialAAB.setOnAction((e) -> handleMaterial());
        createGUI.setOnAction((e) -> handleCreate());
        clear.setOnAction((e) -> clear());

    }

    public void handleAddSphere() {
        double x = Double.parseDouble(centerSphereX.getText());
        double y = Double.parseDouble(centerSphereY.getText());
        double z = Double.parseDouble(centerSphereZ.getText());
        double radius = Double.parseDouble(sphereRadius.getText());
        System.out.println(x + y + z);
        Sphere sphere = new Sphere(new Point3(x, y, z), radius, this.control.getCurrentMaterial());
        this.control.addGeo(sphere);
        updateLists();
        System.out.println("hm should work");
    }

    public void handleCreate() {
        this.control.create();
        updateLists();
    }

    public void updateLists() {
        geoListView.setItems(FXCollections.observableArrayList(this.control.getGeoList()));

    }

    public void handleAddPlane() {
        double xP = Double.parseDouble(pointPlaneX.getText());
        double yP = Double.parseDouble(pointPlaneY.getText());
        double zP = Double.parseDouble(pointPlaneZ.getText());

        double xN = Double.parseDouble(normalPlaneX.getText());
        double yN = Double.parseDouble(normalPlaneY.getText());
        double zN = Double.parseDouble(normalPlaneZ.getText());

        Plane plane = new Plane(new Point3(xP, yP, zP), new Normal3(xN, yN, zN), this.control.getCurrentMaterial());
        this.control.addGeo(plane);
        updateLists();
    }

    public void handleAddTri() {
        double xP1 = Double.parseDouble(p1TriangleX.getText());
        double yP1 = Double.parseDouble(p1TriangleY.getText());
        double zP1 = Double.parseDouble(p1TriangleZ.getText());

        Point3 point1 = new Point3(xP1, yP1, zP1);

        double xP2 = Double.parseDouble(p2TriangleX.getText());
        double yP2 = Double.parseDouble(p2TriangleY.getText());
        double zP2 = Double.parseDouble(p2TriangleZ.getText());

        Point3 point2 = new Point3(xP2, yP2, zP2);

        double xP3 = Double.parseDouble(p3TriangleX.getText());
        double yP3 = Double.parseDouble(p3TriangleY.getText());
        double zP3 = Double.parseDouble(p3TriangleZ.getText());

        Point3 point3 = new Point3(xP3, yP3, zP3);

        Triangle tri = new Triangle(point1, point2, point3, this.control.getCurrentMaterial());
        this.control.addGeo(tri);
        updateLists();
    }

    public void handleAddLight() {
        double x = Double.parseDouble(pointLightX.getText());
        double y = Double.parseDouble(pointLightY.getText());
        double z = Double.parseDouble(pointLightZ.getText());
        
        Point3 p = new Point3(x,y,z);
        
        double r = Double.parseDouble(pointLightR.getText());
        double g = Double.parseDouble(pointLightG.getText());
        double b = Double.parseDouble(pointLightB.getText());
        
        Color c = new Color(r,g,b);
        
        double dx = Double.parseDouble(directionLightX.getText());
        double dy = Double.parseDouble(directionLightY.getText());
        double dz = Double.parseDouble(directionLightZ.getText());
        
        Vector3 d = new Vector3(dx,dy,dz);
        
        double angle = Double.parseDouble(lightAngle.getText());
        
        if (pointLightCheck.isSelected()) {
            PointLight pl = new PointLight(c,p, true);
            this.control.addLight(pl);
        }
        if(spotLightCheck.isSelected()){
            SpotLight spot = new SpotLight(c,p,d,angle,true);
            this.control.addLight(spot);
        }
        if(directionalLightCheck.isSelected()){
            DirectionalLight direct = new DirectionalLight(c,d,true);
            this.control.addLight(direct);
        }
        
        updateLists();
    }
    
    public void handleMaterial(){
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Root-Path History");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("FXMLMaterial.fxml"));
        fxmlLoader.setController(new FXMLMaterialController(this.control));
        try {
            Pane pane = (Pane) fxmlLoader.load();
            Scene scene = new Scene(pane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.println("Scene not found");
        }
        
    }
    
    public void clear(){
        this.control = new SceneControl();
    }

}
