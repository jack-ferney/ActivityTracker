package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BikingDistanceGoalTest {

    private BikingDistanceGoal testBikeDGoal;

    @BeforeEach
    void runBefore() {
        testBikeDGoal = new BikingDistanceGoal(55);
        assertEquals(55, testBikeDGoal.getGoalValue());
        assertFalse(testBikeDGoal.getGoalStatus());
    }

    @Test
    void testCompleteGoal() {
        testBikeDGoal.completeGoal();
        assertTrue(testBikeDGoal.getGoalStatus());
    }

    @Test
    void testEditGoal() {
        testBikeDGoal.editGoal(80);
        assertEquals(80, testBikeDGoal.getGoalValue());
        assertFalse(testBikeDGoal.getGoalStatus());
    }

}
