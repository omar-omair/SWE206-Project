import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private ImageView back;

    @FXML
    private Button infoButtonL;

    @FXML
    private Button infoButtonUN;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<Applicant> table;

    @FXML
    private TableColumn<Applicant, String> status;

    @FXML
    private TableColumn<Applicant, String> name;

    @FXML
    private TableColumn<Applicant, String> id;

    protected static ObservableList<Applicant> appList = FXCollections.observableArrayList(App.appList);

    public void initialize() {
        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        offerButtonL.setOnAction(e -> {
            try {
           changeScene(e, "jobOfferMenu.fxml");}
           catch (Exception ex) {
            ex.printStackTrace();
           }
        });

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
            changeScene(e, "mainMenu.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        infoButtonL.setOnAction(e -> {
            try {
           changeScene(e, "infoMenu.fxml");}
           catch (Exception ex) {
            ex.printStackTrace();
           }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "appList.fxml";
                changeScene(e, "settingsMenu.fxml");}
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

        employeesButton.setOnAction(e -> {
            try {
                changeScene(e, "employeeList.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        name.setCellValueFactory(new PropertyValueFactory<Applicant, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Applicant, String>("id"));
        status.setCellValueFactory(new PropertyValueFactory<Applicant, String>("status"));
        table.setItems(appList);

    }

    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
