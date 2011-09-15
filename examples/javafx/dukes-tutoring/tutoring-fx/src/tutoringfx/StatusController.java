/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package tutoringfx;

import dukestutoring.entity.Guardian;
import dukestutoring.entity.Student;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tutoringfx.adapter.StatusClient;

/**
 *
 * @author ievans
 */
public class StatusController implements Initializable {
    @FXML private Label userLabel;
    @FXML private Label userId;
    @FXML private Button logoutButton;
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, String> studentNameColumn;
    @FXML private TableColumn<Student, String> statusColumn;
    private Guardian guardian;
    
    private static final Logger logger = Logger.getLogger("StatusController");
    
    @Override public void initialize(URL location, ResourceBundle resources) {
        logoutButton.setText("logout");
        
        guardian = TutoringFx.getInstance().getGuardian();
        logger.log(Level.INFO, "Guardian is {0}", guardian.getName());
        userId.setText(guardian.getName());
        
        List<Student> studentList = guardian.getStudents();
        logger.log(Level.INFO, "Guardian {0} has {1} students.", new Object[]{guardian.getName(), studentList.size()});
        ObservableList<Student> tableData = FXCollections.observableArrayList(studentList);
        logger.log(Level.INFO, "ObservableList tableData has {0} elements.", tableData.size());
        table.setItems(tableData);
    }
    
    @FXML protected void refresh(ActionEvent event) {
        logger.info("Refreshing table data.");
        table.setItems(null);
        table.setItems(this.updateData());
    }
    
    @FXML protected void logout(ActionEvent event) {
        TutoringFx.getInstance().userLogout();
    }
    
    private ObservableList<Student> updateData() {
        StatusClient client = new StatusClient();
        List<Student> studentList = (List<Student>) client.getStatusByGuardianEmail(guardian.getEmail());
        client.close();
        return FXCollections.observableArrayList(studentList);
    }
}
