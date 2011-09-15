/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tutoringfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Login Controller.
 */
public class LoginController {
    @FXML private TextField userId;
    @FXML private PasswordField password;
    @FXML private Label errorMessage;
    
    @FXML protected void processLogin(ActionEvent event) {
        if(!TutoringFx.getInstance().userLogin(userId.getText(), password.getText())){
            errorMessage.setText("Invalid username or password: " + userId.getText());
        }
    }
}
