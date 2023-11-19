package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.options.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Represents an instance of an activity tracker with an activity list and goals using GUI based interactions
public class ActivityTrackerGUI extends JFrame {
    private static final String JSON_STORE = "./data/ActivityList.json";
    private ActivityList activities;
    private RunningDistanceGoal runningDGoal = new RunningDistanceGoal(0);
    private RunningTimeGoal runningTGoal = new RunningTimeGoal(0);
    private BikingDistanceGoal bikingDGoal = new BikingDistanceGoal(0);
    private BikingTimeGoal bikingTGoal = new BikingTimeGoal(0);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String printGoals = "Biking Distance Goal:" + "                         " + "Running Distance Goal:" + "\n"
            + "   -" + bikingDGoal.getGoalValue() + "km" + "                                                  "
            + "   -" + runningDGoal.getGoalValue() + "km" + "\n"
            + "   -" + "Completed: " + bikingDGoal.getGoalStatus() + "                              " + "   -"
            + "Completed: " + runningDGoal.getGoalStatus() + "\n\n"
            + "Biking Time Goal:" + "                               " + "Running Time Goal:" + "\n"
            + "   -" + bikingTGoal.getGoalValue() + "min" + "                                                  "
            + "   -" + runningTGoal.getGoalValue() + "min" + "\n" + "   -" + "Completed: "
            + bikingTGoal.getGoalStatus() + "                               " + "   -" + "Completed: "
            + runningTGoal.getGoalStatus();
    private JPanel activeArea = new JPanel();
    private JTextArea activitiesText = new JTextArea();
    private JTextArea goalsText = new JTextArea();

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 750;
    private List<Option> options;
    private Font font;

    // EFFECTS: creates a new instance of activity tracker with no activities in the list and
    //          uses a graphical base interaction
    public ActivityTrackerGUI() {
        super("Activity Tracker");
        setUp();
    }

    private void setUp() {
        options = new ArrayList<>();
        font = new Font(Font.SANS_SERIF, 1, 15);
        activities = new ActivityList();
        runningDGoal = new RunningDistanceGoal(0);
        runningTGoal = new RunningTimeGoal(0);
        bikingDGoal = new BikingDistanceGoal(0);
        bikingTGoal = new BikingTimeGoal(0);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        startGraphics();
        activitiesText.setFont(font);
        activitiesText.setEditable(false);
        goalsText.setFont(font);
        goalsText.setEditable(false);
    }

    private void startGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createOptions();
        createActiveArea();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createActiveArea() {
        JScrollPane activitiesScrollPane = new JScrollPane(activitiesText);
        activitiesScrollPane.setPreferredSize(new Dimension(WIDTH - 350, 540));
        activitiesText.append(activities.writeActivitiesForGUI());
        activeArea.add(activitiesScrollPane);
        JScrollPane goalsScrollPane = new JScrollPane(goalsText);
        goalsScrollPane.setPreferredSize(new Dimension(WIDTH - 350, 150));
        goalsText.append(printGoals);
        activeArea.add(goalsScrollPane);
        add(activeArea);
    }

    private void updateActiveArea() {
        printGoals = "Biking Distance Goal:" + "                         " + "Running Distance Goal:" + "\n"
                + "   -" + bikingDGoal.getGoalValue() + "km" + "                                                  "
                + "   -" + runningDGoal.getGoalValue() + "km" + "\n"
                + "   -" + "Completed: " + bikingDGoal.getGoalStatus() + "                              " + "   -"
                + "Completed: " + runningDGoal.getGoalStatus() + "\n\n"
                + "Biking Time Goal:" + "                               " + "Running Time Goal:" + "\n"
                + "   -" + bikingTGoal.getGoalValue() + "min" + "                                                  "
                + "   -" + runningTGoal.getGoalValue() + "min" + "\n" + "   -" + "Completed: "
                + bikingTGoal.getGoalStatus() + "                               " + "   -" + "Completed: "
                + runningTGoal.getGoalStatus();
        activitiesText.selectAll();
        activitiesText.replaceSelection("");
        activitiesText.append(activities.writeActivitiesForGUI());
        goalsText.selectAll();
        goalsText.replaceSelection("");
        goalsText.append(printGoals);
    }

    private void createOptions() {
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new GridLayout(0, 1));
        optionsArea.setSize(new Dimension(0, 0));
        add(optionsArea, BorderLayout.WEST);

        AddOption addOption = new AddOption(this, optionsArea);
        options.add(addOption);

        GetEditDeleteOption getEditDeleteOption = new GetEditDeleteOption(this, optionsArea);
        options.add(getEditDeleteOption);

