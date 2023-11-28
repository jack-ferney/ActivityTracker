package model;

// represents a running distance goal with a goal value and completion status
public class RunningDistanceGoal implements Goal {

    private float distance;
    private boolean completed;

    // REQUIRES: distance > 0
    // EFFECTS: Creates a running distance goal of distance given an initial completed status of false
    public RunningDistanceGoal(float distance) {
        this.distance = distance;
        this.completed = false;
    }

    @Override
    public boolean getGoalStatus() {
        return this.completed;
    }

    @Override
    public float getGoalValue() {
        return this.distance;
    }

    @Override
    public void completeGoal() {
        this.completed = true;
        EventLog.getInstance().logEvent(new Event("A goal was completed!"));
    }

    @Override
    public void editGoal(float newGoal) {
        this.distance = newGoal;
        this.completed = false;
        EventLog.getInstance().logEvent(new Event("A goal was edited!"));
    }
}
