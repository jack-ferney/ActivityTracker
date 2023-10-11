package model;

public interface Goals {
    boolean getGoalStatus();

    float getGoalValue();

    void completeGoal();

    void editGoal(float newGoal);
}
