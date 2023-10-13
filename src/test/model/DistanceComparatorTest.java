package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DistanceComparatorTest {

    private Activity testRActivity1;
    private Activity testRActivity2;
    private Activity testRActivity3;
    private Activity testRActivity4;
    private Activity testBActivity1;
    private Activity testBActivity2;
    private Activity testBActivity3;
    private Activity testBActivity4;
    private DistanceComparator distComparator;

    @BeforeEach
    void runBefore() {
        testRActivity1 = new RunningActivity(6, 30, "Evening run");
        testRActivity2 = new RunningActivity(5, 35, "Slow run");
        testRActivity3 = new RunningActivity(4, 30, "Shorter run");
        testRActivity4 = new RunningActivity(4, 45, "Same distance run");
        testBActivity1 = new BikingActivity(6, 45, "Evening ride");
        testBActivity2 = new BikingActivity(20, 50, "Slow ride");
        testBActivity3 = new BikingActivity(30, 50, "Fast ride");
        testBActivity4 = new BikingActivity(30, 40, "Same distance ride");
        distComparator = new DistanceComparator();
    }

    @Test
    void testDistanceLower() {
        assertEquals(-1, distComparator.compare(testRActivity2, testRActivity1));
        assertEquals(-1, distComparator.compare(testBActivity2, testBActivity3));
        assertEquals(-1, distComparator.compare(testRActivity2, testBActivity3));
    }

    @Test
    void testDistanceHigher() {
        assertEquals(1, distComparator.compare(testRActivity1, testRActivity2));
        assertEquals(1, distComparator.compare(testBActivity2, testBActivity1));
        assertEquals(1, distComparator.compare(testBActivity2, testRActivity3));
    }

    @Test
    void testDistanceEqual() {
        assertEquals(0, distComparator.compare(testRActivity1, testBActivity1));
        assertEquals(0, distComparator.compare(testBActivity3, testBActivity4));
        assertEquals(0, distComparator.compare(testRActivity3, testRActivity4));
    }
}
