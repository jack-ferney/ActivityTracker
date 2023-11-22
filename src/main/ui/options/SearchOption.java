package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a "Search Activities" button that allows the user to add an activity using the GUI interface
public class SearchOption extends Option implements ActionListener {

    // EFFECTS: creates a SearchOption that gets added to the parent JComponent and creates the button
    public SearchOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Search Activities");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        button.addActionListener(this);
        addToParent(parent);
    }

    // EFFECTS: calls the searchActivities method on the tracker this button is associated with when pressed
    public void actionPerformed(ActionEvent e) {
        tracker.searchActivities();
    }
}
