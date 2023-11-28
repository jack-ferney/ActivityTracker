package ui;

import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("What type of interface do you want to use? (G)raphical or (C)onsol based? \n");
        String choice = input.next();
        choice = choice.toLowerCase();
        if (choice.equals("g")) {
            ActivityTrackerGUI liveTracker = new ActivityTrackerGUI();
        } else if (choice.equals("c")) {
            ActivityTracker liveTracker = new ActivityTracker();
            liveTracker.runActivityTracker();
        } else {
            System.out.println("\n\nINVALID CHOICE!");
        }
    }
}
