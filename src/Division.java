import java.util.ArrayList;

public class Division extends Unit {
    private String name;
    private ArrayList<Band> jobBands = getJobBands();
    private ArrayList<Directorate> directorates;
    private ArrayList<Employee> employees = new ArrayList<>();
    private int unitCapacity;

    public Division(String name, int unitCapacity) throws Exception {
        super(name,unitCapacity,"Division");
    }


    public void addDirectorate(Directorate directorate){
        this.directorates.add(directorate);
    }
    public void removeDirectorate(Directorate directorate){
        this.directorates.remove(directorate);
    }
    public Unit changeToDirectorate() throws Exception {
        Unit object1 = new Directorate(this.getName(), this.getUnitCapacity());
        for(Band jobBand:this.jobBands){
            object1.addJobBand(jobBand);
        }
        return object1;
    }
    public Unit changeToDepartment() throws Exception {
        Unit object1 = new Department(this.getName(), this.getUnitCapacity());
        for (Band jobBand:this.jobBands){
            object1.addJobBand(jobBand);
        }
        return object1;
    }

    public Unit changeToDivision() throws Exception {return this;}

}
