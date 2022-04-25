import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class controller {

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

    }
}
