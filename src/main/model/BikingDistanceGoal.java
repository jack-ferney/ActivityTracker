package model;

public class BikingDistanceGoal implements Goals {

    private float distance;
    private boolean completed;

    // REQUIRES: distance > 0
    // EFFECTS: Creates a biking distance goal of distance given an initial completed status of false
    public BikingDistanceGoal(float distance) {
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
        this.distance = newGoal;
    }
}
