import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class unitsListController {
    // Main menu variables

    @FXML
    private JFXButton applicantsButton;

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
    private Button editButtonL;

    @FXML
    private Button editButtonUN;

    @FXML
    private ImageView back;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<Unit, String> level;

    @FXML
    private TableColumn<Unit, String> name;

    @FXML
    private TableView<Unit> table;



    public void initialize() {
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

        back.setOnMouseClicked(e -> {
            try {
            changeScene(e, "unitsMenu.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "unitList.fxml";
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

        editButtonL.setOnMouseClicked(e -> {
            try {
                newUnitController.edit = true;
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

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        name.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        level.setCellValueFactory(new PropertyValueFactory<Unit, String>("level"));
        table.setItems(FXCollections.observableArrayList(App.unitList));

    }

    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
