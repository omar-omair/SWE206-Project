import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class appListController {
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
    private Button jobButtonL;

    @FXML
    private Button jobButtonUN;

    @FXML
    private Button offerButtonL;

    @FXML
    private Button offerButtonUN;

    public void initialize() {
        offerButtonL.setOnAction(e -> {
            try {
                AnchorPane loader = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("jobOfferMenu.fxml"));
                Stage stage = (Stage)(offerButtonUN.getScene().getWindow());
                stage.setScene(new Scene(loader));
                stage.show();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void changeToMain(ActionEvent event) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource("mainMenu.fxml"));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
        
    }
}
