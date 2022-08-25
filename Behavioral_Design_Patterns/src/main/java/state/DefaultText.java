package state;

import java.util.Locale;

public class DefaultText implements WritingState{
    @Override
    public void write(String words) {
        System.out.println(words);
    }
}
