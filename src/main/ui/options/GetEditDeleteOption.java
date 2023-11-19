package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GetEditDeleteOption extends Option implements ActionListener {

    private Shape shapeToEdit;

    public GetEditDeleteOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToEdit = null;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        tracker.getActivity();
    }
}
