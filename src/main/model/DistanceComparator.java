package model;

// represents a comparator that compares the distance value of two given activities
public class DistanceComparator implements Comparator {

    @Override
    public int compare(Activity a1, Activity a2) {
        if (a1.getDistance() > a2.getDistance()) {
            return 1;
        } else if (a1.getDistance() == a2.getDistance()) {
            return 0;
        } else {
            return -1;
        }
    }
}
