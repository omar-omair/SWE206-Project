public class Test {
    public static void main(String[] args) throws Exception {
        Band band = new Band("coe");
        Job job = new Job("eng", 100, band);
        Department department = new Department("swe", 10);
        Applicant applicant = new Applicant("Ghaith" , "2020" , "male" , "high" , 5);
        Employee employee = new Employee("mm" , 1000, "male", "2020",job, department, 19 );
        Interview interview = new Interview("5", "12");
        Employee employee1 = new Employee("mm" , 1000, "male", "2020",job, department, 19 );
        System.out.println(employee.assignInterview(interview));
        System.out.println(interview.getInterviewersNames());
        employee.getInterview().viewInterviewDetails();

    }
}
