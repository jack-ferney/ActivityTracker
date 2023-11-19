package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;

public abstract class Option {

    protected Font font;
    protected JButton button;
    protected ActivityTrackerGUI tracker;
    private boolean active;

    public Option(ActivityTrackerGUI tracker, JComponent parent) {
        this.tracker = tracker;
        this.font = new Font(Font.SANS_SERIF, 1, 23);
        createButton(parent);
    }

    // getters
    public boolean isActive() {
        return active;
    }

    // EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    // EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }
}
