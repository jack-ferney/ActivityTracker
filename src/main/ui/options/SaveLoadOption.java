package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a "Save/Load Activities" button that allows the user to add an activity using the GUI interface
public class SaveLoadOption extends Option implements ActionListener {

    // EFFECTS: creates a SaveLoadOption that gets added to the parent JComponent and creates the button
    public SaveLoadOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save or Load Activities");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        button.addActionListener(this);
        addToParent(parent);
    }

    // EFFECTS: calls the saveLoadAction method on the tracker this button is associated with when pressed
    public void actionPerformed(ActionEvent e) {
        tracker.saveLoadAction();
    }
}
