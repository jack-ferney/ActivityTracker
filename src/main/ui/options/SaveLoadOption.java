package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveLoadOption extends Option {

    private Shape shapeToLoadSave;

    public SaveLoadOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToLoadSave = null;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save or Load Activities");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SaveLoadOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class SaveLoadOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(SaveLoadOption.this);
        }
    }
}
