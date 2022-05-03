import java.util.ArrayList;

public class Directorate extends Unit{
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;
    private static final long serialVersionUID = 2007609530191952104L;
   

    public Directorate(String name, int unitCapacity) throws Exception {
        super(name,unitCapacity,"Directorate");
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

    public Unit changeToDepartment() throws Exception {
        Unit object = new Department(this.getName(), this.getUnitCapacity());
        for (Band jobBand: this.jobBands) object.addJobBand(jobBand);
        return object;
    }
    public Unit changeToDivision() throws Exception {
        Unit division = new Division(this.getName(), this.getUnitCapacity());
        for (Band jobBand: this.jobBands) division.addJobBand(jobBand);
        return division;
    }

    public Directorate changeToDirectorate() throws Exception {return this;}
}
