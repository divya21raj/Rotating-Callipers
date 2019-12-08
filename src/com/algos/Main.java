package com.algos;

import com.algos.Gui.ControlPanel;
import com.algos.Gui.DrawingPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        DrawingPanel drawingPanel = new DrawingPanel();
        JFrame frame = new JFrame("Rotating Calipers");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(drawingPanel);
        frame.add(new ControlPanel(drawingPanel), BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
    }

}
