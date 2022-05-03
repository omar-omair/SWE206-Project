import java.io.IOException;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.util.Duration;

public class newUnitController {
    @FXML
    private Button addButtonL;

    @FXML
    private Button addButtonUN;

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private ImageView back;

    @FXML
    private JFXRadioButton department;

    @FXML
    private JFXRadioButton directorate;

    @FXML
    private JFXRadioButton division;

    @FXML
    private Button editButtonL;

    @FXML
    private Button editButtonUN;

    @FXML
    private Label editUnit;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXCheckBox engineering;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private ToggleGroup level;

    @FXML
    private JFXCheckBox managment;

    @FXML
    private TextField name;

    @FXML
    private Label newUnit;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;
    
    protected static boolean edit = false;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private TextField capacity;

    @FXML
    private Label wrong;

    @FXML
    private Label success;


    protected static Unit unit;

    @FXML
    public void initialize() {

        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        if(edit) {
            editUnit.setVisible(true);
            newUnit.setVisible(false);
            addButtonL.setVisible(false);
            editButtonUN.setVisible(true);
            unit = App.unitList.get(unitsListController.index);
            name.setText(unit.getName());
            capacity.setText(Integer.toString(unit.getUnitCapacity()));
            
            if(unit instanceof Department) {
                department.setSelected(true);
            }
            else if(unit instanceof Division) {
                division.setSelected(true);
            }
            else {
                directorate.setSelected(true);
            }

            for(int i = 0; i < unit.getJobBands().size(); i++) {
                if(unit.getJobBands().get(i).getName().equalsIgnoreCase("Project Management")) {
                    managment.setSelected(true);
                    managment.setDisable(true);
                }
                else if(unit.getJobBands().get(i).getName().equalsIgnoreCase("Engineering")) {
                    engineering.setSelected(true);
                    engineering.setDisable(true);
                }
            }

        }
        else {
            editUnit.setVisible(false);
            newUnit.setVisible(true);
            addButtonL.setVisible(true);
            editButtonL.setVisible(false);
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
            if(!edit) {
            changeScene(e, "unitsMenu.fxml");}
            else {
            changeScene(e, "unitList.fxml");
            }}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                if(edit) {
                    settingsMenuController.prevMenu = "unitList.fxml";
                }
                else {
                    settingsMenuController.prevMenu = "newUnitMenu.fxml";
                }
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
                String levelPicked = ((JFXRadioButton) level.getSelectedToggle()).getText();
                unit.setName(name.getText());
                unit.setUnitCapacity(Integer.parseInt(capacity.getText()));
                if(engineering.isDisabled() == false && engineering.isSelected() == true) {
                    unit.addJobBand(App.engineering);
                }
                if(managment.isDisabled() == false && managment.isSelected() == true) {
                    unit.addJobBand(App.management);
                }
                if(!(levelPicked.equals(unit.getLevel()))) {
                    if(levelPicked.equals("Division")) {
                        App.unitList.set(unitsListController.index, unit.changeToDivision());
                    }
                    else if(levelPicked.equals("Department")) {
                        App.unitList.set(unitsListController.index, unit.changeToDepartment());
                    }
                    else {
                        App.unitList.set(unitsListController.index, unit.changeToDirectorate());
                        
                    }
                }

                App.save(App.unitList, "../unitList.ser");
                changeScene(e, "unitList.fxml");
                
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        addButtonUN.setOnAction(e -> {
            try {
                Unit nUnit;

                if(division.isSelected()){
                    nUnit = new Division(name.getText(),Integer.parseInt(capacity.getText()));
                }
                else if(directorate.isSelected()){
                    nUnit = new Directorate(name.getText(),Integer.parseInt(capacity.getText()));
                }
                else{
                    nUnit = new Department(name.getText(),Integer.parseInt(capacity.getText()));
                }
                if(managment.isSelected()) {
                    nUnit.addJobBand(App.management);
                }
                if(engineering.isSelected()) {
                    nUnit.addJobBand(App.engineering);
                }
                App.unitList.add(nUnit);
                App.save(App.unitList,"../unitList.ser");
                App.unitList = App.read(App.unitList, "../unitList.ser");
                success.setVisible(true);
                wrong.setVisible(false);

                name.setText("");
                capacity.setText("");
                level.selectToggle(null);
                engineering.setSelected(false);
                managment.setSelected(false);
            }
            catch (Exception ex) {
                success.setVisible(false);
                wrong.setVisible(true);
            }
        });

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z -> {
            if(name.getText().equals("") || capacity.getText().equals("") || level.getSelectedToggle() == null || (!managment.isSelected() && !engineering.isSelected())) {
                if(edit) {
                    editButtonL.setVisible(true);
                    editButtonUN.setVisible(false);
                }
                else {
                    addButtonL.setVisible(true);
                    addButtonUN.setVisible(false);
                }
            }
            else {
                if(edit) {
                    editButtonL.setVisible(false);
                    editButtonUN.setVisible(true);
                }
                else {
                    addButtonL.setVisible(false);
                    addButtonUN.setVisible(true);
                }
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

