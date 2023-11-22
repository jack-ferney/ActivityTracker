package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents an "Add Activity" button that allows the user to add an activity using the GUI interface
public class AddOption extends Option implements ActionListener {

    // EFFECTS: creates an AddOption that gets added to the parent JComponent and creates the button
    public AddOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add New Activity");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        button.addActionListener(this);
        addToParent(parent);
    }

    // EFFECTS: calls the addActivity method on the tracker this button is associated with when pressed
    public void actionPerformed(ActionEvent e) {
        tracker.addActivity();
    }
}
