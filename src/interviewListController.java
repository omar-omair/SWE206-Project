import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;


public class interviewListController {

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private Button editButtonL;

    @FXML
    private Button editButtonUN;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXListView<?> interList;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private Button removeButtonL;

    @FXML
    private Button removeButtonUN;

    @FXML
    private Button infoButtonL;

    @FXML
    private Button infoButtonUN;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private ImageView back;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<Interview, String> appName;

    @FXML
    private TableColumn<Interview, String> result;

    @FXML
    private TableColumn<Interview, String> date;

    @FXML
    private TableColumn<Interview, String> mainInterviewer;

    @FXML
    private TableColumn<Interview, String> time;

    @FXML
    private TableView<Interview> table;

    protected static Interview interview;

    protected static int index;

    @FXML
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
            changeScene(e, "interviewMenu.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        editButtonUN.setOnMouseClicked(e -> {
            try {
           newInterMenuController.edit = true;
           changeScene(e, "newInterMenu.fxml");
            }
           catch (Exception ex) {
            ex.printStackTrace();
           }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "interList.fxml";
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

        removeButtonUN.setOnAction(e -> {
            try {
            App.interList.remove(index);
            App.save(App.interList,"../interList.ser");
            App.interList = App.read(App.interList, "../interList.ser");
            table.setItems(FXCollections.observableArrayList(App.interList));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        infoButtonUN.setOnAction(e -> {
            try {
                infoMenuController.employeeInfo = false;
                infoMenuController.interviewInfo = true;
                changeScene(e, "infoMenu.fxml");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        result.setCellValueFactory(new PropertyValueFactory<Interview, String>("result"));
        date.setCellValueFactory(new PropertyValueFactory<Interview, String>("date"));
        appName.setCellValueFactory(new PropertyValueFactory<Interview, String>("interviewee"));
        mainInterviewer.setCellValueFactory(new PropertyValueFactory<Interview, String>("firstInterviewerName"));
        time.setCellValueFactory(new PropertyValueFactory<Interview, String>("time"));
        table.setItems(FXCollections.observableArrayList(App.interList));

        table.getSelectionModel().selectedItemProperty().addListener(z-> {
            interview = table.getSelectionModel().getSelectedItem();
            index = table.getSelectionModel().getSelectedIndex();
            if(interview != null) {
                removeButtonUN.setVisible(true);
                removeButtonL.setVisible(false);
                editButtonUN.setVisible(true);
                editButtonL.setVisible(false);
                infoButtonUN.setVisible(true);
                infoButtonL.setVisible(false);
            }
            else {
                removeButtonUN.setVisible(false);
                removeButtonL.setVisible(true);
                editButtonUN.setVisible(false);
                editButtonL.setVisible(true);
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