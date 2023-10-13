package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeComparatorTest {

    private Activity testRActivity1;
    private Activity testRActivity2;
    private Activity testRActivity3;
    private Activity testRActivity4;
    private Activity testBActivity1;
    private Activity testBActivity2;
    private Activity testBActivity3;
    private Activity testBActivity4;
    private TimeComparator timeComparator;

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
        timeComparator = new TimeComparator();
    }

    @Test
    void testTimeLower() {
        assertEquals(-1, timeComparator.compare(testRActivity1, testRActivity4));
        assertEquals(-1, timeComparator.compare(testBActivity4, testBActivity2));
        assertEquals(-1, timeComparator.compare(testRActivity4, testBActivity2));
    }

    @Test
    void testTimeHigher() {
        assertEquals(1, timeComparator.compare(testRActivity4, testRActivity3));
        assertEquals(1, timeComparator.compare(testBActivity2, testBActivity4));
        assertEquals(1, timeComparator.compare(testBActivity3, testRActivity1));
    }

    @Test
    void testTimeEqual() {
        assertEquals(0, timeComparator.compare(testBActivity2, testBActivity3));
        assertEquals(0, timeComparator.compare(testRActivity1, testRActivity3));
        assertEquals(0, timeComparator.compare(testRActivity4, testBActivity1));
    }
}
