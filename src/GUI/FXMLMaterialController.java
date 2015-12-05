/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Material.BlinnPhongMaterial;
import Material.LambertMaterial;
import Material.Material;
import Material.PhongMaterial;
import Material.ReflectiveMaterial;
import Material.SingleColorMaterial;
import Material.TransparentMaterial;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import texture.SingleColorTexture;
import visualization.Color;

/**
 * FXML Controller class
 *
 * @author Besitzer
 */
public class FXMLMaterialController implements Initializable {

    private SceneControl sc;

    @FXML
    private ChoiceBox materialChoice;

    @FXML
    private TextField diffuseR;
    @FXML
    private TextField diffuseG;
    @FXML
    private TextField diffuseB;

    @FXML
    private TextField specularR;
    @FXML
    private TextField specularG;
    @FXML
    private TextField specularB;

    @FXML
    private TextField exponent;

    @FXML
    private TextField reflectR;
    @FXML
    private TextField reflectG;
    @FXML
    private TextField reflectB;

    @FXML
    private TextField refraction;

    @FXML
    private Button addMaterial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeChoice();
        addMaterial.setOnAction((e) -> handleAddMaterial());
    }

    public FXMLMaterialController(SceneControl sc) {
        this.sc = sc;
    }

    public void initializeChoice() {
        materialChoice.getItems().addAll("LambertMaterial", "PhongMaterial", "BlinnPhongMaterial", "ReflectiveMaterial", "TransparentMaterial");
    }

    public Material getMaterial() {

        double rd = Double.parseDouble(diffuseR.getText());
        double gd = Double.parseDouble(diffuseG.getText());
        double bd = Double.parseDouble(diffuseB.getText());

        double rs = Double.parseDouble(specularR.getText());
        double gs = Double.parseDouble(specularG.getText());
        double bs = Double.parseDouble(specularB.getText());

        int exp = Integer.parseInt(exponent.getText());

        double rr = Double.parseDouble(specularR.getText());
        double gr = Double.parseDouble(specularG.getText());
        double br = Double.parseDouble(specularB.getText());

        Color diffuse = new Color(rd, gd, bd);

        Color specular = new Color(rs, gs, bs);
        
        Color reflected = new Color(rr, gr, br);
        
        int refract = Integer.parseInt(refraction.getText());
        
        if (materialChoice.getSelectionModel().getSelectedItem().equals("LambertMaterial")) {
            return new LambertMaterial(new SingleColorTexture(diffuse));
        }
        if (materialChoice.getSelectionModel().getSelectedItem().equals("PhongMaterial")) {
            return new PhongMaterial(new SingleColorTexture(diffuse), new SingleColorTexture(specular) , exp);
        }
        if (materialChoice.getSelectionModel().getSelectedItem().equals("BlinnPhongMaterial")) {
            return new BlinnPhongMaterial(new SingleColorTexture(diffuse), new SingleColorTexture(specular) , exp);
        }
        if (materialChoice.getSelectionModel().getSelectedItem().equals("ReflectiveMaterial")) {
            return new ReflectiveMaterial(new SingleColorTexture(diffuse), new SingleColorTexture(specular), exp, new SingleColorTexture(reflected));
        }
        if (materialChoice.getSelectionModel().getSelectedItem().equals("TransparentMaterial")) {
            return new TransparentMaterial(refract);
        }
        return new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)));
    }
    
    public void handleAddMaterial(){
        sc.setCurrentMaterial(getMaterial());
        Stage window = (Stage) materialChoice.getScene().getWindow();
        window.close();
    }

}
