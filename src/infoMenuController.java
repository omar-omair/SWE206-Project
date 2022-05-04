import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

public class infoMenuController {

    @FXML
    private TextField EL;

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private ImageView back;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private TextField gender;

    @FXML
    private TextField id;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private TextField name;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private TextField status;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private TextField years;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button toPDF;

    @FXML
    private Label eduLabel;

    @FXML
    private Label infoLabel;
    
    @FXML
    private Label jobLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label yearsLabel;

    @FXML
    private TextField job;

    private Applicant applicant;

    private Employee employee;

    protected static Boolean employeeInfo = false;

    @FXML
    public void initialize() {
        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        if(employeeInfo) {
            employee = App.employeeList.get(employeeListController.index);
            eduLabel.setText("Unit");
            infoLabel.setText("Employee info");
            statusLabel.setText("salary");
            jobLabel.setVisible(true);
            job.setVisible(true);
            EL.setText(employee.getUnit().toString());
            name.setText(employee.getName());
            id.setText(employee.getId());
            years.setText(Integer.toString(employee.getYearsOfExperience()));
            gender.setText(employee.getGender());
            status.setText(Integer.toString(employee.getSalary()));
            job.setText(employee.getJob().toString());

        }

        else {
            applicant = App.appList.get(appListController.index);
            name.setText(applicant.getName());
            id.setText(applicant.getId());
            years.setText(Integer.toString(applicant.getYearsOfExperience()));
            EL.setText(applicant.getEducationLevel());
            gender.setText(applicant.getGender());
            status.setText(applicant.getStatus());
        }

        applicantsButton.setOnAction(e -> {
            try {
           changeScene(e, "mainMenu.fxml");}
           catch (Exception ex) {
            ex.printStackTrace();
           }
        });

        interviewsButton.setOnAction(e -> {
            try {
           changeScene(e, "interviewMenu.fxml");}
           catch (Exception ex) {
            ex.printStackTrace();
           }
        });

        back.setOnMouseClicked(e -> {
            try {
            changeScene(e, "appList.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "appList.fxml";
                changeScene(e, "settingsMenu.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        unitsButton.setOnAction(e -> {
            try {
                changeScene(e, "unitsMenu.fxml");}
                catch (Exception ex) {
                 ex.printStackTrace();
                }
        });

        
        toPDF.setOnAction(e -> {
            try {
            if(employeeInfo) {employee.toPDF();}
            else {applicant.toPDF();}}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        back.setOnMouseClicked(e -> {
            try {
            if(employeeInfo) {changeScene(e, "employeeList.fxml");}
            else{changeScene(e, "appList.fxml");}}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        employeesButton.setOnAction(e -> {
            try {
                changeScene(e, "employeeList.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }); 

    }
    
    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
