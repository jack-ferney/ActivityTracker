package model;

import java.util.ArrayList;
import java.util.List;

public class ActivityList {

    private List<Activity> activities;
    private Comparator distComparator = new DistanceComparator();
    private Comparator timeComparator = new TimeComparator();

    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    // EFFECTS: returns longest distance of all activities in list, if multiple found then gives first found in list
    //          if the list is empty return null
    public Activity getLongestDistance() {

        if (activities.isEmpty()) {
            return null;
        } else {
            Activity longestDistanceSoFar = activities.get(0);

            for (Activity activity : activities) {
                if (distComparator.compare(activity, longestDistanceSoFar) > 0) {
                    longestDistanceSoFar = activity;
                }
            }
            return longestDistanceSoFar;
        }
    }

    // EFFECTS: returns shortest distance of all activities in list, if multiple found then gives first found in list
    //          if the list is empty return null
    public Activity getShortestDistance() {

        if (activities.isEmpty()) {
            return null;
        } else {
            Activity shortestDistSoFar = activities.get(0);

            for (Activity activity : activities) {
                if (distComparator.compare(shortestDistSoFar, activity) > 0) {
                    shortestDistSoFar = activity;
                }
            }
            return shortestDistSoFar;
        }
    }

    // EFFECTS: returns longest time of all activities in list, if multiple found then gives first found in list
    //          if the list is empty return null
    public Activity getLongestTime() {

        if (activities.isEmpty()) {
            return null;
        } else {
            Activity longestTimeSoFar = activities.get(0);

            for (Activity activity : activities) {
                if (timeComparator.compare(activity, longestTimeSoFar) > 0) {
                    longestTimeSoFar = activity;
                }
            }
            return longestTimeSoFar;
        }
    }

    // EFFECTS: returns shortest time of all activities in list, if multiple found then gives first found in list
    //          if the list is empty return null
    public Activity getShortestTime() {

        if (activities.isEmpty()) {
            return null;
        } else {
            Activity shortestTimeSoFar = activities.get(0);

            for (Activity activity : activities) {
                if (timeComparator.compare(shortestTimeSoFar, activity) > 0) {
                    shortestTimeSoFar = activity;
                }
            }
            return shortestTimeSoFar;
        }
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public Activity get(int i) {
        return activities.get(i);
    }

    public int size() {
        return activities.size();
    }

    public List<String> getListOfTitles() {
        List<String> titles = new ArrayList<>();
        for (Activity activity : activities) {
            titles.add(activity.getTitle());
        }
        return titles;
    }
}
