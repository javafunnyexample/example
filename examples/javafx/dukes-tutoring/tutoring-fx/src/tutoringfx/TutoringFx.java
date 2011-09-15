/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoringfx;

import dukestutoring.entity.Guardian;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tutoringfx.adapter.StatusClient;
import tutoringfx.security.Authenticator;

/**
 * Main Application. This class handles navigation and user session.
 */
public class TutoringFx extends Application {
    private Stage stage;
    private Guardian guardian;
    
    private static TutoringFx instance;

    public TutoringFx() {
        instance = this;
    }
    
    public static TutoringFx getInstance() {
        return instance;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            gotoLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(TutoringFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Guardian getGuardian() {
        return guardian;
    }
        
    public boolean userLogin(String email, String password){
        if (Authenticator.validate(email, password)) {
            StatusClient client = new StatusClient();
            guardian = (Guardian) client.getGuardianByEmail_JSON(Guardian.class, email);
            client.close();
            gotoStatus();
            return true;
        } else {
            return false;
        }
    }
    
    public void userLogout(){
        guardian = null;
        gotoLogin();
    }
    
    public void refreshStatus() {
        gotoStatus();
    }
    
    private void gotoStatus() {
        try {
            replaceSceneContent("status.fxml");
        } catch (Exception ex) {
            Logger.getLogger(TutoringFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            replaceSceneContent("login.fxml");
        } catch (Exception ex) {
            Logger.getLogger(TutoringFx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BorderPane replaceSceneContent(String fxml) throws Exception {
        BorderPane page = (BorderPane) FXMLLoader.load(TutoringFx.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page);
            scene.getStylesheets().add("tutoringfx/main.css");
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}