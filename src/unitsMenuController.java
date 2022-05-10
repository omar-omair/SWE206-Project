import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;
public class unitsMenuController {
    // Main menu variables

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private ImageView closeButton;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView unitListButton;

    @FXML
    private ImageView newUnitButton;

    @FXML
    private Text fullNameLabel;

    @FXML
    public void initialize() {
        fullNameLabel.setText(controller.accountFullName);
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

        unitsButton.setOnAction(e -> {
            try {
                changeScene(e, "unitsMenu.fxml");}
                catch (Exception ex) {
                 ex.printStackTrace();
                }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "unitsMenu.fxml";
                changeScene(e, "settingsMenu.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        unitListButton.setOnMouseClicked(e -> {
            try {
                changeScene(e, "unitList.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        newUnitButton.setOnMouseClicked(e -> {
            try {
                newUnitController.edit = false;
                changeScene(e, "newUnitMenu.fxml");}
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