        SearchOption searchOption = new SearchOption(this, optionsArea);
        options.add(searchOption);

        CompleteGoalOption completeGoalOption = new CompleteGoalOption(this, optionsArea);
        options.add(completeGoalOption);

        EditGoalOption editGoalOption = new EditGoalOption(this, optionsArea);
        options.add(editGoalOption);

        SaveLoadOption saveLoadOption = new SaveLoadOption(this, optionsArea);
        options.add(saveLoadOption);
    }

    // MODIFIES: this
    // EFFECTS: loads the saved activities and replaces any current activities
    private void loadFiles() {
        try {
            activities = jsonReader.read();
            updateActiveArea();
            JOptionPane.showMessageDialog(null, "Loaded saved activities!!!");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the current activities to a file
    private void saveFiles() {
        try {
            jsonWriter.open();
            jsonWriter.write(activities);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved current activities!!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    public void saveLoadAction() {
        String[] options = {"Save", "Load"};
        var loadOrSave = JOptionPane.showOptionDialog(null, "Save or Load?", "Select one:",
                0, 3, null, options, options[0]);
        if (loadOrSave == 0) {
            this.saveFiles();
        } else if (loadOrSave == 1) {
            this.loadFiles();
        }
    }

    public void addActivity() {
        String[] options = {"Biking", "Running"};
        float distance = 0;
        float time = 0;
        String title = null;
        var bikingOrRunning = JOptionPane.showOptionDialog(null,
                "Is this a biking activity or running activity?", "Select one:",
                0, 3, null, options, options[0]);
        distance = Float.parseFloat(JOptionPane.showInputDialog("What is the distance for this activity?"));
        time = Float.parseFloat(JOptionPane.showInputDialog("What is the time for this activity?"));
        title = JOptionPane.showInputDialog("Title for this activity?");
        if (bikingOrRunning == 0) {
            activities.addActivity(new BikingActivity(distance, time, title));
        } else if (bikingOrRunning == 1) {
            activities.addActivity(new RunningActivity(distance, time, title));
        }
        updateActiveArea();
    }

    public void getActivity() {
        if (activities.size() == 0) {
            JOptionPane.showMessageDialog(null, "You have no activities. Try adding some first!");
        } else {
            String chosenActivity = JOptionPane.showInputDialog("Enter title of activity you want to get");
            chosenActivity.toLowerCase();
            displayActivity(chosenActivity);
        }
    }

    private void displayActivity(String chosenActivity) {
        Activity gotActivity = activities.getActivityGUI(chosenActivity);
        String message = "Activity Details:" + "\n\n   Title: " + gotActivity.getTitle() + "\n      Distance: "
                + gotActivity.getDistance() + "km   " + "Time: " + gotActivity.getTime() + "mins   " + "Average Speed: "
                + gotActivity.getAverageSpeed() + "km/h   " + "Pace: " + gotActivity.getPace() + "mins/km"
                + "\n\nDo you want to edit or delete this activity?";
        String[] options = {"Edit", "Delete", "Cancel"};
        var choice = JOptionPane.showOptionDialog(null,
                message, "Select one:", 0,
                3, null, options, options[0]);
        if (choice == 0) {
            editActivity(gotActivity);
        } else if (choice == 1) {
            deleteActivity(gotActivity);
        }
        updateActiveArea();
    }

    private void deleteActivity(Activity gotActivity) {
        activities.removeActivity(gotActivity);
    }

    private void editActivity(Activity gotActivity) {
        float newDistance = Float.parseFloat(JOptionPane.showInputDialog("New Distance: "));
        float newTime = Float.parseFloat(JOptionPane.showInputDialog("New Time: "));
        gotActivity.setDistance(newDistance);
        gotActivity.setTime(newTime);
    }

    // MODIFIES: goals
    // EFFECTS: if there is an activity in the list of activities that fulfills the requirement set by a goal then that
    //          goal is completed
    public void completeGoals() {
        if (!(activities.getLongestDistance(ActivityList.ActivityType.BIKING) == null)) {
            if (activities.getLongestDistance(ActivityList.ActivityType.BIKING).getDistance()
                    >= bikingDGoal.getGoalValue()) {
                bikingDGoal.completeGoal();
            }
            if (activities.getLongestTime(ActivityList.ActivityType.BIKING).getTime() >= bikingTGoal.getGoalValue()) {
                bikingTGoal.completeGoal();
            }
        }
        if (!(activities.getLongestDistance(ActivityList.ActivityType.RUNNING) == null)) {
            if (activities.getLongestDistance(ActivityList.ActivityType.RUNNING).getDistance()
                    >= runningDGoal.getGoalValue()) {
                runningDGoal.completeGoal();
            }
            if (activities.getLongestTime(ActivityList.ActivityType.RUNNING).getTime() >= runningTGoal.getGoalValue()) {
                runningTGoal.completeGoal();
            }
        }
        updateActiveArea();
        JOptionPane.showMessageDialog(null,
                "If you have an activity that completed the goal then it has been marked as completed!");
    }

    public void editGoals() {
        String[] options = {"Biking Distance Goal",
                "Biking Time Goal",
                "Running Distance Goal",
                "Running Time Goal"};
        var choice = JOptionPane.showOptionDialog(null,
                "Which goal do you want to edit?", "Select one:", 0,
                3, null, options, options[0]);
        if (choice == 0) {
            float newValue = Float.parseFloat(JOptionPane.showInputDialog("New Goal (kms): "));
            bikingDGoal.editGoal(newValue);
        } else if (choice == 1) {
            float newValue = Float.parseFloat(JOptionPane.showInputDialog("New Goal (mins): "));
            bikingTGoal.editGoal(newValue);
        } else if (choice == 2) {
            float newValue = Float.parseFloat(JOptionPane.showInputDialog("New Goal (kms): "));
            runningDGoal.editGoal(newValue);
        } else if (choice == 3) {
            float newValue = Float.parseFloat(JOptionPane.showInputDialog("New Goal (mins): "));
            runningTGoal.editGoal(newValue);
        }
        updateActiveArea();
    }

    public void searchActivities() {
        if (activities.size() == 0) {
            JOptionPane.showMessageDialog(null,
                    "You have no activities to search! Try adding some first!");
        } else {
            String[] dataFilter = {"Longest Distance", "Shortest Distance", "Longest Time", "Shortest Time"};
            String[] activityFilter = {"Biking", "Running", "Both"};
            var dataFilterChoice = JOptionPane.showOptionDialog(null, "Criteria of Search:",
                    "Select one:", 0, 3, null, dataFilter, dataFilter[0]);
            var activityFilterChoice = JOptionPane.showOptionDialog(null, "Type of Activity:",
                    "Select one:", 0, 3, null, activityFilter, activityFilter[0]);
            displayFilteredActivity(dataFilterChoice, activityFilterChoice);
        }
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void displayFilteredActivity(int dataFilterChoice, int activityFilterChoice) {
        if (dataFilterChoice == 0 && activityFilterChoice == 0) {
            displayActivity(activities.getLongestDistance(ActivityList.ActivityType.BIKING).getTitle());
        } else if (dataFilterChoice == 0 && activityFilterChoice == 1) {
            displayActivity(activities.getLongestDistance(ActivityList.ActivityType.RUNNING).getTitle());
        } else if (dataFilterChoice == 0 && activityFilterChoice == 2) {
            displayActivity(activities.getLongestDistance(ActivityList.ActivityType.BOTH).getTitle());
        } else if (dataFilterChoice == 1 && activityFilterChoice == 0) {
            displayActivity(activities.getShortestDistance(ActivityList.ActivityType.BIKING).getTitle());
        } else if (dataFilterChoice == 1 && activityFilterChoice == 1) {
            displayActivity(activities.getShortestDistance(ActivityList.ActivityType.RUNNING).getTitle());
        } else if (dataFilterChoice == 1 && activityFilterChoice == 2) {
            displayActivity(activities.getShortestDistance(ActivityList.ActivityType.BOTH).getTitle());
        } else if (dataFilterChoice == 2 && activityFilterChoice == 0) {
            displayActivity(activities.getLongestTime(ActivityList.ActivityType.BIKING).getTitle());
        } else if (dataFilterChoice == 2 && activityFilterChoice == 1) {
            displayActivity(activities.getLongestTime(ActivityList.ActivityType.RUNNING).getTitle());
        } else if (dataFilterChoice == 2 && activityFilterChoice == 2) {
            displayActivity(activities.getLongestTime(ActivityList.ActivityType.BOTH).getTitle());
        } else if (dataFilterChoice == 3 && activityFilterChoice == 0) {
            displayActivity(activities.getShortestTime(ActivityList.ActivityType.BIKING).getTitle());
        } else if (dataFilterChoice == 3 && activityFilterChoice == 1) {
            displayActivity(activities.getShortestTime(ActivityList.ActivityType.RUNNING).getTitle());
        } else if (dataFilterChoice == 3 && activityFilterChoice == 2) {
            displayActivity(activities.getShortestTime(ActivityList.ActivityType.BOTH).getTitle());
        }
    }
}
