package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitOption extends Option {

    private Shape shapeToQuit;

    public QuitOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToQuit = null;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Quit App");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new QuitOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class QuitOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(QuitOption.this);
        }
    }
}
