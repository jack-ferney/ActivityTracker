package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RunningDistanceGoalTest {

    private RunningDistanceGoal testRunningDGoal;

    @BeforeEach
    void runBefore() {
        testRunningDGoal = new RunningDistanceGoal(55);
        assertEquals(55, testRunningDGoal.getGoalValue());
        assertFalse(testRunningDGoal.getGoalStatus());
    }

    @Test
    void testCompleteGoal() {
        testRunningDGoal.completeGoal();
        assertTrue(testRunningDGoal.getGoalStatus());
    }

    @Test
    void testEditGoal() {
        testRunningDGoal.editGoal(80);
        assertEquals(80, testRunningDGoal.getGoalValue());
        assertFalse(testRunningDGoal.getGoalStatus());
    }

}
