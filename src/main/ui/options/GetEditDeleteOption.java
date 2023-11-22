package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a "Get/Edit/Delete Activity" button that allows the user to add an activity using the GUI interface
public class GetEditDeleteOption extends Option implements ActionListener {

    // EFFECTS: creates a GetEditDeleteOption that gets added to the parent JComponent and creates the button
    public GetEditDeleteOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Get/Edit/Delete Activity");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        button.addActionListener(this);
        addToParent(parent);
    }

    // EFFECTS: calls the getActivity method on the tracker this button is associated with when pressed
    public void actionPerformed(ActionEvent e) {
        tracker.getActivity();
    }
}
