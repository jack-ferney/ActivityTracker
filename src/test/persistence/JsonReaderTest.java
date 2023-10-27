package persistence;

import model.ActivityList;
import model.BikingActivity;
import model.RunningActivity;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.son");
        try {
            ActivityList al = reader.read();
            fail("exception expected");
        } catch (IOException e) {
            // expected result
        }
    }

    @Test
    void testReaderEmptyActivityList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyActivityList.json");
        try {
            ActivityList al = reader.read();
            assertEquals(0, al.size());
        } catch (IOException e) {

        }
    }

    @Test
    void testReaderGeneralActivityList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralActivityList.json");
        try {
            ActivityList al = reader.read();
            assertEquals(2, al.size());
            checkActivity(100, 120, "test bike ride", al.get(0));
            assertTrue(BikingActivity.class == al.get(0).getClass());
            checkActivity(20, 100, "test run", al.get(1));
            assertTrue(RunningActivity.class == al.get(1).getClass());
        } catch (IOException e) {
            fail("shouldn't have caught anything");
        }
    }

}
