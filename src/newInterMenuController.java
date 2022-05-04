import java.io.IOException;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class newInterMenuController {

    @FXML
    protected Button addButton;

    @FXML
    protected Button addButtonL;

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private ImageView back;

    @FXML
    private Button editButton;

    @FXML
    private Button editButtonL;

    @FXML
    protected Label editInter;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXComboBox<Employee> firstInterviewer;

    @FXML
    private JFXComboBox<Applicant> interviewee;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private Label newInter;

    @FXML
    private JFXComboBox<String> result;

    @FXML
    private JFXComboBox<Employee> secondInterviewer;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXComboBox<Employee> thirdInterviewer;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField time;

    protected static boolean edit = false;
    
    @FXML
    public void initialize() {

        if(settingsMenuController.dark == true) {
            pane.getStylesheets().remove("style.css");
            pane.getStylesheets().add("styleDark.css");
        }

        if(edit) {
            editInter.setVisible(true);
            newInter.setVisible(false);
            addButtonL.setVisible(false);
            editButtonL.setVisible(true);
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
            if(!edit) {
            changeScene(e, "interviewMenu.fxml");}
            else {
            changeScene(e, "interList.fxml");
            }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        settingsButton.setOnMouseClicked(e -> {
            try {
                if(edit) {
                    settingsMenuController.prevMenu = "interList.fxml";
                }
                else {
                    settingsMenuController.prevMenu = "newInterMenu.fxml";
                }
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

        addButton.setOnAction(e -> {
            try {
            Employee firstInter = firstInterviewer.getSelectionModel().getSelectedItem();
            Employee secondInter = secondInterviewer.getSelectionModel().getSelectedItem();
            Employee thirdInter = thirdInterviewer.getSelectionModel().getSelectedItem();

            Interview interview = new Interview(date.getValue().toString(),time.getText());
            interview.addInterviewer(firstInter);
            if(secondInter != null) {
                interview.addInterviewer(secondInter);
            }
            if(thirdInter != null) {
                interview.addInterviewer(thirdInter);
            }

            interview.setFirstInterviewerName();
            interview.setSecondInterviewerName();
            interview.setThirdInterviewerName();
            interview.setInterviewee(interviewee.getSelectionModel().getSelectedItem());
            App.interList.add(interview);
            App.save(App.interList,"../interList.ser");
            App.interList = App.read(App.interList,"../interList.ser");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Callback<DatePicker, DateCell> callB = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        LocalDate today = LocalDate.now();
                        setDisable(empty || item.compareTo(today) < 0);
                    }

                };
            }

        };

        date.setDayCellFactory(callB);

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z -> {
            if(interviewee.getSelectionModel().getSelectedItem() == null || firstInterviewer.getSelectionModel().getSelectedItem() == null || 
            result.getSelectionModel().getSelectedItem() == null || date.getValue() != null || time.getText().equals("")) { 
                if(edit) {
                    editButtonL.setVisible(true);
                    editButton.setVisible(false);
                }
                else {
                    addButtonL.setVisible(true);
                    addButton.setVisible(false);
                }
            }
            else {
                if(edit) {
                    editButtonL.setVisible(false);
                    editButton.setVisible(true);
                }
                else {
                    addButtonL.setVisible(false);
                    addButton.setVisible(true);
                }
            }

            if(firstInterviewer.getSelectionModel().getSelectedItem() == null) {
                secondInterviewer.setEditable(false);
            }
            else {
                secondInterviewer.setEditable(true);
            }

            if(secondInterviewer.getSelectionModel().getSelectedItem() == null) {
                thirdInterviewer.setEditable(false);
            }
            else {
                thirdInterviewer.setEditable(true);
            }
        }));
        valueChecker.play();
        valueChecker.setCycleCount(Timeline.INDEFINITE);
    }

    
    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }

   

}

