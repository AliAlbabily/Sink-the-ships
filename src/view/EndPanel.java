package view;

import javax.swing.*;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends JPanel implements ActionListener {

    private JLabel endMessage;
    private JList list = new JList<String[]>();
    private JButton restartGame;

    private Controller controller;

    public EndPanel(Controller controller) {
        this.controller = controller;
        setupPanel();
    }

    private void setupPanel() {
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        endMessage = new JLabel("HIGHSCORE");
        endMessage.setHorizontalAlignment(JLabel.CENTER);
        endMessage.setPreferredSize(new Dimension(0,30));
        add(endMessage, BorderLayout.NORTH);

        add(list, BorderLayout.CENTER);

        restartGame = new JButton("Restart Game");
        restartGame.addActionListener(this); // anropar actionPerformed
        add(restartGame, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == restartGame) {
            controller.restartGameButtonPressed();
        }
    }

    public void updateHighscoreGUI(String[] highscore) {
        list.setListData(highscore);
    }
}