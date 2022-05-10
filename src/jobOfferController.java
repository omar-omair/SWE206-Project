import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.*;
import java.io.IOException;
import java.util.ArrayList;

public class jobOfferController {

    @FXML
    private TextField applicantName;

    @FXML
    private JFXButton applicantsButton;

    @FXML
    private Button createOfferButtonL;

    @FXML
    private Button createOfferUN;

    @FXML
    private JFXButton employeesButton;

    @FXML
    private JFXButton interviewsButton;

    @FXML
    private JFXComboBox<Job> jobBox;

    @FXML
    private JFXSlider salarySlider;

    @FXML
    private ImageView settingsButton;

    @FXML
    private AnchorPane slider;

    @FXML
    private JFXComboBox<Unit> unitBox;

    @FXML
    private JFXButton unitsButton;

    @FXML
    private ImageView back;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label wrong;

    @FXML
    private Text fullNameLabel;

    private Applicant applicant = App.appList.get(appListController.index);

    public void initialize() {
        unitBox.setItems(FXCollections.observableArrayList(App.unitList));
        fullNameLabel.setText(controller.accountFullName);
        unitBox.getSelectionModel().selectedItemProperty().addListener(z-> {
            ArrayList<Band> bands = unitBox.getSelectionModel().getSelectedItem().getJobBands();
            ArrayList<Job> jobs = new ArrayList<>();

            for(int i = 0; i < bands.size(); i++ ) {
                if(bands.get(i).getName().equalsIgnoreCase("Project Management")) {
                    jobs.addAll(bands.get(i).getJobs());
                }
                if(bands.get(i).getName().equalsIgnoreCase("Engineering")) {
                    jobs.addAll(bands.get(i).getJobs());
                }
            }

            jobBox.setItems(FXCollections.observableArrayList(jobs));
        });

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
            changeScene(e, "appList.fxml");}
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

        createOfferUN.setOnAction(e -> {
            try{
                applicant.setOfferedUnit(unitBox.getSelectionModel().getSelectedItem());
                
            if(applicant.createJobOffer() == true) {
                applicant.setOfferedSalary((int) salarySlider.getValue());
                applicant.setOfferedJob(jobBox.getSelectionModel().getSelectedItem());
                applicant.createJobOffer();
                App.save(App.appList,"../appList.ser");
                App.appList = App.read(App.appList, "../appList.ser");
                changeScene(e, "appList.fxml");
                }
            applicant.setOfferedUnit(null);
            applicant.setStatus("Hold");
            wrong.setVisible(true);
            }
            catch (Exception ex) {
                wrong.setVisible(true);
            }
        });

        applicantName.setText(applicant.getName());

        Timeline valueChecker = new Timeline(new KeyFrame(Duration.millis(1), z-> {
            if(unitBox.getSelectionModel().getSelectedItem() != null && jobBox.getSelectionModel().getSelectedItem() != null) {
                ArrayList<Integer> salary = applicant.calaculateSalary(jobBox.getSelectionModel().getSelectedItem(), unitBox.getSelectionModel().getSelectedItem());
                createOfferButtonL.setVisible(false);
                createOfferUN.setVisible(true);
                salarySlider.setMin(salary.get(0));
                salarySlider.setMax(salary.get(1));
                salarySlider.setVisible(true);
            }
            else {
                createOfferButtonL.setVisible(true);
                createOfferUN.setVisible(false);
                salarySlider.setVisible(false);
            }
        }));
        
        valueChecker.setCycleCount(Timeline.INDEFINITE);
        valueChecker.play();
    }
    
    void changeScene(Event event, String fileName) throws IOException {
        AnchorPane loader = FXMLLoader.load(getClass().getClassLoader().getResource(fileName));
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader));
        stage.show();
    }
}
