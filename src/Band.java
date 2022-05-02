import java.util.ArrayList;

public class Band {
    private String name;
    private ArrayList<Unit> units;
    private ArrayList<Job> jobs = new ArrayList<>();

    public Band(String name) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }
    public void addUnit(Unit unit){
        this.units.add(unit);
    }
    public void removeUnit(Unit unit){
        this.units.remove(unit);
    }
    public void addJob(Job job){
        this.jobs.add(job);
    }
    public void removeJob(Job job){
        this.jobs.remove(job);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
    public ArrayList<String> getJobsNames(){
        ArrayList<String> jobNames = new ArrayList<>();
        for (Job job: this.jobs) jobNames.add(job.getName());
        return jobNames;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }
    public ArrayList<String> getUnitNames(){
        ArrayList<String> unitNames = new ArrayList<>();
        for (Unit unit:this.units) unitNames.add(unit.getName());
        return unitNames;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setName(String name) throws Exception {
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }
}
