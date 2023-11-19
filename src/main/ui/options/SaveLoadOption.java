package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveLoadOption extends Option implements ActionListener {

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
        button.addActionListener(this);
        addToParent(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tracker.saveLoadAction();
    }
}
