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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class controller {

    //login Page variables

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField passwordText;

    @FXML
    private JFXTextField userText;

    @FXML
    private ImageView eyeHidden;

    @FXML
    private ImageView eyeShow;

    @FXML
    private JFXTextField passwordReveal;

    @FXML
    private Button loginButton;

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
    void initialize() {

        eyeHidden.setOnMouseClicked(e -> {
            passwordReveal.setText(passwordText.getText());
            passwordReveal.setVisible(true);
            passwordText.setVisible(false);
            eyeShow.setVisible(true);
            eyeHidden.setVisible(false);
        });

        eyeShow.setOnMouseClicked(e -> {
            passwordText.setText(passwordReveal.getText());
            passwordReveal.setVisible(false);
            passwordText.setVisible(true);
            eyeShow.setVisible(false);
            eyeHidden.setVisible(true);
        });

        
        loginButton.setOnMouseClicked(e -> {
            try {
                if (userText.getText().equalsIgnoreCase("Ahmed12") && 
                (passwordText.getText().equalsIgnoreCase("123") || passwordReveal.getText().equalsIgnoreCase("123"))) {
                    Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                    Stage stage = (Stage)(loginButton.getScene().getWindow());
                    stage.setScene(new Scene(root));
                }
                else {
                    
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        });

    }
}
