import java.io.IOException;

import com.jfoenix.controls.JFXButton;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
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
    private Label genderLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField job;

    private Applicant applicant;

    private Employee employee;

    private Interview interview;

    private Unit unit;

    protected static Boolean employeeInfo = false;

    protected static Boolean interviewInfo = false;

    protected static boolean unitInfo = false;

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

        else if(interviewInfo) {
            interview = App.interList.get(interviewListController.index);
            infoLabel.setText("Interview info");
            nameLabel.setText("Interviewee's name");
            idLabel.setText("First interviewer's name");
            eduLabel.setText("Second interviewer's name");
            yearsLabel.setText("Third interviewer's name");
            genderLabel.setText("Date");
            statusLabel.setText("Result");
            eduLabel.setFont(new Font(10.5));
            yearsLabel.setFont(new Font(11));
            name.setText(interview.getInterviewee().getName());
            id.setText(interview.getInterviewersNames().get(0));
            EL.setText(interview.getInterviewersNames().get(1));
            years.setText(interview.getInterviewersNames().get(2));
            gender.setText(interview.getDate() + " at " + interview.getTime());
            status.setText(interview.getResult());
            toPDF.setVisible(false);

        }

        else if(unitInfo){
            unit = App.unitList.get(unitsListController.index);
            infoLabel.setText("Unit Info");
            nameLabel.setText("Unit name");
            idLabel.setText("Level");
            eduLabel.setText("Capacity");
            yearsLabel.setText("available spots");
            genderLabel.setText("Superior unit");
            statusLabel.setVisible(false);
            status.setVisible(false);
            toPDF.setVisible(false);
            name.setText(unit.getName());
            EL.setText(Integer.toString(unit.getUnitCapacity()));
            years.setText(Integer.toString(unit.getAvailableSpots()));
            gender.setText(unit.getSuperior());
            id.setText(unit.getLevel());
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
            else if(interviewInfo) {changeScene(e, "interList.fxml");}
            else if(unitInfo) {changeScene(e, "unitList.fxml");}
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
