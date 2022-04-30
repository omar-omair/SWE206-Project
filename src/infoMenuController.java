import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
            changeScene(e, "appList.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                changeScene(e, "settingsMenu.fxml");}
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
