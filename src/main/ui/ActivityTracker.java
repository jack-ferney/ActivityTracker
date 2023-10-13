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

    private void goals() {

    }

    private void getActivity() {
        if (activities.getShortestTime() == null) {
            System.out.println("List is empty. Try adding activities first!");
        } else {
            List<String> titles = activities.getListOfTitles();
            int number = 1;
            for (String title : titles) {
                System.out.println(number + title);
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

    private void editActivity(int choice) {
        Activity chosenActivity = activities.get(choice - 1);
        System.out.println("\n" + chosenActivity.getTitle());
        System.out.println("\tDistance: " + chosenActivity.getDistance());
        System.out.println("\tTime: " + chosenActivity.getTime());
        System.out.println("\tPace: " + chosenActivity.getPace());
        System.out.println("\tAverage Speed: " + chosenActivity.getAverageSpeed());
        System.out.println("\nDo you want to edit the details of this activity? (type yes)");
        String yesNoChoice = input.next();
        yesNoChoice.toLowerCase();
        if (yesNoChoice.equals("yes")) {
            System.out.println("\nWhat do you want to edit?");
            System.out.println("\tD - distance");
            System.out.println("\tT - time");
            String editChoice = input.next();
            editChoice.toLowerCase();
            if (editChoice.equals("D")) {
                System.out.println("New distance: ");
                float newDistance = input.nextFloat();
                chosenActivity.setDistance(newDistance);
            } else if (editChoice.equals("T")) {
                System.out.println("New time: ");
                float newTime = input.nextFloat();
                chosenActivity.setTime(newTime);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }


    private void searchList() {
        if (activities.getShortestTime() == null) {
            System.out.println("List is empty. Try adding activities first!");
        } else {
            System.out.println("Search by (d)istance or (t)ime?");
            String search = input.next();
            search.toLowerCase();
            if (search.equals("d")) {
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
            } else if (search.equals("t")) {
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
        }
    }

    private void addActivity() {
        System.out.println("Enter distance for activity:");
        float distance = input.nextFloat();
        if (distance < 0) {
            System.out.println("Distance must be greater than 0!");
        } else {
            System.out.println("Enter time for activity:");
            float time = input.nextFloat();
            if (time < 0) {
                System.out.println("Time must be greater than 0!");
            } else {
                System.out.println("Enter title for activity:");
                String title = input.next();
                System.out.println("Enter type of activity (b or r)!");
                String type = input.next();
                type.toLowerCase();
                if (type.equals("b")) {
                    activities.addActivity(new BikingActivity(distance, time, title));
                } else if (type.equals("r")) {
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