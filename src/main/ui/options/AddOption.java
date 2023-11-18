package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOption extends Option {

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
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new AddOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class AddOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(AddOption.this);
        }
    }
}
