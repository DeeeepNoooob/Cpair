package com.company;
import java.util.Comparator;

/**
 * Created by Yk on 2017/5/22.
 */
class SortByX implements Comparator<Object>{
    public int compare(Object obj1,Object obj2){
        Point point1=(Point)obj1;
        Point point2=(Point)obj2;
        if(point1.getX()>point2.getX())
            return 1;
        else
            return -1;
    }
}
