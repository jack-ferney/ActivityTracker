package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityListTest {

    private ActivityList testActivityList;
    private Activity testRActivity1;
    private Activity testRActivity2;
    private Activity testRActivity3;
    private Activity testRActivity4;
    private Activity testBActivity1;
    private Activity testBActivity2;
    private Activity testBActivity3;
    private Activity testBActivity4;
    private TimeComparator timeComparator;
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
        timeComparator = new TimeComparator();
        testActivityList = new ActivityList();
        testActivityList.addActivity(testBActivity1);
        testActivityList.addActivity(testBActivity2);
        testActivityList.addActivity(testBActivity3);
        testActivityList.addActivity(testBActivity4);
        testActivityList.addActivity(testRActivity1);
        testActivityList.addActivity(testRActivity2);
        testActivityList.addActivity(testRActivity3);
        testActivityList.addActivity(testRActivity4);
    }

    @Test
    void testConstructor() {
        assertEquals(testBActivity1, testActivityList.get(0));
        assertEquals(8, testActivityList.size());
    }

    @Test
    void testGetLongestDistNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getLongestDistance());

    }

    @Test
    void testGetLongestTimeNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getLongestTime());
    }

    @Test
    void testGetShortestDistNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getShortestDistance());
    }

    @Test
    void testGetShortestTimeNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getShortestTime());
    }

    @Test
    void testGetLongestDistMultiple() {
        assertEquals(testBActivity3, testActivityList.getLongestDistance());
    }

    @Test
    void testGetLongestTimeMultiple() {
        assertEquals(testBActivity2, testActivityList.getLongestTime());
    }

    @Test
    void testGetShortestDistMultiple() {
        assertEquals(testRActivity3, testActivityList.getShortestDistance());
    }

    @Test
    void testGetShortestTimeMultiple() {
        assertEquals(testRActivity1, testActivityList.getShortestTime());
    }

    @Test
    void testGetListOfTitles() {
        List<String> titles = testActivityList.getListOfTitles();
        assertEquals("Evening ride", titles.get(0));
        assertEquals(8, titles.size());
        assertEquals("Same distance run", titles.get(7));
    }
}
