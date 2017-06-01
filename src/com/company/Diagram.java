package com.company;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 * Created by Yk on 2017/5/22.
 */
public class Diagram extends JFrame {
    ArrayList<Point> all = new ArrayList<Point>();
    ArrayList<Point> result = new ArrayList<Point>();
    private int rw = 200;
    private Graphics g;
    private Color rectColor = new Color(0xf5f5f5);
    private int x1, y1;
    private int x2, y2;

    public Diagram(Cpair ch) {
        x1 = 6 * ch.result.get(0).getX()+ 400;
        y1 = -6 * ch.result.get(0).getY() + 400;
        x2 = 6 * ch.result.get(1).getX() + 400;
        y2 = -6 * ch.result.get(1).getY() + 400;
        result.addAll(ch.result);
        all.addAll(ch.points);
//        result.addAll(ch.result);
        setLocationRelativeTo(getOwner());
        setTitle("Closest");
        Container p = getContentPane();
        setSize(800, 800);
        setVisible(true);
        p.setBackground(rectColor);
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        g = this.getGraphics();
        paint(g);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
//        g.drawRect(50,50,rw,rw);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2.0f));
        g2.setColor(Color.black);
        System.out.println();
        for (int i = 0; i < all.size(); i++) {
            int x = 6 * all.get(i).getX() + 400;
            int y = -6 * all.get(i).getY() + 400;
            System.out.print(all.get(i).getX() + "," + all.get(i).getY()+" ");
            g.drawLine(x, y, x, y);
        }
        Graphics2D g3 = (Graphics2D) g;
        g3.setStroke(new BasicStroke(4.0f));
        g3.setColor(Color.red);
        g3.drawLine(x1, y1, x1, y1);
        g3.drawLine(x2, y2, x2, y2);

    }

}
