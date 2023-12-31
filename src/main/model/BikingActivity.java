package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a biking activity with a distance, time, title, pace, and average speed
public class BikingActivity implements Activity, Writable {

    private float distance;
    private float time;
    private float pace;
    private float averageSpeed;
    private String title;

    // REQUIRES: distance > 0
    //           time > 0
    // EFFECTS: Creates a biking activity of given distance and time
    public BikingActivity(float distance, float time, String title) {
        this.distance = distance;
        this.time = time;
        this.pace = time / distance;
        this.averageSpeed = distance / time;
        this.title = title;
    }

    @Override
    public float determineCaloriesBurned(float weight) {
        return (12 * weight * this.getTime() / 60);
    }

    @Override
    public float getDistance() {
        return this.distance;
    }

    @Override
    public float getTime() {
        return this.time;
    }

    @Override
    public float getPace() {
        return this.pace;
    }

    @Override
    public float getAverageSpeed() {
        return this.averageSpeed;
    }

    @Override
    public void setDistance(float distance) {
        this.distance = distance;
        this.pace = this.time / distance;
        this.averageSpeed = distance / this.time;
        EventLog.getInstance().logEvent(new Event(this.getTitle() + " was edited!"));
    }

    @Override
    public void setTime(float time) {
        this.time = time;
        this.pace = time / this.distance;
        this.averageSpeed = this.distance / time;
        EventLog.getInstance().logEvent(new Event(this.getTitle() + " was edited!"));
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("distance", distance);
        json.put("time", time);
        json.put("title", title);
        json.put("activity type", "biking");
        return json;
    }
}
