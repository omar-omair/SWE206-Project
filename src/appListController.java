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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
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

    @FXML
    private Button removeButtonL;

    @FXML
    private Button removeButtonUN;

    @FXML
    private Label wrong;

    protected static int index;

    protected static Applicant applicant;

    public void initialize() {
        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        offerButtonUN.setOnAction(e -> {
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

        infoButtonUN.setOnAction(e -> {
            try {
           infoMenuController.employeeInfo = false;
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

        removeButtonUN.setOnAction(e -> {
            try {
                remove();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    
        jobButtonUN.setOnAction(e -> {
            try {
            if(applicant.checkFeasibility() == true) {
            Employee employee = applicant.assignJob();
            App.employeeList.add(employee);
            App.save(App.employeeList,"../empList.ser");
            App.employeeList = App.read(App.employeeList, "../empList.ser");
            remove();
            }
            else {
                wrong.setVisible(true);
            }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        name.setCellValueFactory(new PropertyValueFactory<Applicant, String>("name"));
        id.setCellValueFactory(new PropertyValueFactory<Applicant, String>("id"));
        status.setCellValueFactory(new PropertyValueFactory<Applicant, String>("status"));
        table.setItems(FXCollections.observableArrayList(App.appList));

        table.getSelectionModel().selectedItemProperty().addListener(t -> {
            applicant = table.getSelectionModel().getSelectedItem();
            index = table.getSelectionModel().getSelectedIndex();
            
            if(applicant != null && applicant.getOfferedSalary() > 0) {
                jobButtonUN.setVisible(true);
                jobButtonL.setVisible(false);
            }
            else {
                jobButtonUN.setVisible(false);
                jobButtonL.setVisible(true);
            }

            if(applicant != null) {
                infoButtonL.setVisible(false);
                infoButtonUN.setVisible(true);
                offerButtonL.setVisible(false);
                offerButtonUN.setVisible(true);
                removeButtonL.setVisible(false);
                removeButtonUN.setVisible(true);
            }

            else {
                infoButtonL.setVisible(true);
                infoButtonUN.setVisible(false);
                offerButtonL.setVisible(true);
                offerButtonUN.setVisible(false);
                removeButtonL.setVisible(true);
                removeButtonUN.setVisible(false);
            }
        });

    }

    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }

    void remove() throws Exception {
        App.appList.remove(index);
        App.save(App.appList,"../appList.ser");
        App.appList = App.read(App.appList,"../appList.ser");
        table.setItems(FXCollections.observableArrayList(App.appList));
    }

}
