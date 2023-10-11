package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RunningTimeGoalTest {

    private RunningTimeGoal testRunningTGoal;

    @BeforeEach
    void runBefore() {
        testRunningTGoal = new RunningTimeGoal(30);
        assertEquals(30, testRunningTGoal.getGoalValue());
        assertFalse(testRunningTGoal.getGoalStatus());
    }

    @Test
    void testCompleteGoal() {
        testRunningTGoal.completeGoal();
        assertTrue(testRunningTGoal.getGoalStatus());
    }

    @Test
    void testEditGoal() {
        testRunningTGoal.editGoal(45);
        assertEquals(45, testRunningTGoal.getGoalValue());
    }

}
