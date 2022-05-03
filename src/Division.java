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
    public void changeToDirectorate() throws Exception {
        Directorate object1 = new Directorate(this.name, this.unitCapacity);
        for(Band jobBand:this.jobBands){
            object1.addJobBand(jobBand);
        }
        Division division = Division.this;
        division = null;
    }
    public void changeToDepartment() throws Exception {
        Department object1 = new Department(this.name, this.unitCapacity);
        for (Band jobBand:this.jobBands){
            object1.addJobBand(jobBand);
        }
        Division division = Division.this;
        division = null;
    }

}
