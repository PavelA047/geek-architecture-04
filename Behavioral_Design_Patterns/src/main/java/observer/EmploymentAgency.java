package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class EmploymentAgency {
    protected List<Observer> observers = new ArrayList<>();
    protected Object arg = new Object();

    protected void notifyFunction(JobPost jobPost) {
        for (Observer observer : observers) {
            observer.update(jobPost, arg);
        }
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void addJob(JobPost jobPost) {
        notifyFunction(jobPost);
    }
}
