package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikingActivityTest {

    private Activity testActivityB;

    @BeforeEach
    void beforeAll() {
        testActivityB = new BikingActivity(45F, 100F, "Morning ride");
        assertEquals(45, testActivityB.getDistance());
        assertEquals(100, testActivityB.getTime());
        testActivityB.setDistance(50F);
        testActivityB.setTime(120F);
        assertEquals(50, testActivityB.getDistance());
        assertEquals(120, testActivityB.getTime());
        assertEquals( testActivityB.getTime() / testActivityB.getDistance(), testActivityB.getPace());
        assertEquals(testActivityB.getDistance() / testActivityB.getTime(), testActivityB.getAverageSpeed());
        assertEquals("Morning ride", testActivityB.getTitle());
    }

    @Test
    void testDetermineCaloriesBurned() {
        float weight = 75;
        assertEquals(12 * weight * testActivityB.getTime() / 60, testActivityB.determineCaloriesBurned(weight));
    }

}
