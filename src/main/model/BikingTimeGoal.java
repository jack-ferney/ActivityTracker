package model;

public class BikingTimeGoal implements Goals {

    private float time;
    private boolean completed;

    // REQUIRES: time > 0
    // EFFECTS: Creates a biking time goal of time given an initial completed status of false
    public BikingTimeGoal(float time) {
        this.time = time;
        this.completed = false;
    }

    @Override
    public boolean getGoalStatus() {
        return this.completed; // stub
    }

    @Override
    public float getGoalValue() {
        return this.time;
    }

    // MODIFIES: this
    // EFFECTS: changes the status of this goal to true
    @Override
    public void completeGoal() {
        this.completed = true;
    }

    // REQUIRES: newGoal > 0
    // MODIFIES: this
    // EFFECTS: changes the requirement of this goal to newGoal value
    @Override
    public void editGoal(float newGoal) {
        this.time = newGoal;
    }
}
