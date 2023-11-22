package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;

// Represents an option button that performs an action that the user can use in the GUI interface
public abstract class Option {

    protected Font font;
    protected JButton button;
    protected ActivityTrackerGUI tracker;
    private boolean active;

    // EFFECTS: creates an option that is added to tracker and parent JComponent while creating this option as a button
    public Option(ActivityTrackerGUI tracker, JComponent parent) {
        this.tracker = tracker;
        this.font = new Font(Font.SANS_SERIF, 1, 23);
        createButton(parent);
    }

    // EFFECTS: creates button that calls actionPerformed method when clicked ands the button to the parent JComponent
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
