/*
 * name: beftu sultan
 * date: 21 oct 2025
 * file name: MyPoint.java
 * description: part of Lab11.java
 */

public class MyPoint {
    double x;
    double y;

    // no arg constructor
    public MyPoint() {
        x = 0;
        y = 0;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    // constructor with coordinate args
    public MyPoint(double xValue, double yValue) {
        x = xValue;
        y = yValue;
    }

    // return distance from object to x,y coordinates
    double distance(double xValue, double yValue) {

        return Math.hypot(xValue - this.getX(), yValue - this.getY());
    }

    // return distance from object to object
    double distance(MyPoint otherPoint) {

        return Math.hypot(otherPoint.getX() - this.getX(), otherPoint.getY() - this.getY());
    }
}

// subclass of MyPoint
class NewPoint extends MyPoint {

    // keep track of how many NewPoint objects have been created since program start
    private static int numberOfObjects = 0;

    public NewPoint() {
        super();
        // every time instance of newpoint is created increment numofobjects
        numberOfObjects++;
    }

    // set point with specified coordinates
    public NewPoint(double xValue, double yValue) {
        super(xValue, yValue);
        // every time instance of newpoint is created increment numofobjects
        numberOfObjects++;
    }

    public static int getNumberOfObjects() {
        return numberOfObjects;
    }

}