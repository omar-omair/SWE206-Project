import java.util.ArrayList;

public class Department extends Unit{
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;
    private static final long serialVersionUID = 7439313491230545621L;

    public Department(String name, int unitCapacity) throws Exception {
        super(name,unitCapacity,"Department");
    }


    public Unit changeToDirectorate() throws Exception {
        Unit directorate = new Directorate(this.getName(), this.getUnitCapacity());
        for (Band jobBand: this.jobBands) directorate.addJobBand(jobBand);
        return directorate;  
    }
    public Unit changeToDivision() throws Exception {
        Unit division = new Division(this.getName(), this.getUnitCapacity());
        for (Band jobBand:this.jobBands) division.addJobBand(jobBand);
        return division;
    }

    public Unit changeToDepartment() throws Exception {return this;}
}
