package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents an "Edit Goals" button that allows the user to add an activity using the GUI interface
public class EditGoalsOption extends Option implements ActionListener {

    // EFFECTS: creates an EditGoalsOption that gets added to the parent JComponent and creates the button
    public EditGoalsOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Edit Goals");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        button.addActionListener(this);
        addToParent(parent);
    }

    // EFFECTS: calls the editGoals method on the tracker this button is associated with when pressed
    public void actionPerformed(ActionEvent e) {
        tracker.editGoals();
    }
}
