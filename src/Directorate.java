import java.util.ArrayList;

public class Directorate extends Unit{
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Unit> departments = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;
    private static final long serialVersionUID = 2007609530191952104L;
    private String superior = "independent";
   

    public Directorate(String name, int unitCapacity, String superior) throws Exception {
        super(name,unitCapacity,"Directorate", superior);
    }
    public void addDepartment(Unit department){
        this.departments.add(department);
    }
    public void removeDepartment(Unit department){
        this.departments.remove(department);
    }

    public ArrayList<Unit> getDepartments() {
        return departments;
    }

    public Unit changeToDepartment(String superior) throws Exception {
        Unit object = new Department(this.getName(), this.getUnitCapacity(), superior);
        for (Band jobBand: this.jobBands) object.addJobBand(jobBand);
        return object;
    }
    public Unit changeToDivision(String superior) throws Exception {
        Unit division = new Division(this.getName(), this.getUnitCapacity(), superior);
        for (Band jobBand: this.jobBands) division.addJobBand(jobBand);
        return division;
    }

    public Directorate changeToDirectorate(String superior) throws Exception {return this;}
}
