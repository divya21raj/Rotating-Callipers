package com.algos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static com.algos.DrawingPanel.*;
import static com.algos.Shamos.getDiameterPoints;

public class ControlPanel extends JPanel{
    Action clearAll = new ClearAction("Clear");
    Action diameterAction = new DiameterAction("Get Diameter");

    DrawingPanel parentPanel;

    ControlPanel(DrawingPanel d) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.lightGray);

        this.add(new JButton(clearAll));
        this.add(new JButton(diameterAction));

        parentPanel = d;
    }

    private class DiameterAction extends AbstractAction {
        public DiameterAction(String name) {super(name);}

        @Override
        public void actionPerformed(ActionEvent e) {
            if(polygonIsNowComplete){
                ArrayList<PointList<Point>> pairs = new Shamos(points).getAllAntipodalPairs();

                diameterPoints = getDiameterPoints();
                drawDiameter = true;
                parentPanel.repaint();
            }
        }
    }
    private class ClearAction extends AbstractAction {

        ClearAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            points.clear();
            trackPoint.x = 0; trackPoint.y = 0;
            polygonIsNowComplete = false;
            drawDiameter = false;
            diameterPoints.clear();
            repaint();
        }
    }
}
