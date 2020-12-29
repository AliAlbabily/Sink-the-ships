package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel implements ActionListener {

    private Controller controller;

    private JButton [] buttons;

    private boolean shipIsHit;

    public NorthPanel(Controller controller) {
        this.controller = controller;

        setupPanel();
    }

    private void setupPanel() {
        setBackground(Color.WHITE);

        createButtons();
    }

    private void createButtons() {
        buttons = new JButton[100];

        GridLayout experimentLayout = new GridLayout(10,10);
        setLayout(experimentLayout);

        for (int i= 0; i < 100; i++) {
            buttons[i] = new JButton();

            buttons[i].setPreferredSize(new Dimension(20,20));

            buttons[i].addActionListener(this);  // anropar actionPerformed
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleDynamicButtons(e);
    }

    private void handleDynamicButtons(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        for (int i = 0; i < 100; i++) {
            if (buttons[i].equals(button)) {
                controller.squareButtonPressed(i);
                buttons[i].setEnabled(false); // lås knappen

                // ändra färgen till en ruta beroende på träff
                if(shipIsHit) {
                    buttons[i].setBackground(new Color(161, 56, 56));
                    buttons[i].setText("X");
                } else {
                    buttons[i].setBackground(new Color(171, 232, 253));
                }

                break; // avsluta loopen
            }
        }
    }

    public void updateOnHitStatusGUI(boolean shipIsHit) {
        this.shipIsHit = shipIsHit;
    }

}
