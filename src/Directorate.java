import java.util.ArrayList;

public class Directorate extends Unit{
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;
    private String level = "Directorate";

    public Directorate(String name, int unitCapacity) throws Exception {
        super(name,unitCapacity);
    }
    public void addDepartment(Department department){
        this.departments.add(department);
    }
    public void removeDepartment(Department department){
        this.departments.remove(department);
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void changeToDepartment() throws Exception {
        Department object = new Department(this.name,this.unitCapacity);
        for (Band jobBand: this.jobBands) object.addJobBand(jobBand);
        Directorate directorate = Directorate.this;
        directorate = null;
    }
    public void changeToDivision() throws Exception {
        Division division = new Division(this.name,this.unitCapacity );
        for (Band jobBand: this.jobBands) division.addJobBand(jobBand);
        Directorate directorate = Directorate.this;
        directorate = null;
    }
}
