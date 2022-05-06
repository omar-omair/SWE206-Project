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
import javafx.stage.Stage;
import java.io.IOException;

public class employeeListController {
    // Main menu variables

   
    @FXML
    private JFXButton applicantsButton;

    @FXML
    private Button editButtonL;

    @FXML
    private Button editButtonUN;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button removeButtonL;

    @FXML
    private Button removeButtonUN;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private TableColumn<Employee, Job> job;

    @FXML
    private TableColumn<Employee, String> name;

    @FXML
    private TableColumn<Employee, String> id;
    
    @FXML
    private TableColumn<Employee, Integer> salary;

    @FXML
    private TableView<Employee> table;

    @FXML
    private TableColumn<Employee, Unit> unit;

    @FXML
    private Button infoButtonL;

    @FXML
    private Button infoButtonUN;

    protected static int index;

    protected static Employee employee;

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

        settingsButton.setOnMouseClicked(e -> {
            try {
                settingsMenuController.prevMenu = "employeeList.fxml";
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

        editButtonUN.setOnAction(e -> {
            try {
                changeScene(e, "empInfo.fxml");}
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        removeButtonUN.setOnAction(e -> {
            try {
            App.employeeList.remove(index);
            App.save(App.employeeList,"../empList.ser");
            App.employeeList = App.read(App.employeeList, "../empList.ser");
            table.setItems(FXCollections.observableArrayList(App.employeeList));
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        infoButtonUN.setOnAction(e -> {
            try {
                infoMenuController.employeeInfo = true;
                infoMenuController.interviewInfo = false;
                changeScene(e, "infoMenu.fxml");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        name.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        job.setCellValueFactory(new PropertyValueFactory<Employee, Job>("job"));
        unit.setCellValueFactory(new PropertyValueFactory<Employee, Unit>("unit"));
        id.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
        salary.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("salary"));
        name.setResizable(false);
        id.setResizable(false);
        job.setResizable(false);
        unit.setResizable(false);
        salary.setResizable(false);
        table.setItems(FXCollections.observableArrayList(App.employeeList));

        table.getSelectionModel().selectedItemProperty().addListener(t -> {
            employee = table.getSelectionModel().getSelectedItem();
            index = table.getSelectionModel().getSelectedIndex();
            if(employee != null) {
                removeButtonUN.setVisible(true);
                removeButtonL.setVisible(false);
                editButtonUN.setVisible(true);
                editButtonL.setVisible(false);
                infoButtonUN.setVisible(true);
                infoButtonL.setVisible(false);
            }
            else{
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
