package model;

// represents a running time goal with a goal value and completion status
public class RunningTimeGoal implements Goal {

    private float time;
    private boolean completed;

    // REQUIRES: time > 0
    // EFFECTS: Creates a running time goal of time given an initial completed status of false
    public RunningTimeGoal(float time) {
        this.time = time;
        this.completed = false;
    }

    @Override
    public boolean getGoalStatus() {
        return this.completed;
    }

    @Override
    public float getGoalValue() {
        return this.time;
    }

    @Override
    public void completeGoal() {
        this.completed = true;
        EventLog.getInstance().logEvent(new Event("A goal was completed!"));
    }

    @Override
    public void editGoal(float newGoal) {
        this.time = newGoal;
        this.completed = false;
        EventLog.getInstance().logEvent(new Event("A goal was edited!"));
    }
}
