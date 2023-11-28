package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// represents a JsonReader that reads data files
public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the saved JSON file and returns the saved activities list
    public ActivityList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseActivityList(jsonObject);
    }

    // EFFECTS: reads a given file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: returns a parsed activity list from a given JSON object
    private ActivityList parseActivityList(JSONObject jsonObject) {
        ActivityList al = new ActivityList();
        addActivities(al, jsonObject);
        return al;
    }

    // MODIFIES: al (activity list from JSON file)
    // EFFECTS: adds all saved activities to a given activity list
    private void addActivities(ActivityList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("activities");
        EventLog.getInstance().logEvent(new Event("Activities were loaded!"));
        for (Object json : jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addActivity(al, nextActivity);
        }
    }

    // MODIFIES: al (activity list from JSON file)
    // EFFECTS: creates an activity from saved file and adds it to the activity list
    private void addActivity(ActivityList al, JSONObject nextActivity) {
        float distance = nextActivity.getFloat("distance");
        float time = nextActivity.getFloat("time");
        String title = nextActivity.getString("title");
        String activityType = nextActivity.getString("activity type");
        if (activityType.equals("biking")) {
            Activity activity = new BikingActivity(distance, time, title);
            al.addActivity(activity);
        }
        if (activityType.equals("running")) {
            Activity activity = new RunningActivity(distance, time, title);
            al.addActivity(activity);
        }
    }
}
