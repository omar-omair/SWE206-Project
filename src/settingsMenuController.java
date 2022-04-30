import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Node;

public class settingsMenuController {
    
    @FXML
    private ImageView back;

    @FXML
    private JFXToggleButton darkSwitch;

    @FXML
    private Button offerButtonUN;

    @FXML
    private Button removeButtonUN;

    @FXML
    private Label signOutButton;

    protected static boolean dark = false;
    
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView moonIcon;

    @FXML
    private ImageView signOutIcon;

    protected static String prevMenu = "";

    @FXML
    public void initialize() {
        if(dark == true) {
            darkSwitch.setSelected(true);
            darkSwitch.setText("ON");
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        darkSwitch.setOnAction( e -> {
            if(dark == false) {
                dark = true;
                darkSwitch.setText("ON");
                pane.getStylesheets().remove("style.css");
                pane.getStylesheets().add("styleDark.css");
            }
            else {
                dark = false;
                darkSwitch.setText("OFF");
                pane.getStylesheets().remove("styleDark.css");
                pane.getStylesheets().add("style.css");
            }
        });

        signOutButton.setOnMouseClicked(e -> {
            try {
            changeScene(e, "login.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        back.setOnMouseClicked(e -> {
            try {
            changeScene(e, prevMenu);}
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