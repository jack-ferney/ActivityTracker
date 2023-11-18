package ui.options;

import ui.ActivityTrackerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchOption extends Option {

    private Shape shapeToSearch;

    public SearchOption(ActivityTrackerGUI tracker, JComponent parent) {
        super(tracker, parent);
        shapeToSearch = null;
    }

    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Search Activities");
        button.setBackground(new Color(255, 150, 31));
        button.setPreferredSize(new Dimension(300,875));
        button.setFont(font);
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        button.addActionListener(new SearchOptionClickHandler());
    }

    @Override
    protected void displayForOption() {

    }

    private class SearchOptionClickHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tracker.setActiveOption(SearchOption.this);
        }
    }
}
