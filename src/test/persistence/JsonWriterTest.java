package persistence;

import model.ActivityList;
import model.BikingActivity;
import model.RunningActivity;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testInvalidFile() {
        try {
            ActivityList al = new ActivityList();
            JsonWriter writer = new JsonWriter("./ds\00jejjs");
            writer.open();
            fail("should have thrown exception");
        } catch (IOException e) {
            // expected result
        }
    }

    @Test
    void testWriterEmptyActivityList() {
        try {
            ActivityList al = new ActivityList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyActivityList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyActivityList.json");
            al = reader.read();
            assertEquals(0, al.size());
        } catch (IOException e) {
            fail("shouldn't have caught anything");
        }
    }

    @Test
    void testWriterGeneralActivityList() {
        try {
            ActivityList al = new ActivityList();
            al.addActivity(new BikingActivity(100, 120, "test bike ride"));
            al.addActivity(new RunningActivity(20, 100, "test run"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralActivityList.json");
            writer.open();
            writer.write(al);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralActivityList.json");
            al = reader.read();
            assertEquals(2, al.size());
            checkActivity(100, 120, "test bike ride", al.get(0));
            checkActivity(20, 100, "test run", al.get(1));
        } catch (IOException e) {
            fail("shouldn't have caught anything");
        }
    }
}
