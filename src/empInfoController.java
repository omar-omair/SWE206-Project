import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    @FXML
    private Label wrong;

    private Employee employee = App.employeeList.get(employeeListController.index);

    @FXML
    private Text fullNameLabel;
    
    @FXML
    private ImageView femaleUser;

    @FXML
    public void initialize() {
        fullNameLabel.setText(controller.accountFullName);
        int oldYears = employee.getYearsOfExperience();
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

        if(controller.isFemale) {
            femaleUser.setVisible(true);
        }

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
                changeScene(e, "employeeList.fxml");
                employee.setYearsOfExperience(oldYears);}
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
            if(!employee.getUnit().getName().equals(nUnit.getName())) {
            if(nUnit.getAvailableSpots() > 0) {
            employee.setYearsOfExperience(Integer.parseInt(years.getText()));
            for(int i = 0; i < App.unitList.size(); i++) {
                if(employee.getUnit().getName().equals(App.unitList.get(i).getName())) {
                    App.unitList.get(i).removeEmployee(employee);
                }
                else if(nUnit.getName().equals(App.unitList.get(i).getName())) {
                    App.unitList.get(i).addEmployee(employee);
                }
            }
            employee.setUnit(nUnit);}

            employee.setSalary((int) salarySlider.getValue());
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

        for(int i = 0; i < jobBox.getItems().size(); i++) {
            if((employee.getJob().getName().equals(jobBox.getItems().get(i).getName()))) {
                jobIndex = i;
                break;
            }
        }
        
        unitBox.getSelectionModel().select(unitIndex);
        jobBox.getSelectionModel().select(jobIndex);

        salarySlider.visibleProperty().addListener(t-> {
            if(salarySlider.isVisible() == true) {
                salarySlider.setMin((int) (employee.getSalary() - (employee.getSalary() * 0.10)));
                salarySlider.setMax(employee.calculateMaxSalary(employee.getJob(), employee.getUnit()));
                salarySlider.setValue(employee.getSalary());
            }
        });

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z -> {
            if(name.getText().equals("") || years.getText().equals("") || unitBox.getSelectionModel().getSelectedItem() == null 
            || jobBox.getSelectionModel().getSelectedItem() == null || wrong.isVisible() == true) {
                editButtonL.setVisible(true);
                editButtonUN.setVisible(false);
                salarySlider.setVisible(false);

            }
            else {
                editButtonL.setVisible(false);
                editButtonUN.setVisible(true);
                salarySlider.setVisible(true);
            }
        }));

        years.textProperty().addListener(z-> {
            try {
            if(!years.getText().equals("")) {
                employee.setYearsOfExperience(Integer.parseInt(years.getText()));
                wrong.setVisible(false);
            }
            if(!years.getText().equals("") && Integer.parseInt(years.getText()) < oldYears) {
                wrong.setText("putting less years than what the employee started with isn't allowed");
                wrong.setVisible(true);
            }
            }
            catch (Exception ex) {
                wrong.setText("Invalid input for years of experience please try Again.");
                wrong.setVisible(true);
            }
        });

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

