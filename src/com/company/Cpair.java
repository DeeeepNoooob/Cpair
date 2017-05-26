package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cpair {

    ArrayList<Point> points = new ArrayList<Point>();
    ArrayList<Point> result;

    public void point(int t) {
        Random in = new Random();
        for (int i = 0; i < t; i++) {
            int a = -in.nextInt(100) + 50;
            int b = -in.nextInt(100) + 50;
            this.points.add(new Point(a, b, i));
        }
        Collections.sort(points, new SortByX());
    }

    public float lByX(ArrayList<Point> p) {
        if(p.size()>0){
        int m = (int) (p.size() / 2 + 0.5);
        float l = (float)(p.get(m).getX() + p.get(m - 1).getX()) / 2;
        return l;}

        else return 0 ;
    }

    public float distance(Point p1, Point p2) {
        int dx = p1.getX() - p2.getX();
        int dy = p1.getY() - p2.getY();
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public ArrayList<Point> min(ArrayList<Point> points1, ArrayList<Point> points2) {
        Point p1 = points1.get(0);
        Point p2 = points1.get(1);
        Point p3 = points2.get(0);
        Point p4 = points2.get(1);
        float d1 = distance(p1, p2);
        float d2 = distance(p3, p4);
        if (d1 < d2)
            return points1;
        else
            return points2;
    }

    public ArrayList<Point> find(ArrayList<Point> p) {
        if (p.size() ==1 ) {
            Point po = new Point(100, 100, 100);

            p.add(po);
            return p;
        }
        if (p.size() == 2)
            return p;
        if (p.size() == 3) {
            ArrayList<Point> min = new ArrayList<>();
            Point p1 = p.get(0);
            Point p2 = p.get(1);
            Point p3 = p.get(2);
            float d1 = distance(p1, p2);
            float d2 = distance(p1, p3);
            float d3 = distance(p2, p3);
            float d = d1 < d2 ? (d1 < d3 ? d1 : d3) : (d2 < d3 ? d2 : d3);
            if (d == d1) {
                min.add(p1);
                min.add(p2);
            }
            if (d == d2) {
                min.add(p1);
                min.add(p3);
            }
            if (d == d3) {
                min.add(p2);
                min.add(p3);
            }
            return min;
        }
        ArrayList<Point> left = new ArrayList<>();
        ArrayList<Point> right = new ArrayList<>();
        ArrayList<Point> candidatel = new ArrayList<>();
        ArrayList<Point> candidater = new ArrayList<>();
        float l = lByX(p);
//        System.out.println();
//        System.out.println("l:"+l);
        for (int i = 0; i < p.size(); i++) {
            if (p.get(i).getX() < l) {
                left.add(p.get(i));
//                System.out.println("l:" + left.get(i).getX() + "," + left.get(i).getY());

            } else
                right.add(p.get(i));

        }

        ArrayList<Point> cleft = new ArrayList<>(find(left));
        ArrayList<Point> cright = new ArrayList<>(find(right));
        float dl = distance(cleft.get(0), cleft.get(1));
        float dr = distance(cright.get(0), cright.get(1));
        float dmin = dl < dr ? dl : dr;
        ArrayList<Point> m;
        if (dl < dr)
            m = new ArrayList<>(cleft);
        else
            m = new ArrayList<>(cright);
//        System.out.println("m:" + m.get(1).getX());
        for (int i = 0; i < p.size(); i++) {
            int x = p.get(i).getX();
            if (Math.abs(l - x) <= dmin && x < l) {
                candidatel.add(p.get(i));

            }
            if (Math.abs(l - x) <= dmin && x >= l) {
                candidater.add(p.get(i));
            }
        }
        for (int i = 0; i < candidatel.size(); i++) {
//            System.out.println("lcandi:" + candidatel.get(i).getX() + "," + candidatel.get(i).getY());
        }
        for (int i = 0; i < candidater.size(); i++) {
//            System.out.println("rcandi:" + candidater.get(i).getX() + "," + candidater.get(i).getY());
        }


        float dis = 10000;
        float min = 10000;
        ArrayList<Point> d = new ArrayList<>();
        d.add(this.points.get(0));
        d.add(this.points.get(1));
        Collections.sort(candidatel, new SortByY());
        Collections.sort(candidater, new SortByY());
        for (int i = 0; i < candidatel.size(); i++) {
            int y = candidatel.get(i).getY();
            for (int j = 0; j < candidater.size(); j++) {
                if (candidater.get(j).getY() >= y - dmin && candidater.get(j).getY() <= y + dmin) {
                    dis = distance(candidatel.get(i), candidater.get(j));
//                    System.out.println("dis:" + dis);
                    if (dis <= min) {
                        min = dis;
                        d.set(0, candidatel.get(i));
                        d.set(1, candidater.get(j));
                    }
                }
            }
        }
//        System.out.println("dmin:" + dmin + "  " + "min:" + min);
        if (dmin <= min)
            return m;
        else
            return d;

    }

    public Cpair(int n) {
        point(n);
        this.result = new ArrayList<Point>(find(points));
    }

    public static void main(String[] args) {
        Cpair c = new Cpair(32);
        System.out.println(c.distance(c.result.get(0), c.result.get(1)));
        System.out.println(c.result.get(0).getX() + "," + c.result.get(0).getY() + " " + c.result.get(1).getX() + "," + c.result.get(1).getY());
        new Diagram(c);
    }
}
