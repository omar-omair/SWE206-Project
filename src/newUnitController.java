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
    private JFXRadioButton directorete;

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
    public void initialize() {

        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        if(edit) {
            editUnit.setVisible(true);
            newUnit.setVisible(false);
            addButtonL.setVisible(false);
            editButtonL.setVisible(true);
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
            }
            }
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

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z -> {
            if(name.getText() == "" || level.getSelectedToggle() == null || (!managment.isSelected() && !engineering.isSelected())) {
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

