import java.util.ArrayList;

public class Division extends Unit {
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Unit> directorates;
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;
    private static final long serialVersionUID = 1425527753303922345L;

    public Division(String name, int unitCapacity, String superior) throws Exception {
        super(name,unitCapacity,"Division", "Independent");
    }


    public void addDirectorate(Unit directorate){
        this.directorates.add(directorate);
    }
    public void removeDirectorate(Unit directorate){
        this.directorates.remove(directorate);
    }
    public Unit changeToDirectorate(String superior) throws Exception {
        Unit object1 = new Directorate(this.getName(), this.getUnitCapacity(), superior);
        for(Band jobBand:this.jobBands){
            object1.addJobBand(jobBand);
        }
        return object1;
    }
    public Unit changeToDepartment(String superior) throws Exception {
        Unit object1 = new Department(this.getName(), this.getUnitCapacity(), superior);
        for (Band jobBand:this.jobBands){
            object1.addJobBand(jobBand);
        }
        return object1;
    }

    public Unit changeToDivision(String superior) throws Exception {return this;}

}
