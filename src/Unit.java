import java.util.ArrayList;
import java.io.Serializable;

public abstract class Unit implements Serializable {
    private int unitCapaciy;
    private ArrayList<Band> jobBands = new ArrayList<>();
    private String name;
    private ArrayList<Employee> employees = new ArrayList<>();
    public Unit(String name, int unitCapaciy) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        if (unitCapaciy <= 0) throw new Exception("Invalid unit capacity");
        this.name = name.toUpperCase();
        this.unitCapaciy = unitCapaciy;
    }
    public void addJobBand(Band band){
        this.jobBands.add(band);
    }
    public void removeJobBand(Band band){
        this.jobBands.remove(band);
    }

    public int getUnitCapaciy() {
        return unitCapaciy;
    }

    public String getName() {
        return name;
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
        return this.unitCapaciy - employees.size();
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

    public void setUnitCapaciy(int unitCapaciy) throws Exception{
        if (unitCapaciy <= 0) throw new Exception("Invalid unit capacity");
        this.unitCapaciy = unitCapaciy;
    }

}
