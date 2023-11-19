package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOption extends Option implements ActionListener {

    private Shape shapeToAdd;

    public AddOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToAdd = null;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        tracker.addActivity();
    }
}
