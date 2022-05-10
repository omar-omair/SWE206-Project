import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private Button infoButtonL;

    @FXML
    private Button infoButtonUN;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<Unit, String> level;

    @FXML
    private TableColumn<Unit, String> name;

    @FXML
    private TableColumn<Unit, Integer> spots;

    @FXML
    private TableColumn<Unit, String> superior;

    @FXML
    private TableView<Unit> table;

    @FXML
    private Button removeButtonL;

    @FXML
    private Button removeButtonUN;
    
    @FXML
    private ImageView femaleUser;

    @FXML
    private Text fullNameLabel;

    protected static int index;

    public void initialize() {
        fullNameLabel.setText(controller.accountFullName);
        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        if(controller.isFemale) {
            femaleUser.setVisible(true);
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

        editButtonUN.setOnMouseClicked(e -> {
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

        infoButtonUN.setOnAction(e -> {
            try {
                infoMenuController.employeeInfo = false;
                infoMenuController.unitInfo = true;
                infoMenuController.interviewInfo = false;
                changeScene(e, "infoMenu.fxml");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        removeButtonUN.setOnAction(e -> {
            try {
            App.unitList.remove(index);
            App.save(App.unitList,"../unitList.ser");
            App.unitList = App.read(App.unitList,"../unitList.ser");
            table.setItems(FXCollections.observableArrayList(App.unitList));}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        name.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        level.setCellValueFactory(new PropertyValueFactory<Unit, String>("level"));
        superior.setCellValueFactory(new PropertyValueFactory<Unit,String>("superior"));
        spots.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("availableSpots"));
        table.setItems(FXCollections.observableArrayList(App.unitList));

        table.getSelectionModel().selectedItemProperty().addListener(t -> {
            Unit picked = table.getSelectionModel().getSelectedItem();
            index = table.getSelectionModel().getSelectedIndex();
            
            if(picked != null) {
                if(picked.getEmployees().size() == 0) {
                    removeButtonUN.setVisible(true);
                    removeButtonL.setVisible(false);
                }
                else {
                    removeButtonUN.setVisible(false);
                    removeButtonL.setVisible(true);
                }
                editButtonL.setVisible(false);
                editButtonUN.setVisible(true);
                infoButtonUN.setVisible(true);
                infoButtonL.setVisible(false);
            }
            else {
                removeButtonUN.setVisible(false);
                removeButtonL.setVisible(true);
                editButtonL.setVisible(true);
                editButtonUN.setVisible(false);
                infoButtonUN.setVisible(false);
                infoButtonL.setVisible(true);
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
