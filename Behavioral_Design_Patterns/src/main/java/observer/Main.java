package observer;

public class Main {
    public static void main(String[] args) {
        JobSeeker john = new JobSeeker("john");
        JobSeeker ivan = new JobSeeker("ivan");

        EmploymentAgency employmentAgency = new EmploymentAgency();

        employmentAgency.attach(john);
        employmentAgency.attach(ivan);

        employmentAgency.addJob(new JobPost("Java developer"));
    }
}
