import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;

public class interviewMenuController {

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private ImageView interListButton;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private ImageView newInterButton;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton unitsButton;
    
    @FXML
    public void initialize() {

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

        interListButton.setOnMouseClicked(e -> {
            try {
           changeScene(e, "interList.fxml");}
           catch (Exception ex) {
            ex.printStackTrace();
           }
        });

        newInterButton.setOnMouseClicked(e -> {
            try {
           newInterMenuController.edit = false;     
           changeScene(e, "newInterMenu.fxml");}
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