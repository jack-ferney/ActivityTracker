package model;

public class RunningActivity implements Activity {

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

    // REQUIRES: weight (kg) > 0
    // EFFECTS: Determines calories burned based on provided weight of user and time of activity
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
    }

    @Override
    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public String getTitle() {
        return title;
    }
}
