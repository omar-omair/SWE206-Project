import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;

public class newAppController {

    @FXML
    private TextField EL;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private Button addButton;

    @FXML
    private Button addButtonL;

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXRadioButton female;

    @FXML
    private TextField id;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private JFXRadioButton male;

    @FXML
    private TextField name;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private TextField years;

    @FXML
    private ImageView back;

    @FXML
    private AnchorPane pane;
 
    @FXML
    private Label wrong;

    @FXML
    private Label success;

    @FXML
    public void initialize() {
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
            changeScene(e, "mainMenu.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "newAppMenu.fxml";
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

        addButton.setOnAction(e -> {
            String gender;
            if(male.isSelected()) {
                gender = "male";
            }
            else {
                gender = "female";
            }
            try {
            Boolean uniqueID = true;

            for(int i = 0; i < App.appList.size(); i++) {
                if(id.getText().equals(App.appList.get(i).getId())) {
                    uniqueID = false;
                }
            }

            for(int i = 0; i < App.employeeList.size(); i++) {
                if(id.getText().equals(App.employeeList.get(i).getId())) {
                    uniqueID = false;
                }
            }
            
            if(uniqueID) {
            App.appList.add(new Applicant(name.getText(), id.getText(), gender, EL.getText(), Integer.parseInt(years.getText())));
            App.save(App.appList,"../appList.ser");
            App.appList = App.read(App.appList, "../appList.ser");
            years.setText("");
            name.setText("");
            EL.setText("");
            id.setText("");
            Gender.selectToggle(null);
            success.setVisible(true);
            wrong.setVisible(false);}

            else {
                wrong.setVisible(true);
                success.setVisible(false);
                wrong.setText("Duplicate IDs are not allowed");
            }
        }
            catch(NumberFormatException ex) {
                wrong.setVisible(true);
                success.setVisible(false);
                wrong.setText("invalid input for years of experience");
                wrong.setLayoutX(wrong.getLayoutX() - 10);
            }

            catch (Exception ex) {
                success.setVisible(false);
                wrong.setVisible(true);
                wrong.setText(ex.getMessage());
            }
        });

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z -> {
            if(years.getText().equals("") || name.getText().equals("") || id.getText().equals("") || 
                EL.getText().equals("") || (!male.isSelected() && !female.isSelected())) {
                
                addButtonL.setVisible(true);
                addButton.setVisible(false);
            }
            else {
                addButtonL.setVisible(false);
                addButton.setVisible(true);
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