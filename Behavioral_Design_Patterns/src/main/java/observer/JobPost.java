package observer;

import java.util.Observable;

public class JobPost extends Observable {
    protected String title;

    public JobPost(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
