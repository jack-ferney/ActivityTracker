package model;

public class TimeComparator implements Comparator {

    @Override
    public int compare(Activity a1, Activity a2) {
        if (a1.getTime() > a2.getTime()) {
            return 1;
        } else if (a1.getTime() == a2.getTime()) {
            return 0;
        } else {
            return -1;
        }
    }
}
