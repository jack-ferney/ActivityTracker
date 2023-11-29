package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
import java.util.ArrayList;

// Represents a list of either biking activities and running activities
public class ActivityList implements Writable {

    // EFFECTS: returns the activity that has the given title (used for GUI interface)
    public Activity getActivityGUI(String chosenActivity) {
        for (Activity activity : activities) {
            if (activity.getTitle().toLowerCase().equals(chosenActivity)) {
                return activity;
            }
        }
        return null;
    }

    public enum ActivityType { RUNNING, BIKING, BOTH }

    private List<Activity> activities;
    private Comparator distComparator = new DistanceComparator();
    private Comparator timeComparator = new TimeComparator();

    // EFFECTS: constructs an empty list of activities
    public ActivityList() {
        this.activities = new ArrayList<>();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("activities", activitiesToJson());
        return json;
    }

    // MODIFIES: this
    // EFFECTS: creates a JSON array and adds each activity in current activity list to it
    private JSONArray activitiesToJson() {
        JSONArray jsonArray = new JSONArray();
        EventLog.getInstance().logEvent(new Event("Activities were saved!"));

        for (Activity activity : activities) {
            jsonArray.put(activity.toJson());
        }

        return jsonArray;
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
        } else {
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
        } else {
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
        } else {
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
            // List is already empty
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
            // List is already empty
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
        EventLog.getInstance().logEvent(new Event("Activity \"" + activity.getTitle() + "\" was added!"));
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
        EventLog.getInstance().logEvent(new Event("Activity \""
                + chosenActivity.getTitle()
                + "\" was removed"));
    }

    // EFFECTS: returns a list of the titles of activities in this list
    public List<String> getListOfTitles() {
        List<String> titles = new ArrayList<>();
        for (Activity activity : activities) {
            titles.add(activity.getTitle());
        }
        return titles;
    }

    // EFFECTS: returns this list of activities in a string form that is used for GUI interface of application
    public String writeActivitiesForGUI() {
        String activitiesText = "Activities:\n";
        if (!activities.isEmpty()) {
            for (Activity activity : activities) {
                String activityType = "";
                if (activity.getClass() == BikingActivity.class) {
                    activityType = "Biking";
                }
                if (activity.getClass() == RunningActivity.class) {
                    activityType = "Running";
                }
                activitiesText += "\n   "
                        + activity.getTitle() + ": " + "\n        " + "Distance: " + activity.getDistance() + "km   "
                        + "Time: " + activity.getTime() + "mins" + "   Activity Type: " + activityType;
            }
        } else {
            activitiesText = "You have no recorded activities! Try adding some!";
        }
        return activitiesText;
    }
}
