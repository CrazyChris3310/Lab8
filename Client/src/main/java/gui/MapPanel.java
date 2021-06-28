package gui;

import dragon.Coordinates;
import dragon.Dragon;
import utilities.Process;

import javax.swing.*;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class MapPanel extends JPanel {

    long maxX;
    long minX;
    float maxY;
    float minY;

    Process process;

    public MapPanel(Process process) {
        this.process = process;
        setPreferredSize(new Dimension(2000,2000));
        setLayout(new BorderLayout());

        setVisible(true);
    }

    public void defineBorderCords() {


    }

    public void paint(Graphics g) {
        g.translate(getWidth()/2, getHeight()/2);

        Graphics2D g2 = (Graphics2D) g;
        g2.scale(1,1);



        drawDragon(-55, 55, g2, new Color(123, 24, 198));
        drawDragon(-48, -50, g2, new Color(55, 219, 205));
        drawDragon(0, 0, g2, new Color(53, 219, 42));
        drawDragon(2, 2, g2, new Color(220, 25, 70));
        drawDragon(500, -150, g2, new Color(220, 25, 70));
    }

    public void drawCoordinates(Graphics2D g2) {

    }

    public void drawDragon(double x, double y, Graphics2D g2, Color color) {
        g2.setPaint(color);
        g2.fill(new Ellipse2D.Double(x, y, 25, 50));
        g2.fill(new Ellipse2D.Double(x + 3, y - 16, 19, 19));
//        g2.setPaint(Color.RED);
        g2.fill(new Polygon(new int[]{(int)x + 21, (int)x + 34, (int)x + 47},
                new int[]{(int)y + 25, (int)y+2, (int)y + 25}, 3));
        g2.fill(new Polygon(new int[]{(int)x + 4, (int)x - 9, (int)x - 22},
                new int[]{(int)y + 25, (int)y+2, (int)y + 25}, 3));
        g2.fill(new Ellipse2D.Double(x + 5, y + 40, 5, 20));
        g2.fill(new Ellipse2D.Double(x + 15, y + 40, 5, 20));
    }
}
