package observer;

import java.util.Observable;
import java.util.Observer;

public class JobSeeker implements Observer {

    protected String name;

    public JobSeeker(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.printf("hi %s! new job posted: %s%n", getClass().getSimpleName(), ((JobPost) o).getTitle());
    }
}
