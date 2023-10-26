package model;

import org.json.JSONObject;
import persistence.Writable;

public class RunningActivity implements Activity, Writable {

    private float distance;
    private float time;
    private float pace;
    private float averageSpeed;
    private String title;

    // REQUIRES: distance > 0
    //           time > 0
    // EFFECTS: Creates a running activity of given distance and time
    public RunningActivity(float distance, float time, String title) {
        this.distance = distance;
        this.time = time;
        this.pace = time / distance;
        this.averageSpeed = distance / time;
        this.title = title;
    }

    @Override
    public float determineCaloriesBurned(float weight) {
        return (10 * weight * this.getTime() / 60);
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
    }

    @Override
    public void setTime(float time) {
        this.time = time;
        this.pace = time / this.distance;
        this.averageSpeed = this.distance / time;
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
        json.put("activity type", "running");
        return json;
    }
}
