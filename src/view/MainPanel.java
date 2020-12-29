package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;

public class MainPanel extends JPanel {

    private int width;
    private int height;

    private NorthPanel northPanel;
    private SouthPanel southPanel;

    private Controller controller;

    public MainPanel(Controller controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setupPanel();
    }

    private void setupPanel() {
        setBackground(Color.WHITE);

        // set the type of Layout for this panel
        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        Border border = this.getBorder();
        // let the panel have a border, 10 pixels on all sides
        Border margin = BorderFactory.createEmptyBorder(10, 10 , 10, 10);
        setBorder(new CompoundBorder(border, margin));

        northPanel = new NorthPanel(controller);
        northPanel.setPreferredSize(new Dimension(400,400)); // before 400, 400
        add(northPanel, layout.NORTH);

        southPanel = new SouthPanel(controller);
        add(southPanel, layout.SOUTH);
    }

    public NorthPanel getNorthPanel() {
        return northPanel;
    }

    public SouthPanel getSouthPanel() {
        return southPanel;
    }

}
