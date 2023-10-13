package model;

public interface Goals {
    boolean getGoalStatus();

    float getGoalValue();

    // MODIFIES: this
    // EFFECTS: changes the status of this goal to true
    void completeGoal();

    // REQUIRES: newGoal > 0
    // MODIFIES: this
    // EFFECTS: changes the requirement of this goal to newGoal value
    void editGoal(float newGoal);
}
