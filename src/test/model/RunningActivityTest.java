package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RunningActivityTest {

    private Activity testActivityR;

    @BeforeEach
    void beforeAll() {
        testActivityR = new RunningActivity(8F, 35F, "Workout Run");
        assertEquals(8, testActivityR.getDistance());
        assertEquals(35, testActivityR.getTime());
        assertEquals( testActivityR.getTime() / testActivityR.getDistance(), testActivityR.getPace());
        assertEquals(testActivityR.getDistance() / testActivityR.getTime(), testActivityR.getAverageSpeed());
        assertEquals("Workout Run", testActivityR.getTitle());
    }

    @Test
    void testDetermineCaloriesBurned() {
        float weight = 75;
        assertEquals(10 * weight * 35 / 60, testActivityR.determineCaloriesBurned(weight));
    }

}
