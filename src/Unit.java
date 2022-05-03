import java.util.ArrayList;
import java.io.Serializable;

public abstract class Unit implements Serializable {
    private int unitCapacity;
    private ArrayList<Band> jobBands = new ArrayList<>();
    private String name;
    private ArrayList<Employee> employees = new ArrayList<>();
    private static final long serialVersionUID = 1955480633460932831L;
    private String level;

    public Unit(String name, int unitCapacity, String level) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        if (unitCapacity <= 0) throw new Exception("Invalid unit capacity");
        this.name = name.toUpperCase();
        this.unitCapacity = unitCapacity;
        this.level = level.toUpperCase();
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
    public void addEmployee(Employee employee) throws Exception{
        for (Employee employee1: employees){
            if (employee == employee1) throw new Exception("Employee already exists in unit");
        }
        this.employees.add(employee);
        addJobBand(employee.getJob().getBand());
        if (employee.getUnit() != Unit.this) employee.getUnit().removeEmployee(employee);
        employee.setUnit(Unit.this);

    }
    public void removeEmployee(Employee employee) throws Exception{
        if (this.employees.size() == 1) throw new Exception("This is the last employee in the unit");
        this.employees.remove(employee);
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

    public void setName(String name) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }

    public void setUnitCapacity(int unitCapacity) throws Exception{
        if (unitCapacity <= 0) throw new Exception("Invalid unit capacity");
        this.unitCapacity = unitCapacity;
    }

    public void setLevel(String level) throws Exception{
        this.level = level.toUpperCase();
    }

    public abstract Unit changeToDepartment() throws Exception;
    public abstract Unit changeToDivision() throws Exception;
    public abstract Unit changeToDirectorate() throws Exception;

    public String toString() {
        return this.name;
    }
}
