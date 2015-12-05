package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This is the main class.
 *
 */
public class Start extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("FXMLRaytracer.fxml"));

        final Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
