package ui;

import model.Activity;
import model.ActivityList;
import model.BikingActivity;
import model.RunningActivity;

import java.util.List;
import java.util.Scanner;

public class ActivityTracker {
    private ActivityList activities;
    private Scanner input;

    // EFFECTS: runs the activity tracker
    public ActivityTracker() {
        runActivityTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runActivityTracker() {
        boolean keepGoing = true;
        String command = null;

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

    // EFFECTS: prints "Coming Soon!!!"
    private void goals() {
        System.out.println("Coming Soon!!!");
    }

    // MODIFIES: this
    // EFFECTS: gets selected activity so the user can view its details or edits details
    private void getActivity() {
        if (activities.getShortestTime() == null) {
            System.out.println("List is empty. Try adding activities first!");
        } else {
            List<String> titles = activities.getListOfTitles();
            int number = 1;
            for (String title : titles) {
                System.out.println(number + ")  " + title);
                number++;
            }
            System.out.println("\n Choose which activity you wish to access to edit or view details! (By number)");
            int choice = input.nextInt();
            if (choice > titles.size()) {
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
        String yesNoChoice = input.next();
        yesNoChoice.toLowerCase();
        if (yesNoChoice.equals("yes")) {
            System.out.println("\nWhat do you want to edit?");
            System.out.println("\tD - distance");
            System.out.println("\tT - time");
            String editChoice = input.next();
            editChoice.toLowerCase();
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
    }

    // EFFECTS: displays chosen activity's details
    private void displayActivityDetails(Activity chosenActivity) {
        System.out.println("\n" + chosenActivity.getTitle());
        System.out.println("\tDistance: " + chosenActivity.getDistance() + " km");
        System.out.println("\tTime: " + chosenActivity.getTime() + " mins");
        System.out.println("\tPace: " + chosenActivity.getPace() + " mins/km");
        System.out.println("\tAverage Speed: " + (chosenActivity.getAverageSpeed() * 60) + " km/h");
        System.out.println("\nDo you want to edit the details of this activity? (type yes)");
    }

    // EFFECTS: searches list for activity with user's chosen filter
    private void searchList() {
        if (activities.getShortestTime() == null) {
            System.out.println("List is empty. Try adding activities first!");
        } else {
            System.out.println("Search by (d)istance or (t)ime?");
            String search = input.next();
            search.toLowerCase();
            if (search.equals("d")) {
                getActivityByDistance();
            } else if (search.equals("t")) {
                getActivityByTime();
            }
        }
    }

    // EFFECTS: Returns activity with highest or lowest time based on user input
    private void getActivityByTime() {
        System.out.println("(H)ighest distance or (L)owest time? ");
        String type = input.next();
        type.toLowerCase();
        if (type.equals("h")) {
            System.out.println("Activity title with longest time: " + activities.getLongestTime().getTitle());
        } else if (type.equals("l")) {
            System.out.println("Activity title with shortest time: " + activities.getShortestTime().getTitle());
        } else {
            System.out.println("Invalid input");
        }
    }

    // EFFECTS: Returns activity with highest or lowest distance based on user input
    private void getActivityByDistance() {
        System.out.println("(H)ighest distance or (L)owest distance? ");
        String type = input.next();
        type.toLowerCase();
        if (type.equals("h")) {
            System.out.println("Activity title with longest distance: "
                    + activities.getLongestDistance().getTitle());
        } else if (type.equals("l")) {
            System.out.println("Activity title with shortest distance: "
                    + activities.getShortestDistance().getTitle());
        } else {
            System.out.println("Invalid input");
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

    // MODIFIES: this
    // EFFECTS: sets up the activities list
    private void init() {
        activities = new ActivityList();
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