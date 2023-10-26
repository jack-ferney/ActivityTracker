package model;

import org.json.JSONObject;

public interface Activity {

    // REQUIRES: weight (kg) > 0
    // EFFECTS: Determines calories burned based on provided weight of user and time of activity
    float determineCaloriesBurned(float weight);

    float getDistance();

    float getTime();

    float getPace();

    float getAverageSpeed();

    // REQUIRES: distance > 0
    // MODIFIES: this
    // EFFECT: changes this distance to given distance and updates pace and average speed to match new distance
    void setDistance(float distance);

    // REQUIRES: time > 0
    // MODIFIES: this
    // EFFECT: changes this distance to given distance and updates pace and average speed to match new time
    void setTime(float time);

    String getTitle();

    JSONObject toJson();

}
