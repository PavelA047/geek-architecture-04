package Iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StationList implements Iterable<RadioStation> {
    protected List<RadioStation> radioStations = new ArrayList<>();
    private int counter = 0;

    @Override
    public Iterator<RadioStation> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                try {
                    return radioStations.get(counter) != null;
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }

            @Override
            public RadioStation next() {
                return radioStations.get(counter++);
            }
        };
    }

    public void addStation(RadioStation radioStation) {
        radioStations.add(radioStation);
    }

    public void removeStation(RadioStation radioStation) {
        radioStations.remove(radioStation);
    }
}
