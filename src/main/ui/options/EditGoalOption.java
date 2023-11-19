package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGoalOption extends Option implements ActionListener {

    private Shape shapeToGoal;

    public EditGoalOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToGoal = null;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        tracker.editGoals();
    }
}
