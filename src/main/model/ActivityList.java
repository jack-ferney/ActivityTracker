package model;

import java.util.ArrayList;
import java.util.List;

public class ActivityList {

    private List<Activity> activities;
    private List<Activity> bikingActivities;
    private List<Activity> runningActivities;

    public ActivityList() {
        this.activities = new ArrayList<>();
        this.bikingActivities = new ArrayList<>();
        this.runningActivities = new ArrayList<>();
    }

    public void sortByDistance() {
        // stub (probably an easy way to do these ones)
    }

    public void sortByTime() {
        // stub (probably an easy way to do these ones)
    }

    public void sortRunFirstAndDistance() {
        // stub
        // (maybe purge the all activities list and then order the two others and add them to the all activities list)
    }

    public void sortRunFirstAndTime() {
        // stub
        // (maybe purge the all activities list and then order the two others and add them to the all activities list)
    }

    public void sortBikeFirstAndDistance() {
        // stub
        // (maybe purge the all activities list and then order the two others and add them to the all activities list)
    }

    public void sortBikeFirstAndTime() {
        // stub
        // (maybe purge the all activities list and then order the two others and add them to the all activities list)
    }
}
