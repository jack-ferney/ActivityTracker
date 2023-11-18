package ui;

import ui.options.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

// Represents an instance of an activity tracker with an activity list and goals using GUI based interactions
public class ActivityTrackerGUI extends ActivityTracker {

    public static final int WIDTH = 1200;
    public static final int HEIGHT = 875;

    private List<Option> options;
    private Option activeOption;
 
    // EFFECTS: creates a new instance of activity tracker with no activities in the list and
    //          uses a graphical base interaction
    public ActivityTrackerGUI() {
        super();
        setUp();
    }

    private void setUp() {
        activeOption = null;
        options = new ArrayList<>();
        startGraphics();
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
        JPanel activeArea = new JPanel();
        activeArea.setLayout(new GridLayout(0,1));
        activeArea.setSize(new Dimension(0,0));
        add(activeArea);
    }

    private void createOptions() {
        JPanel optionsArea = new JPanel();
        optionsArea.setLayout(new GridLayout(0,1));
        optionsArea.setSize(new Dimension(0, 0));
        add(optionsArea, BorderLayout.WEST);

        AddOption addOption = new AddOption(this, optionsArea);
        options.add(addOption);

        ViewOption viewOption = new ViewOption(this, optionsArea);
        options.add(viewOption);

        SearchOption searchOption = new SearchOption(this, optionsArea);
        options.add(searchOption);

        GetEditOption getEditOption = new GetEditOption(this, optionsArea);
        options.add(getEditOption);

        GoalOption goalOption = new GoalOption(this, optionsArea);
        options.add(goalOption);

        SaveLoadOption saveLoadOption = new SaveLoadOption(this, optionsArea);
        options.add(saveLoadOption);

        QuitOption quitOption = new QuitOption(this, optionsArea);
        options.add(quitOption);

        setActiveOption(viewOption);
    }

    public void setActiveOption(Option option) {
        if (activeOption != null) {
            activeOption.deactivate();
        }
        option.activate();
        activeOption = option;
    }
}
