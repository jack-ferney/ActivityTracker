package persistence;

import model.Activity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkActivity(float distance, float time, String title, Activity activity) {
        assertEquals(distance, activity.getDistance());
        assertEquals(time, activity.getTime());
        assertEquals(title, activity.getTitle());
    }

}
