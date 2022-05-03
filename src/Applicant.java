import java.io.Serializable;
import java.util.ArrayList;

public class Applicant implements Serializable {
    private String name, id, gender, educationLevel;
    private String status = "Hold";
    private int yearsOfExperience;
    private Interview interview = null;
    private int offeredSalary = 0;
    private Unit offeredUnit;
    private Job offeredJob;
    private static final long serialVersionUID = -4343060459389843856L;

    public Applicant(String name, String id, String gender, String educationLevel, int yearsOfExperience) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        if (!(id.matches("[0-9]+"))) throw new Exception("Invalid ID");
        if (yearsOfExperience < 0) throw new Exception("Invalid years of experience");
        if(!(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))) throw new Exception("Invalid gender");
        this.name = name.toUpperCase();
        this.id = id;
        this.gender = gender.toUpperCase();
        this.educationLevel = educationLevel.toUpperCase();
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public int getOfferedSalary() {
        return offeredSalary;
    }

    public Unit getOfferedUnit() {
        return offeredUnit;
    }

    public Job getOfferedJob() {
        return offeredJob;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel.toUpperCase();
    }

    public void setGender(String gender) throws Exception{
        this.gender = gender.toUpperCase();
        if(!(this.gender.equalsIgnoreCase("male") || this.gender.equalsIgnoreCase("female"))) throw new Exception("Invalid gender");

    }

    public void setId(String id) throws Exception {
        if (!(id.matches("[0-9]+"))) throw new Exception("Invalid ID");
        this.id = id;
    }

    public void setName(String name) throws Exception {
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }

    public void setStatus(String status) throws Exception {
        this.status = status.toUpperCase();
        if (!(this.status.equalsIgnoreCase("approved") || this.status.equalsIgnoreCase("rejected") || this.status.equalsIgnoreCase("Hold")))
            throw new Exception("Invalid status");
    }

    public void setYearsOfExperience(int yearsOfExperience) throws Exception {
        this.yearsOfExperience = yearsOfExperience;
        if (this.yearsOfExperience < 0) throw new Exception("Invalid years of experience");

    }
    public void viewInfo(){
        System.out.printf("Applicant name: %s\nApplicant ID: %s\nGender: %s\nYears of experience: %d\nEducation level: %s\nStatus: %s\n", name,id,gender,yearsOfExperience,educationLevel,status);
    }
    public boolean assignInterview(Interview interview){
        if (this.interview == null){
            this.interview = interview;
            interview.setInterviewee(Applicant.this);
            return true;
        }
        else return false;
    }
    public void setOfferedSalary(int offeredSalary) {
        this.offeredSalary = offeredSalary;
    }
    public void setOfferedUnit(Unit offeredUnit) {
        this.offeredUnit = offeredUnit;
    }
    public void setOfferedJob(Job offeredJob) {
        this.offeredJob = offeredJob;
    }

    public ArrayList<Integer> calaculateSalary(Job job, Unit unit){
        ArrayList<Integer> salaryRange = new ArrayList<>();
        int minBasicSalary,maxBasicSalary,totalMinSalary,totalMaxSalary;
        if (unit instanceof Division){
            minBasicSalary = job.getBasicSalary() + 500 * (yearsOfExperience - 2) + 1000;
            maxBasicSalary = job.getBasicSalary() + 500 * (yearsOfExperience + 2) + 1000;
        }
        else if(unit instanceof Directorate){
            minBasicSalary = job.getBasicSalary() + 500 * (yearsOfExperience - 2) + 500;
            maxBasicSalary = job.getBasicSalary() + 500 * (yearsOfExperience + 2) + 500;
        }
        else {
            minBasicSalary = job.getBasicSalary() + 500 * (yearsOfExperience - 2);
            maxBasicSalary = job.getBasicSalary() + 500 * (yearsOfExperience + 2);
        }
        totalMinSalary = (int) (minBasicSalary + 0.25 * minBasicSalary + 0.1 * minBasicSalary);
        totalMaxSalary = (int) (maxBasicSalary + 0.25 * maxBasicSalary + 0.1 * maxBasicSalary);
        salaryRange.add(totalMinSalary);
        salaryRange.add(totalMaxSalary);
        return salaryRange;
    }
    public boolean assignJob() throws Exception {
        if (offeredUnit.checkAvailability() > 0 && offeredSalary > 0){
            Employee employee = new Employee(this.name, this.offeredSalary, this.gender, this.id, this.offeredJob, this.offeredUnit,this.yearsOfExperience);
            return true;
        }
        else return false;
    }
    public boolean createJobOffer() throws Exception{
        if(this.offeredUnit.checkAvailability() > 0) {
            setStatus("Approved");
            return true;
        }
        else return false;
    }
}
