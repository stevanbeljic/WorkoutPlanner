package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExerciseCard extends JPanel {

    private static final long serialVersionUID = 1L;
    private ArrayList<Set> setsArray;

    public ExerciseCard(Exercise e) {
        setMaximumSize(new Dimension(820, 100));
        setLayout(new BorderLayout());

        add(Box.createHorizontalStrut(50), BorderLayout.EAST);
        
        JLabel exNameLabel = new JLabel(e.getName());
        exNameLabel.setBounds(21, 115, 97, 24);
        exNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        exNameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(exNameLabel, BorderLayout.CENTER);

        setsArray = e.getSets();

        JPanel viewportView = new JPanel();
        viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.Y_AXIS));

        for (int i = 0; i < setsArray.size(); i++) {
            JLabel setIndexLabel = new JLabel("Set " + (i + 1) + ": " + setsArray.get(i).getReps() + " repetitions");
            viewportView.add(setIndexLabel);
        }

        JScrollPane setScrollPane = new JScrollPane();
        setScrollPane.setBounds(150, 0, 676, 249);
        setScrollPane.setPreferredSize(new Dimension(400, 100));
        setScrollPane.setViewportView(viewportView);
        add(setScrollPane, BorderLayout.EAST);

        revalidate();
    }

}
