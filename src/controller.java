import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

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
    private Text incorrectLogin;

    private HashMap<String,String[]> accounts;

    protected static String accountFullName;

    protected static boolean isFemale = false;

    @FXML
    public void initialize() {
        try {
        FileInputStream fis = new FileInputStream("../accounts.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        accounts = (HashMap<String, String[]>) ois.readObject();
        ois.close();}
        
        catch (Exception ex) {
            ex.printStackTrace();
        }

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
                boolean correctInfo = false;
                
                for(Entry<String,String[]> entry: accounts.entrySet()) {
                    if(userText.getText().equalsIgnoreCase(entry.getKey()) && 
                    (passwordText.getText().equalsIgnoreCase(entry.getValue()[0]) || passwordReveal.getText().equalsIgnoreCase(entry.getValue()[0]))) {
                        accountFullName = entry.getValue()[1];
                        correctInfo = true;

                        if( entry.getValue().length > 2 && entry.getValue()[2].equalsIgnoreCase("F")) {
                            isFemale = true;
                        }
                        else {
                            isFemale = false;
                        }
                    }
                }

                if (correctInfo) {
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
