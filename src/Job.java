import java.io.Serializable;

public class Job implements Serializable {
    private String name;
    private int basicSalary;
    private Band band;

    public Job(String name, int basicSalary, Band band) throws Exception{
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        if(basicSalary < 0) throw new Exception("Invalid salary");
        this.name = name.toUpperCase();
        this.band = band;
        this.basicSalary = basicSalary;
    }

    public String getName() {
        return name;
    }

    public Band getBand() {
        return band;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setName(String name) throws Exception {
        if (!(name.toUpperCase().matches("[A-Z - ]+"))) throw new Exception("Invalid name");
        this.name = name.toUpperCase();
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public void setBasicSalary(int basicSalary) throws Exception {
        if (basicSalary < 0) throw new Exception("Invalid salary");
        this.basicSalary = basicSalary;
    }

    public String toString() {
        return this.name;
    }
}
