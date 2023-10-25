package ui;

import model.*;

import java.util.List;
import java.util.Scanner;

public class ActivityTracker {
    private ActivityList activities;
    private RunningDistanceGoal runningDGoal;
    private RunningTimeGoal runningTGoal;
    private BikingDistanceGoal bikingDGoal;
    private BikingTimeGoal bikingTGoal;
    private Scanner input;

    // EFFECTS: runs the activity tracker
    public ActivityTracker() {
        runActivityTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runActivityTracker() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                completeCommand(command);
            }
        }

        System.out.println("\n Keep Working Out!!!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command according to input
    private void completeCommand(String command) {
        if (command.equals("a")) {
            addActivity();
        } else if (command.equals("s")) {
            searchList();
        } else if (command.equals("l")) {
            getActivity();
        } else if (command.equals("g")) {
            goals();
        } else {
            System.out.println("Invalid input mate!");
        }
    }

    // MODIFIES: list of goals
    // EFFECTS: allows user to complete goals or edit goals
    private void goals() {
        printGoals();
        System.out.println("\n(E)dit goals or (m)ark as completed? ");
        String choice = input.next();
        choice = choice.toLowerCase();
        if (choice.equals("m")) {
            if (activities.size() == 0) {
                System.out.println("You have no activities. Try adding some activities and then try again!");
            } else {
                completeGoals();
            }
        } else if (choice.equals("e")) {
            editGoals();
        }
    }

    // EFFECTS: prints goals and current status
    private void printGoals() {
        System.out.println("Biking Distance Goal: " + this.bikingDGoal.getGoalValue() + " km." + " Completed? "
                + this.bikingDGoal.getGoalStatus());
        System.out.println("Biking Time Goal: " + this.bikingTGoal.getGoalValue() + " mins." + " Completed? "
                + this.bikingTGoal.getGoalStatus());
        System.out.println("Running Distance Goal: " + this.runningDGoal.getGoalValue() + " km." + " Completed? "
                + this.runningDGoal.getGoalStatus());
        System.out.println("Running Time Goal: " + this.runningTGoal.getGoalValue() + " mins." + " Completed? "
                + this.runningTGoal.getGoalStatus());
    }

    // MODIFIES: list of goals
    // EFFECTS: allows user to choose which goal they wish to edit
    private void editGoals() {
        System.out.println("\nChoose which goal you wish to edit: ");
        System.out.println("\n 1) Biking Distance Goal " + this.bikingDGoal.getGoalValue() + " km");
        System.out.println("\n 2) Biking Time Goal " + this.bikingTGoal.getGoalValue() + " mins");
        System.out.println("\n 3) Running Distance Goal " + this.runningDGoal.getGoalValue() + " km");
        System.out.println("\n 4) Running Time Goal " + this.runningTGoal.getGoalValue() + " mins");
        String choice = input.next();
        if (choice.equals("1")) {
            System.out.println("\nNew goal in km? ");
            float value = input.nextFloat();
            this.bikingDGoal.editGoal(value);
        } else if (choice.equals("2")) {
            System.out.println("\nNew goal in mins? ");
            float value = input.nextFloat();
            this.bikingTGoal.editGoal(value);
        } else if (choice.equals("3")) {
            System.out.println("\nNew goal in km? ");
            float value = input.nextFloat();
            this.runningDGoal.editGoal(value);
        } else if (choice.equals("4")) {
            System.out.println("\nNew goal in mins? ");
            float value = input.nextFloat();
            this.runningTGoal.editGoal(value);
        }
    }

    // MODIFIES: list of goals
    // EFFECTS: if there is an activity in the list of activities that fulfills the requirement set by a goal then that
    //          goal is completed
    private void completeGoals() {
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
        System.out.println("If you have an activity that completed the goal then it has been marked as completed!\n");
        printGoals();
    }

    // MODIFIES: this
    // EFFECTS: gets selected activity so the user can view its details or edits details or delete it from the list
    private void getActivity() {
        if (activities.getShortestTime(ActivityList.ActivityType.BOTH) == null) {
            System.out.println("List is empty. Try adding activities first!");
        } else {
            List<String> titles = activities.getListOfTitles();
            int number = 1;
            for (String title : titles) {
                System.out.println(number + ")  " + title);
                number++;
            }
            System.out.println("\n Choose which activity you wish to access to edit or view details or delete! "
                    + "(By number)");
            int choice = input.nextInt();
            if ((choice > titles.size()) || (choice < 0)) {
                System.out.println("Invalid choice! ");
            } else {
                editActivity(choice);
            }
        }
    }

    // MODIFIES: selected activity
    // EFFECTS: allows user to edit selected activity
    private void editActivity(int choice) {
        Activity chosenActivity = activities.get(choice - 1);
        displayActivityDetails(chosenActivity);
        String decision = input.next();
        decision = decision.toLowerCase();
        if (decision.equals("e")) {
            editDetails(chosenActivity);
        } else if (decision.equals("d")) {
            deleteActivity(chosenActivity);
        }
    }

    // MODIFIES: selected activity
    // EFFECTS: allows user to select what details within the selected activity they wish to edit
    private void editDetails(Activity chosenActivity) {
        System.out.println("\nWhat do you want to edit?");
        System.out.println("\tD - distance");
        System.out.println("\tT - time");
        String editChoice = input.next();
        editChoice = editChoice.toLowerCase();
        if (editChoice.equals("d")) {
            System.out.println("New distance (km): ");
            float newDistance = input.nextFloat();
            chosenActivity.setDistance(newDistance);
        } else if (editChoice.equals("t")) {
            System.out.println("New time (mins): ");
            float newTime = input.nextFloat();
            chosenActivity.setTime(newTime);
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // EFFECTS: displays chosen activity's details
    private void displayActivityDetails(Activity chosenActivity) {
        System.out.println("\n" + chosenActivity.getTitle());
        System.out.println("\tDistance: " + chosenActivity.getDistance() + " km");
        System.out.println("\tTime: " + chosenActivity.getTime() + " mins");
        System.out.println("\tPace: " + chosenActivity.getPace() + " mins/km");
        System.out.println("\tAverage Speed: " + (chosenActivity.getAverageSpeed() * 60) + " km/h");
        System.out.println("\nDo you want to (E)dit the details of this activity, (D)elete this activity"
                + " or (R)eturn to the menu?");
    }

    // EFFECTS: searches list for activity with user's chosen filter
    private void searchList() {
        if (activities.getShortestTime(ActivityList.ActivityType.BOTH) == null) {
            System.out.println("List is empty. Try adding activities first!");
        } else {
            System.out.println("Search by (d)istance or (t)ime?");
            String search = input.next();
            search = search.toLowerCase();
            System.out.println("Search (a)ll activities, only (b)iking activities or only (r)unning activities: ");
            String activitiyType = input.next();
            activitiyType = activitiyType.toLowerCase();
            if (activitiyType.equals("a") || activitiyType.equals("b") || activitiyType.equals("r")) {
                if (search.equals("d")) {
                    getActivityByDistance(activitiyType);
                } else if (search.equals("t")) {
                    getActivityByTime(activitiyType);
                }
            } else {
                System.out.println("Invalid activity choice!");
            }
        }
    }

    // EFFECTS: Returns activity within activity type with highest or lowest time based on user input
    private void getActivityByTime(String activityType) {
        System.out.println("(H)ighest time or (L)owest time? ");
        String type = input.next();
        type = type.toLowerCase();
        if (type.equals("h")) {
            getLongestTime(activityType);
        } else if (type.equals("l")) {
            getShortestTime(activityType);
        } else {
            System.out.println("Invalid input");
        }
    }

    // EFFECTS: gives the activity within the given activity type that has the longest time value
    private void getLongestTime(String activityType) {
        if (activityType.equals("a")) {
            System.out.println("Activity title with longest time: "
                    + activities.getLongestTime(ActivityList.ActivityType.BOTH).getTitle());
        } else if (activityType.equals("b")) {
            System.out.println("Biking activity title with longest time: "
                    + activities.getLongestTime(ActivityList.ActivityType.BIKING).getTitle());
        } else if (activityType.equals("r")) {
            System.out.println("Running activity title with longest time: "
                    + activities.getLongestTime(ActivityList.ActivityType.RUNNING).getTitle());
        }
    }

    // EFFECTS: gives the activity within the given activity type that has the shortest time value
    private void getShortestTime(String activityType) {
        if (activityType.equals("a")) {
            System.out.println("Activity title with longest time: "
                    + activities.getShortestTime(ActivityList.ActivityType.BOTH).getTitle());
        } else if (activityType.equals("b")) {
            System.out.println("Biking activity title with longest time: "
                    + activities.getShortestTime(ActivityList.ActivityType.BIKING).getTitle());
        } else if (activityType.equals("r")) {
            System.out.println("Running activity title with longest time: "
                    + activities.getShortestTime(ActivityList.ActivityType.RUNNING).getTitle());
        }
    }

    // EFFECTS: Returns activity within activity type with highest or lowest distance based on user input
    private void getActivityByDistance(String activityType) {
        System.out.println("(H)ighest distance or (L)owest distance? ");
        String type = input.next();
        type = type.toLowerCase();
        if (type.equals("h")) {
            getLongestDistance(activityType);
        } else if (type.equals("l")) {
            getShortestDistance(activityType);
        } else {
            System.out.println("Invalid input");
        }
    }

    // EFFECTS: gives the activity within the given activity type that has the longest time value
    private void getLongestDistance(String activityType) {
        if (activityType.equals("a")) {
            System.out.println("Activity title with longest distance: "
                    + activities.getLongestDistance(ActivityList.ActivityType.BOTH).getTitle());
        } else if (activityType.equals("b")) {
            System.out.println("Biking activity title with longest distance: "
                    + activities.getLongestDistance(ActivityList.ActivityType.BIKING).getTitle());
        } else if (activityType.equals("r")) {
            System.out.println("Running activity title with longest distance: "
                    + activities.getLongestDistance(ActivityList.ActivityType.RUNNING).getTitle());
        }
    }

    // EFFECTS: gives the activity within the given activity type that has the shortest time value
    private void getShortestDistance(String activityType) {
        if (activityType.equals("a")) {
            System.out.println("Activity title with shortest distance: "
                    + activities.getShortestDistance(ActivityList.ActivityType.BOTH).getTitle());
        } else if (activityType.equals("b")) {
            System.out.println("Biking activity title with shortest distance: "
                    + activities.getShortestDistance(ActivityList.ActivityType.BIKING).getTitle());
        } else if (activityType.equals("r")) {
            System.out.println("Running activity title with shortest distance: "
                    + activities.getShortestDistance(ActivityList.ActivityType.RUNNING).getTitle());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds activity to list based on given distance, time and title
    private void addActivity() {
        System.out.println("Enter distance for activity (km):");
        float distance = input.nextFloat();
        if (distance < 0) {
            System.out.println("Distance must be greater than 0!");
        } else {
            System.out.println("Enter time for activity (mins):");
            float time = input.nextFloat();
            if (time < 0) {
                System.out.println("Time must be greater than 0!");
            } else {
                System.out.println("Enter title for activity:");
                String title = input.next();
                System.out.println("Enter type of activity (b or r)!");
                String type = input.next();
                if (type.equals("b") || type.equals("B")) {
                    activities.addActivity(new BikingActivity(distance, time, title));
                } else if (type.equals("r") || type.equals("R")) {
                    activities.addActivity(new RunningActivity(distance, time, title));
                } else {
                    System.out.println("Invalid type of activity!");
                }
            }
        }
    }

    // MODIFIES: activity list
    // EFFECTS: removes the given activity from the activity list
    private void deleteActivity(Activity chosenActivity) {
        activities.removeActivity(chosenActivity);
    }

    // MODIFIES: this
    // EFFECTS: sets up the activities list
    private void init() {
        activities = new ActivityList();
        runningDGoal = new RunningDistanceGoal(0);
        runningTGoal = new RunningTimeGoal(0);
        bikingDGoal = new BikingDistanceGoal(0);
        bikingTGoal = new BikingTimeGoal(0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays options available to the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tA -> Add Activity to List");
        System.out.println("\tS -> Search List");
        System.out.println("\tL -> Get Activity in List");
        System.out.println("\tG -> View and edit goals");
        System.out.println("\tQ -> Quit");
    }
}