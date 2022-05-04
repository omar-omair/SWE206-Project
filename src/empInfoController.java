import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.animation.KeyFrame;

public class empInfoController {

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private ImageView back;

    @FXML
    private Button editButtonL;

    @FXML
    private Button editButtonUN;

    @FXML
    private Label editInter;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private TextField years;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private JFXComboBox<Job> jobBox;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXComboBox<Unit> unitBox;

    @FXML
    private JFXButton unitsButton;
    
    @FXML
    private JFXSlider salarySlider;

    private Employee employee = App.employeeList.get(employeeListController.index);

    @FXML
    public void initialize() {

        unitBox.setItems(FXCollections.observableArrayList(App.unitList));
        unitBox.getSelectionModel().selectedItemProperty().addListener(z-> {
            ArrayList<Band> bands = unitBox.getSelectionModel().getSelectedItem().getJobBands();
            ArrayList<Job> jobs = new ArrayList<>();

            for(int i = 0; i < bands.size(); i++ ) {
                if(bands.get(i).getName().equalsIgnoreCase("Project Management")) {
                    jobs.addAll(bands.get(i).getJobs());
                }
                if(bands.get(i).getName().equalsIgnoreCase("Engineering")) {
                    jobs.addAll(bands.get(i).getJobs());
                }
            }

            jobBox.setItems(FXCollections.observableArrayList(jobs));
        });

        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
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
                changeScene(e, "employeeList.fxml");}
                catch (Exception ex) {
                 ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "employeeList.fxml";
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

        employeesButton.setOnAction(e -> {
            try {
                changeScene(e, "employeeList.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        editButtonUN.setOnAction(e -> {
            try {
            Unit nUnit = unitBox.getSelectionModel().getSelectedItem();
            if(nUnit.checkAvailability() > 0) {
            employee.setYearsOfExperience(Integer.parseInt(years.getText()));
            employee.getUnit().removeEmployee(employee);
            employee.setUnit(nUnit);
            employee.getUnit().addEmployee(employee);
            App.save(App.employeeList, "../empList.ser");
            App.employeeList = App.read(App.employeeList, "../empList.ser");
            changeScene(e, "employeeList.fxml");
            }

            
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        name.setText(employee.getName());
        years.setText(Integer.toString(employee.getYearsOfExperience()));
        int unitIndex = 0;
        int jobIndex = 0;

        for(int i=0; i< App.unitList.size(); i++) {
            if(employee.getUnit().getName().equals(App.unitList.get(i).getName())) {
                unitIndex = i;
                break;
            }
        }

        for(int i = 0; i < App.engineering.getJobs().size(); i++) {
            if((App.engineering.getJobs().get(i) != null && employee.getJob().getName().equals(App.engineering.getJobs().get(i).getName()))
            || (App.engineering.getJobs().get(i) != null && employee.getJob().getName().equals(App.management.getJobs().get(i).getName()))) {
                jobIndex = i;
                break;
            }
        }
        
        unitBox.getSelectionModel().select(unitIndex);
        jobBox.getSelectionModel().select(jobIndex);

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z -> {
            if(name.equals("") || years.equals("") || unitBox.getSelectionModel().getSelectedItem() == null 
            || jobBox.getSelectionModel().getSelectedItem() == null) {
                editButtonL.setVisible(true);
                editButtonUN.setVisible(false);
                salarySlider.setVisible(false);

            }
            else {
                editButtonL.setVisible(false);
                editButtonUN.setVisible(true);
                salarySlider.setVisible(true);
                salarySlider.setMin(employee.getSalary() - (employee.getSalary() * 0.15));
                salarySlider.setMax(employee.calculateSalary(employee.getJob(), employee.getUnit()));
            }
        }));

        valueChecker.setCycleCount(Timeline.INDEFINITE);
        valueChecker.play();
    }

    
    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }

   

}

