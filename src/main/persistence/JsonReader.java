package persistence;

import model.Activity;
import model.ActivityList;
import model.BikingActivity;
import model.RunningActivity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public ActivityList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseActivityList(jsonObject);
    }

    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private ActivityList parseActivityList(JSONObject jsonObject) {
        ActivityList al = new ActivityList();
        addActivities(al, jsonObject);
        return al;
    }

    private void addActivities(ActivityList al, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("activities");
        for (Object json : jsonArray) {
            JSONObject nextActivity = (JSONObject) json;
            addActivity(al, nextActivity);
        }
    }

    private void addActivity(ActivityList al, JSONObject nextActivity) {
        float distance = nextActivity.getFloat("distance");
        float time = nextActivity.getFloat("time");
        String title = nextActivity.getString("title");
        String activityType = nextActivity.getString("activity type");
        if (activityType.equals("biking")) {
            Activity activity = new BikingActivity(distance, time, title);
            al.addActivity(activity);
        } else if (activityType.equals("running")) {
            Activity activity = new RunningActivity(distance, time, title);
            al.addActivity(activity);
        }
    }
}
