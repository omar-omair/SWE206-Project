import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.*;


public class App extends Application{
    protected static ArrayList<Applicant> appList = new ArrayList<>();
    protected static ArrayList<Unit> unitList = new ArrayList<>();
    protected static ArrayList<Employee> employeeList = new ArrayList<>();
    protected static ArrayList<Interview> interList = new ArrayList<>();
    
    protected static Band management;
    protected static Band engineering;
    
    public void start(Stage stage) throws Exception{
        appList = read(appList, "../appList.ser");
        unitList = read(unitList, "../unitList.ser");
        employeeList = read(employeeList, "../empList.ser");
        interList = read(interList, "../interList.ser");
        
        management = new Band("Project Management");
        engineering = new Band("Engineering");

        management.addJob(new Job("Program Manager", 12000, management));
        management.addJob(new Job("Product Manager", 14000, management));

        engineering.addJob(new Job("Lead Engineer", 10000, engineering));
        engineering.addJob(new Job("Engineer", 8000, engineering));
        engineering.addJob(new Job("Senior Engineer", 14000, engineering));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        HashMap<String,String[]> accounts = new HashMap<String,String[]>();
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("  Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icon.png")));
        stage.show();
    }
    public static void main(String[] args) throws Exception {
        launch();
    }

    public static <T> void save(ArrayList<T> list, String filepath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
    }

    public static <T> ArrayList<T> read(ArrayList<T> list, String filepath) throws Exception {
        FileInputStream fis = new FileInputStream(filepath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        list = (ArrayList<T>) ois.readObject();
        ois.close();
        return list;
    }
}
