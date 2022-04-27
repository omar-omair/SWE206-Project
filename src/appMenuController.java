import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class appMenuController {
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
    private ImageView appListButton;

    @FXML
    public void initialize() {
         appListButton.setOnMouseClicked(e -> {
            try {    
            AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource("appList.fxml"));
            Stage stage = (Stage)(appListButton.getScene().getWindow());
            stage.setScene(new Scene(loader));
            stage.show();
            }  
            catch(IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}