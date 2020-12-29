package view;

import javax.swing.*;
import controller.Controller;

public class EndFrame extends JFrame {

    private Controller controller;
    private EndPanel endPanel;

    private final int width = 280;
    private final int height = 280;

    public EndFrame(Controller controller) {
        this.controller = controller;

        setupFrame();
    }

    public EndPanel getEndPanel(){
        return endPanel;
    }

    private void setupFrame() {
        endPanel = new EndPanel(controller);
        add(endPanel); // adds the panel to the frame

        setTitle("Game Over");
        setSize(width,height);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // avslutar programmet när man stänger av fönstret
        setLocation(200, 200);
        setResizable(false);
        setVisible( true ); // fönstret görs synligt
    }

    public void closeEndFrameWindow() {
        setVisible(false); // hide window
        dispose(); // Destroy the JFrame object, (close window)
    }
}