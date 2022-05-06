import java.util.ArrayList;
import java.io.Serializable;

public abstract class Unit implements Serializable {
    private int unitCapacity;
    private ArrayList<Band> jobBands = new ArrayList<>();
    private String name;
    private ArrayList<Employee> employees = new ArrayList<>();
    private int availableSpots;
    private static final long serialVersionUID = 1955480633460932831L;
    private String level;
    private String superior = "Independent";

    public Unit(String name, int unitCapacity, String level, String superior) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        if (unitCapacity <= 0) throw new Exception("Invalid unit capacity");
        this.name = name.toUpperCase();
        this.unitCapacity = unitCapacity;
        this.level = level.toUpperCase();
        this.availableSpots = unitCapacity;
        if(superior != null) {
            this.superior = superior;
        }
        updateAvailableSpots();
    }
    public void addJobBand(Band band){
        this.jobBands.add(band);
    }
    public void removeJobBand(Band band){
        this.jobBands.remove(band);
    }

    public int getUnitCapacity() {
        return unitCapacity;
    }

    public String getName() {
        return name;
    }

    public String getLevel() {
        return level;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void updateAvailableSpots() {
        availableSpots = this.unitCapacity - employees.size();
    }

    public void addEmployee(Employee employee) throws Exception{
        for (Employee employee1: employees){
            if (employee == employee1) throw new Exception("Employee already exists in unit");
        }
        this.employees.add(employee);
        updateAvailableSpots();

    }
    public void removeEmployee(Employee employee) throws Exception{
        this.employees.remove(employee);
        updateAvailableSpots();
    }
    public int checkAvailability(){
        return this.unitCapacity - employees.size();
    }

    public ArrayList<String> getEmployeesNames() {
        ArrayList<String> employeesNames =new ArrayList<>();
        for (Employee employee: employees){
            employeesNames.add(employee.getName());
        }
        return employeesNames;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<String> getJobBandsNames() {
        ArrayList<String> bandsNames = new ArrayList<>();
        for (Band band:jobBands) bandsNames.add(band.getName());
        return bandsNames;
    }

    public ArrayList<Band> getJobBands() {
        return jobBands;
    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public String getSuperior() {
        return superior;
    }

    public void setName(String name) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }

    public void setUnitCapacity(int unitCapacity) throws Exception{
        if (unitCapacity <= 0) throw new Exception("Invalid unit capacity");
        this.unitCapacity = unitCapacity;
        updateAvailableSpots();
    }

    public void setLevel(String level) throws Exception{
        this.level = level.toUpperCase();
    }

    public abstract Unit changeToDepartment(String superior) throws Exception;
    public abstract Unit changeToDivision(String superior) throws Exception;
    public abstract Unit changeToDirectorate(String superior) throws Exception;

    public String toString() {
        return this.name;
    }
}
