package state;

import java.util.Locale;

public class LowerCase implements WritingState{
    @Override
    public void write(String words) {
        System.out.println(words.toLowerCase(Locale.ROOT));
    }
}
