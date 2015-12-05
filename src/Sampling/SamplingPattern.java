package Sampling;

import bibliothek.Point2;
import java.util.ArrayList;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class SamplingPattern {

    public final ArrayList<Point2> pointList;

    public SamplingPattern(ArrayList<Point2> points) {
        this.pointList = points;
    }

    public SamplingPattern(int points) {

        pointList = new ArrayList<Point2>();

        for (int i = 0; i < points; i++) {
            createRandomPoints();
        }

    }

    private void createRandomPoints() {

        // a random double between -0.5 and 0.5
        double randomX = Math.random() - 0.5;
        double randomY = Math.random() - 0.5;

        Point2 p = new Point2(randomX, randomY);

        pointList.add(p);

    }

    public ArrayList<Point2> getPointsList() {
        return this.pointList;
    }

    public ArrayList<Point2> asDisk() {
        ArrayList<Point2> points = new ArrayList<Point2>();
        for (Point2 p : this.pointList) {
            double x = 2.0 * p.x;
            double y = 2.0 * p.y;
            double r = 0;
            double a = 0;
            if(x > -y){
                if(x > y){
                    r = x;
                    a = y/x;
                }else{
                    r = y;
                    a = 2 - x/y;
                }
            }else{
                if(x < y){
                    r = -x;
                    a = 4+y/x;
                }else{
                    r = -y;
                    if(y != 0){
                        a = 6-x/y;
                    }else{
                        a = 0;
                    }
                }
            }
            double phi = a * Math.PI / 4.0;
            points.add(new Point2(r * Math.cos(phi), r * Math.sin(phi)));
        }
        return points;
    }


}
