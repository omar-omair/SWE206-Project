public class Employee {
    private String name,gender, id;
    private int salary, yearsOfExperience;
    private Job job;
    private Interview interview;
    private Unit unit;


    public Employee(String name, int salary, String gender, String id, Job job, Unit unit,int yearsOfExperience) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        if (salary < 0) throw new Exception("Invalid salary");
        if(!(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))) throw new Exception("Invalid gender");
        if (!(id.matches("[0-9]+"))) throw new Exception("Invalid ID");
        if (unit.checkAvailability() <= 0) throw new Exception("The unit has no available jobs");
        this.name = name.toUpperCase();
        this.salary = salary;
        this.gender = gender.toUpperCase();
        this.id = id;
        this.job = job;
        this.unit = unit;
        unit.addEmployee(Employee.this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if (!(id.matches("[0-9]+"))) throw new Exception("Invalid ID");
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) throws Exception{
        if(!(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female"))) throw new Exception("Invalid gender");
        this.gender = gender.toUpperCase();
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) throws Exception {
        if (salary < 0) throw new Exception("Invalid salary");
        this.salary = salary;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    public void viewInfo(){
        System.out.printf("Employee name: %s\nID: %s\nGender: %s\nSalary: %d\nJob: %s", this.name, this.id, this.gender, this.salary,this.job.getName());
    }
    public boolean manageJobUnit(Job job, Unit unit) throws Exception {
        if (unit.checkAvailability() > 0){
            setJob(job);
            unit.addEmployee(Employee.this);
            this.unit.removeEmployee(Employee.this);
            this.salary = calculateSalary(job, unit);
            return true;
        }
        else return false;
    }
    public int calculateSalary(Job job, Unit unit){
        int basicSalary, totalSalary;
        if (unit instanceof Division){
            basicSalary = job.getBasicSalary() + 500 * (this.yearsOfExperience) + 1000;
        }
        else if(unit instanceof Directorate){
            basicSalary = job.getBasicSalary() + 500 * (this.yearsOfExperience) + 500;
        }
        else {
            basicSalary = job.getBasicSalary() + 500 * (yearsOfExperience);
        }
        totalSalary = (int) (basicSalary + 0.25 * basicSalary + 0.1 * basicSalary);
        return totalSalary;
    }
    public boolean assignInterview(Interview interview){
        if (this.interview == null && interview.getInterviewers().size() < 3){
            this.interview = interview;
            interview.addInterviewer(Employee.this);
            return true;
        }
        else return false;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }
}
