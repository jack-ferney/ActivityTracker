package model;

import java.util.ArrayList;
import java.util.List;

public class ActivityList {

    public enum ActivityType { RUNNING, BIKING, BOTH }

    private List<Activity> activities;
    private Comparator distComparator = new DistanceComparator();
    private Comparator timeComparator = new TimeComparator();

    // EFFECTS: constructs an empty list of activities
    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    // REQUIRES: an activity type enumeration value
    // EFFECTS: returns the activity within the activity type specified with the longest distance
    public Activity getLongestDistance(ActivityType activityType) {
        List<Activity> consideredActivities = new ArrayList<>();
        Activity longestDistance;
        if (activityType == ActivityType.BIKING) {
            getBikingActivities(consideredActivities);
        } else if (activityType == ActivityType.RUNNING) {
            getRunningActivities(consideredActivities);
        } else if (activityType == ActivityType.BOTH) {
            consideredActivities = activities;
        }
        if (consideredActivities.isEmpty()) {
            return null;
        } else {
            longestDistance = consideredActivities.get(0);
            for (Activity activity : consideredActivities) {
                if (distComparator.compare(activity, longestDistance) > 0) {
                    longestDistance = activity;
                }
            }
            return longestDistance;
        }
    }

    // REQUIRES: an activity type enumeration value
    // EFFECTS: returns the activity within the activity type specified with the shortest distance
    public Activity getShortestDistance(ActivityType activityType) {
        List<Activity> consideredActivities = new ArrayList<>();
        Activity shortestDistance;
        if (activityType == ActivityType.BIKING) {
            getBikingActivities(consideredActivities);
        } else if (activityType == ActivityType.RUNNING) {
            getRunningActivities(consideredActivities);
        } else if (activityType == ActivityType.BOTH) {
            consideredActivities = activities;
        }
        if (consideredActivities.isEmpty()) {
            return null;
        } else {
            shortestDistance = consideredActivities.get(0);
            for (Activity activity : consideredActivities) {
                if (distComparator.compare(shortestDistance, activity) > 0) {
                    shortestDistance = activity;
                }
            }
            return shortestDistance;
        }
    }

    // REQUIRES: an activity type enumeration value
    // EFFECTS: returns the activity within the activity type specified with the longest time
    public Activity getLongestTime(ActivityType activityType) {
        List<Activity> consideredActivities = new ArrayList<>();
        Activity longestTime;
        if (activityType == ActivityType.BIKING) {
            getBikingActivities(consideredActivities);
        } else if (activityType == ActivityType.RUNNING) {
            getRunningActivities(consideredActivities);
        } else if (activityType == ActivityType.BOTH) {
            consideredActivities = activities;
        }
        if (consideredActivities.isEmpty()) {
            return null;
        } else {
            longestTime = consideredActivities.get(0);
            for (Activity activity : consideredActivities) {
                if (timeComparator.compare(activity, longestTime) > 0) {
                    longestTime = activity;
                }
            }
            return longestTime;
        }
    }

    // REQUIRES: an activity type enumeration value
    // EFFECTS: returns the activity within the activity type specified with the shortest time
    public Activity getShortestTime(ActivityType activityType) {
        List<Activity> consideredActivities = new ArrayList<>();
        Activity shortestTime;
        if (activityType == ActivityType.BIKING) {
            getBikingActivities(consideredActivities);
        } else if (activityType == ActivityType.RUNNING) {
            getRunningActivities(consideredActivities);
        } else {
            consideredActivities = activities;
        }
        if (consideredActivities.isEmpty()) {
            return null;
        } else {
            shortestTime = consideredActivities.get(0);
            for (Activity activity : consideredActivities) {
                if (timeComparator.compare(shortestTime, activity) > 0) {
                    shortestTime = activity;
                }
            }
            return shortestTime;
        }
    }

    // REQUIRES: a list of activities (empty or not)
    // EFFECTS: returns a list of only the running activities in the given list
    private void getRunningActivities(List<Activity> consideredActivities) {
        if (activities.isEmpty()) {
            consideredActivities = null;
        } else {
            for (Activity activity : activities) {
                if (activity.getClass() == RunningActivity.class) {
                    consideredActivities.add(activity);
                }
            }
        }
    }

    // REQUIRES: a list of activities (empty or not)
    // EFFECTS: returns a list of only the biking activities in the given list
    private void getBikingActivities(List<Activity> consideredActivities) {
        if (activities.isEmpty()) {
            consideredActivities = null;
        } else {
            for (Activity activity : activities) {
                if (activity.getClass() == BikingActivity.class) {
                    consideredActivities.add(activity);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the given activity to the end of this list
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    // EFFECTS: returns the activity in this list with the given index
    public Activity get(int i) {
        return activities.get(i);
    }

    // EFFECTS: returns the length of the given list
    public int size() {
        return activities.size();
    }

    // REQUIRES: list to contain chosenActivity
    // EFFECTS: removes the given activity from this list
    public void removeActivity(Activity chosenActivity) {
        activities.remove(chosenActivity);
    }

    // EFFECTS: returns a list of the titles of activities in this list
    public List<String> getListOfTitles() {
        List<String> titles = new ArrayList<>();
        for (Activity activity : activities) {
            titles.add(activity.getTitle());
        }
        return titles;
    }
}
