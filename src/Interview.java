import java.io.Serializable;
import java.util.ArrayList;

public class Interview implements Serializable {
    private String time, date;
    private String result = "hold";
    private Applicant interviewee;
    private ArrayList<Employee> interviewers = new ArrayList<>();
    private String firstInterviewerName;
    private String secondInterviewerName;
    private String thirdInterviewerName;

    public Interview(String time, String date){
        this.time = time.toUpperCase();
        this.date = date.toUpperCase();
    }

    public void setInterviewee(Applicant interviewee) {
        this.interviewee = interviewee;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addInterviewer(Employee interviewer){
        this.interviewers.add(interviewer);
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFirstInterviewerName() {
        this.firstInterviewerName = interviewers.get(0).getName();
    }

    public void setSecondInterviewerName() {
        if(interviewers.get(1) == null) {
            this.secondInterviewerName = "None";
        }
        else {
            this.secondInterviewerName = interviewers.get(1).getName();
        }
    }

    public void setThirdInterviewerName() {
        if(interviewers.get(2) == null) {
            this.thirdInterviewerName = "None";
        }
        else {
            this.thirdInterviewerName = interviewers.get(2).getName();
        }
    }

    public Applicant getInterviewee() {
        return interviewee;
    }

    public ArrayList<Employee> getInterviewers() {
        return interviewers;
    }
    public ArrayList<String> getInterviewersNames(){
        ArrayList<String> interviewers = new ArrayList<>();
        for (Employee interviewer:this.interviewers) interviewers.add(interviewer.getName());
        return interviewers;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {
        return result;
    }

    public String getTime() {
        return time;
    }
    public void manageApplicantStatus() throws Exception {
        if (this.result.equalsIgnoreCase("pass")) interviewee.setStatus("approved");
        else if (this.result.equalsIgnoreCase("fail")) interviewee.setStatus("rejected");
    }
    public void viewInterviewDetails(){
        System.out.printf("The interview will take place on %s at %s", date,time);
    }
}
