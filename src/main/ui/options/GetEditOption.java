package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetEditOption extends Option {

    private Shape shapeToEdit;

    public GetEditOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToEdit = null;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Get/Edit Activity");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new GetEditOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class GetEditOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(GetEditOption.this);
        }
    }
}
