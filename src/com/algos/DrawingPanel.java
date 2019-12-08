package com.algos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static com.algos.Shamos.getDiameterPoints;

class DrawingPanel extends JPanel
        implements MouseListener, MouseMotionListener
{
    private static final Dimension MIN_DIM = new Dimension(300, 300);
    private static final Dimension MAX_DIM = new Dimension(800, 800);
    private static final Dimension PREF_DIM = new Dimension(500, 500);

    static boolean polygonIsNowComplete = false;
    static boolean drawDiameter = false;

    /**
     The 'dummy' point tracking the mouse.
     */
    static final Point trackPoint = new Point(0,0, 0);

    static PointList<Point> points = new PointList<>();

    static PointList<Point> diameterPoints = getDiameterPoints();

    DrawingPanel()
    {
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int numPoints = points.size();
        if (numPoints == 0)
            return; // nothing to draw

        Point prevPoint = points.get(0);

        // draw polygon
        for (Object point : points) {
            Point curPoint = (Point) point;
            draw(g, prevPoint, curPoint);
            prevPoint = curPoint;
        }

        // now draw tracking line or complete the polygon
        if (polygonIsNowComplete)
            draw(g, prevPoint, points.get(0));
        else
            draw(g, prevPoint, trackPoint);

        if(drawDiameter) drawLine(g, diameterPoints.get(0), diameterPoints.get(1), Color.red);
    }

    public void mouseClicked(MouseEvent evt)
    {
        int x = evt.getX();
        int y = evt.getY();

        switch (evt.getClickCount())
        {
            case 1: // single-click
                if (polygonIsNowComplete)
                {
                    points.clear();
                    polygonIsNowComplete = false;
                }
                points.add(new Point(x, y, points.size()));
                repaint();
                break;

            case 2: // double-click
                polygonIsNowComplete = true;
                /*points.add(new Point(x, y, points.size()));*/
                repaint();
                break;

            default:
                break;
        }
    }

    public void mouseMoved(MouseEvent evt)
    {
        trackPoint.x = evt.getX();
        trackPoint.y = evt.getY();
        repaint();
    }

    private void draw(Graphics g, Point p1, Point p2)
    {
        int x1 = p1.x;
        int y1 = p1.y;

        int x2 = p2.x;
        int y2 = p2.y;

        drawLine(g, p1, p2, Color.green.darker());

        // draw the line first so that the points
        // appear on top of the line ends, not below

        g.setColor(Color.red);
        g.fillOval(x1, y1, 8, 8);
        g.drawString(String.valueOf(p1.id), x1, y1);

        g.setColor(Color.blue);
        g.fillOval(x2, y2, 8, 8);
        g.drawString(String.valueOf(p2.id), x2, y2);
    }

    private void drawLine(Graphics g, Point p1, Point p2, Color color) {
        int x1 = p1.x;
        int y1 = p1.y;

        int x2 = p2.x;
        int y2 = p2.y;

        g.setColor(color);

        g.drawLine(x1 + 3, y1 + 3, x2 + 3, y2 + 3);
        g.drawLine(x1 + 4, y1 + 4, x2 + 4, y2 + 4);
        g.drawLine(x1 + 5, y1 + 5, x2 + 5, y2 + 5);

    }

    public Dimension getMinimumSize()
    { return MIN_DIM; }

    public Dimension getPreferredSize()
    { return PREF_DIM; }

    public void mouseDragged(MouseEvent evt)
    { /* do nothing */ }

    public void mousePressed(MouseEvent evt)
    { /* do nothing */ }

    public void mouseReleased(MouseEvent evt)
    { /* do nothing */ }

    public void mouseEntered(MouseEvent evt)
    { /* do nothing */ }

    public void mouseExited(MouseEvent evt)
    { /* do nothing */ }
}
