package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalOption extends Option {

    private Shape shapeToGoal;

    public GoalOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToGoal = null;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Goals");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new GoalOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class GoalOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(GoalOption.this);
        }
    }
}
