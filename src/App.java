import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.*;


public class App extends Application{
    protected static ArrayList<Applicant> appList = new ArrayList<>();

    public void start(Stage stage) throws Exception{
        appList.add(new Applicant("Omar Alomair","202046100","Male","PHD",5));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("  login");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icon.png")));
        stage.show();
    }
    public static void main(String[] args) throws Exception {
        launch();
    }
}
