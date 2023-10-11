package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikingActivityTest {

    private Activity testActivityB;

    @BeforeEach
    void beforeAll() {
        testActivityB = new BikingActivity(50F, 120F, "Morning ride");
        assertEquals(50, testActivityB.getDistance());
        assertEquals(120, testActivityB.getTime());
        assertEquals( testActivityB.getTime() / testActivityB.getDistance(), testActivityB.getPace());
        assertEquals(testActivityB.getDistance() / testActivityB.getTime(), testActivityB.getAverageSpeed());
        assertEquals("Morning ride", testActivityB.getTitle());
    }

    @Test
    void testDetermineCaloriesBurned() {
        float weight = 75;
        assertEquals(12 * weight * 120 / 60, testActivityB.determineCaloriesBurned(weight));
    }

}
