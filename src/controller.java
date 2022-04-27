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

    @FXML
    private Text incorrectLogin;

    @FXML
    public void initialize() {
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
                    AnchorPane loader = (AnchorPane) FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
                    Stage stage = (Stage)(loginButton.getScene().getWindow());
                    stage.setScene(new Scene(loader));
                    incorrectLogin.setVisible(false);
                    stage.setTitle("  Portal");
                    stage.show();
                }
                else {
                    incorrectLogin.setVisible(true);
                }
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
