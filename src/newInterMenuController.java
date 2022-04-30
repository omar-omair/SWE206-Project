import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

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

public class newInterMenuController {

    @FXML
    protected Button addButton;

    @FXML
    protected Button addButtonL;

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private ImageView back;

    @FXML
    private Button editButton;

    @FXML
    private Button editButtonL;

    @FXML
    protected Label editInter;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXComboBox<?> firstInterviewer;

    @FXML
    private JFXComboBox<?> interviewee;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private Label newInter;

    @FXML
    private JFXComboBox<?> result;

    @FXML
    private JFXComboBox<?> secondInterviewer;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXComboBox<?> thirdInterviewer;

    @FXML
    private JFXButton unitsButton;

    protected static boolean edit = false;
    
    @FXML
    public void initialize() {

        if(edit) {
            editInter.setVisible(true);
            newInter.setVisible(false);
            addButtonL.setVisible(false);
            editButtonL.setVisible(true);
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
            changeScene(e, "interviewMenu.fxml");}
            else {
            changeScene(e, "interList.fxml");
            }
            }
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

