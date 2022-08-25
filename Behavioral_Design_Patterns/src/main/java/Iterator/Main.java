package Iterator;

public class Main {
    public static void main(String[] args) {
        StationList stationList = new StationList();

        stationList.addStation(new RadioStation(89));
        stationList.addStation(new RadioStation(101));
        stationList.addStation(new RadioStation(102));
        stationList.addStation(new RadioStation(103.2f));

        for (RadioStation s : stationList) {
            System.out.println(s.getFrequency());
        }
    }
}
