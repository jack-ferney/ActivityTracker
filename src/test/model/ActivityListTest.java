package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityListTest {

    private ActivityList testActivityList;
    private ActivityList testAL;
    private Activity testRActivity1;
    private Activity testRActivity2;
    private Activity testRActivity3;
    private Activity testRActivity4;
    private Activity testRA;
    private Activity testBActivity1;
    private Activity testBActivity2;
    private Activity testBActivity3;
    private Activity testBActivity4;
    private Activity testBActivity5;
    private Activity testBA;
    private TimeComparator timeComparator;
    private DistanceComparator distComparator;

    @BeforeEach
    void runBefore() {
        testRActivity1 = new RunningActivity(6, 35, "Evening run");
        testRActivity2 = new RunningActivity(5, 30, "Slow run");
        testRActivity3 = new RunningActivity(4, 30, "Shorter run");
        testRActivity4 = new RunningActivity(4, 45, "Same distance run");
        testRA = new RunningActivity(1,1, "Test1");
        testBActivity1 = new BikingActivity(6, 45, "Evening ride");
        testBActivity2 = new BikingActivity(20, 50, "Slow ride");
        testBActivity3 = new BikingActivity(30, 50, "Fast ride");
        testBActivity4 = new BikingActivity(30, 40, "Same distance ride");
        testBActivity5 = new BikingActivity(3, 20, "Shortest Ride");
        testBA = new BikingActivity(1,1,"Test2");
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
        testAL = new ActivityList();
        testAL.addActivity(testRA);
        testAL.addActivity(testBA);
    }

    @Test
    void testConstructor() {
        assertEquals(testBActivity1, testActivityList.get(0));
        assertEquals(8, testActivityList.size());
    }

    @Test
    void testGetLongestDistNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getLongestDistance(ActivityList.ActivityType.BOTH));

    }

    @Test
    void testGetLongestTimeNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getLongestTime(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetShortestDistNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getShortestDistance(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetShortestTimeNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getShortestTime(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetLongestDistMultiple() {
        assertEquals(testBActivity3, testActivityList.getLongestDistance(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetLongestDistBikingActivityNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getLongestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetLongestDistBikingActivityOnlyRunningInList() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testRActivity1);
        testList.addActivity(testRActivity2);
        assertNull(testList.getLongestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetLongestDistBikingActivityRunningActivityLonger() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testRActivity2);
        testList.addActivity(testRActivity1);
        testList.addActivity(testBActivity5);
        assertEquals(testBActivity5, testList.getLongestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetLongestDistBikingActivityLongList() {
        assertEquals(testBActivity3, testActivityList.getLongestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetShortestDistBikingActivityNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getShortestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetShortestDistBikingActivityOnlyRunningInList() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testRActivity1);
        testList.addActivity(testRActivity2);
        assertNull(testList.getShortestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetShortestDistBikingActivityRunningActivityShorter() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testRActivity3);
        testList.addActivity(testRActivity1);
        testList.addActivity(testBActivity5);
        assertEquals(testBActivity5, testList.getShortestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetShortestDistBikingActivityLongList() {
        assertEquals(testBActivity1, testActivityList.getShortestDistance(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetLongestDistRunningActivityNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getLongestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetLongestDistRunningActivityOnlyRunningInList() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testBActivity5);
        testList.addActivity(testBActivity2);
        assertNull(testList.getLongestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetLongestDistRunningActivityBikingActivityLonger() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testRActivity2);
        testList.addActivity(testRActivity1);
        testList.addActivity(testBActivity2);
        assertEquals(testRActivity1, testList.getLongestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetLongestDistRunningActivityLongList() {
        assertEquals(testRActivity1, testActivityList.getLongestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetShortestDistRunningActivityNothingInList() {
        ActivityList testList = new ActivityList();
        assertNull(testList.getShortestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetShortestDistRunningActivityOnlyBikingInList() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testBActivity5);
        testList.addActivity(testBActivity2);
        assertNull(testList.getShortestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetShortestDistRunningActivityBikingActivityShorter() {
        ActivityList testList = new ActivityList();
        testList.addActivity(testRActivity2);
        testList.addActivity(testRActivity1);
        testList.addActivity(testBActivity5);
        assertEquals(testRActivity2, testList.getShortestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetShortestDistRunningActivityLongList() {
        assertEquals(testRActivity3, testActivityList.getShortestDistance(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetLongestTimeMultiple() {
        assertEquals(testBActivity2, testActivityList.getLongestTime(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetShortestDistMultiple() {
        assertEquals(testRActivity3, testActivityList.getShortestDistance(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetShortestTimeMultiple() {
        assertEquals(testRActivity2, testActivityList.getShortestTime(ActivityList.ActivityType.BOTH));
    }

    @Test
    void testGetLongestTimeBiking() {
        assertEquals(testBActivity2, testActivityList.getLongestTime(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetShortestTimeBiking() {
        assertEquals(testBActivity4, testActivityList.getShortestTime(ActivityList.ActivityType.BIKING));
    }

    @Test
    void testGetLongestTimeRunning() {
        assertEquals(testRActivity4, testActivityList.getLongestTime(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetShortestTimeRunning() {
        assertEquals(testRActivity2, testActivityList.getShortestTime(ActivityList.ActivityType.RUNNING));
    }

    @Test
    void testGetListOfTitles() {
        List<String> titles = testActivityList.getListOfTitles();
        assertEquals("Evening ride", titles.get(0));
        assertEquals(8, titles.size());
        assertEquals("Same distance run", titles.get(7));
    }

    @Test
    void testRemoveActivity() {
        assertTrue(testActivityList.getListOfTitles().contains("Evening ride"));
        assertEquals(8, testActivityList.size());
        testActivityList.removeActivity(testBActivity1);
        assertFalse(testActivityList.getListOfTitles().contains("Evening ride"));
        assertEquals(7, testActivityList.size());
    }

    @Test
    void testGetActivityGUI() {
        assertEquals(testRA, testAL.getActivityGUI("test1"));
        assertEquals(testBA, testAL.getActivityGUI("test2"));
        assertNull(testAL.getActivityGUI("hi"));
    }

    @Test
    void testWriteActivitiesForGuiNormal() {
        assertEquals("Activities:\n\n   Test1: \n        Distance: 1.0km   Time: 1.0mins   Activity Type: " +
                "Running\n   Test2: " + "\n        Distance: 1.0km   Time: 1.0mins   Activity Type: Biking",
                testAL.writeActivitiesForGUI());
    }

    @Test
    void testWriteActivitiesForGuiBlank() {
        ActivityList blankActivities = new ActivityList();
        assertEquals("You have no recorded activities! Try adding some!", blankActivities.writeActivitiesForGUI());
    }

}
