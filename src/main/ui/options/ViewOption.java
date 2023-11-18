package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewOption extends Option {

    private Shape shapeToView;

    public ViewOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToView = null;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("View Activities");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new ViewOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class ViewOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(ViewOption.this);
        }
    }
}
