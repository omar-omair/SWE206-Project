import java.util.ArrayList;

public class Department extends Unit{
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;
    private static final long serialVersionUID = 7439313491230545621L;
    private String superior = "independent";

    public Department(String name, int unitCapacity, String superior) throws Exception {
        super(name,unitCapacity,"Department", superior);
    }


    public Unit changeToDirectorate(String superior) throws Exception {
        Unit directorate = new Directorate(this.getName(), this.getUnitCapacity(), superior);
        for (Band jobBand: this.jobBands) directorate.addJobBand(jobBand);
        return directorate;  
    }
    public Unit changeToDivision(String superior) throws Exception {
        Unit division = new Division(this.getName(), this.getUnitCapacity(), superior);
        for (Band jobBand:this.jobBands) division.addJobBand(jobBand);
        return division;
    }

    public Unit changeToDepartment(String superior) throws Exception {return this;}
}
