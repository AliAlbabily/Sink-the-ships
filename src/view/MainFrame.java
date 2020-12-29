package view;

import javax.swing.*;
import controller.Controller;

public class MainFrame extends JFrame {

    private Controller controller;
    private MainPanel panel;

    private final int width = 500;
    private final int height = 530;

    public MainFrame(Controller controller) {
        this.controller = controller;

        setupFrame();
    }

    private void setupFrame() {
        panel = new MainPanel(controller, width, height);
        add(panel); // adds the panel to the frame

        setTitle("Sink The Ships");
        setSize(width,height);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // avslutar programmet när man stänger av fönstret
        setLocation(200, 200);
        setResizable(false);
        setVisible( true ); // fönstret görs synligt
    }

    public void closeMainFrameWindow() {
        setVisible(false); // hide window
        dispose(); // Destroy the JFrame object, (close window)
    }

    public MainPanel getPanel() {
        return panel;
    }

}