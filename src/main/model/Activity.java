package model;

public interface Activity {

    float determineCaloriesBurned(float weight);

    float getDistance();

    float getTime();

    float getPace();

    float getAverageSpeed();

    void setDistance(float distance);

    void setTime(float time);

    String getTitle();
}
