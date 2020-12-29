package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;


public class SouthPanel extends JPanel {

    private JLabel lblShotStatus;
    private JLabel lblNumOfShots;
    private JLabel lblTotShipPoints;

    public SouthPanel(Controller controller) {
        setupPanel();
    }

    private void setupPanel() {
        // layout till south-panelen
        GridLayout experimentLayout = new GridLayout(3,0);
        setLayout(experimentLayout);

        // margin till lblShotStatus
        Border border = this.getBorder();
        Border margin = BorderFactory.createEmptyBorder(0, 0 , 5, 0);

        lblShotStatus = new JLabel("Player move status:"); // Speldragstatus
        lblShotStatus.setBorder(new CompoundBorder(border, margin));
        add(lblShotStatus);

        lblTotShipPoints = new JLabel("Remaining ships points: 15"); // Kvarstående skepp poäng
        add(lblTotShipPoints);

        lblNumOfShots = new JLabel("Num of shots: 0"); // Antal skott
        add(lblNumOfShots);
    }


    public void updateNumOfShotsGUI(int numOfShots) {
        lblNumOfShots.setText("Num of shots: " + numOfShots);
    }

    public void updateGameStatusGUI(String message) {
        lblShotStatus.setText("Player move status: " + message);
    }

    public void updateTotShipsLifePointsGUI(int points) {
        lblTotShipPoints.setText("Remaining ships points: " + points);
    }
}