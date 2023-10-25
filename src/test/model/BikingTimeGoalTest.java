package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikingTimeGoalTest {

    private BikingTimeGoal testBikeTGoal;

    @BeforeEach
    void runBefore() {
        testBikeTGoal = new BikingTimeGoal(30);
        assertEquals(30, testBikeTGoal.getGoalValue());
        assertFalse(testBikeTGoal.getGoalStatus());
    }

    @Test
    void testCompleteGoal() {
        testBikeTGoal.completeGoal();
        assertTrue(testBikeTGoal.getGoalStatus());
    }

    @Test
    void testEditGoal() {
        testBikeTGoal.editGoal(45);
        assertEquals(45, testBikeTGoal.getGoalValue());
        assertFalse(testBikeTGoal.getGoalStatus());
    }

}
