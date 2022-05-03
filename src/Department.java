import java.util.ArrayList;

public class Department extends Unit{
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;

    public Department(String name, int unitCapacity) throws Exception {
        super(name,unitCapacity,"Department");
    }


    public void changeToDirectorate() throws Exception {
        Directorate directorate = new Directorate(this.name, this.unitCapacity);
        for (Band jobBand: this.jobBands) directorate.addJobBand(jobBand);
        Department department = Department.this;
        department = null;
    }
    public void changeToDivision() throws Exception {
        Division division = new Division(this.name, this.unitCapacity);
        for (Band jobBand:this.jobBands) division.addJobBand(jobBand);
        Department department = Department.this;
        department = null;
    }
}
