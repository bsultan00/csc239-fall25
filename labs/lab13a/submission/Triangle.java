// create subclass of GeometricObject
public class Triangle extends GeometricObject {
    // default side lengths for triangle object
    private double side1 = 1.0;
    private double side2 = 1.0;
    private double side3 = 1.0;

    // to create default triangle
    public Triangle() {
    }

    // create triangle with specified side lengths
    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    // setters for side lengths
    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    // getters for side lengths
    public double getSide1() {
        return side1;
    }

    public double getSide2() {
        return side2;
    }

    public double getSide3() {
        return side3;
    }

    // override the following methods from abstract class GeometricObject to be unique to the triangle object!
    @Override
    // calculate the triangle's area
    public double getArea() {
        double s = (side1 + side2 + side3) * 0.5;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    // calculate the triangle's perimeter
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    // display all properties of the triangle
    public String toString() {
        return String.format(
                "side1= %6.2f, side2= %6.2f, side3= %6.2f, color= %s, filled= %b,\nperimeter = %6.2f, area = %6.2f",
                side1, side2, side3, getColor(), isFilled(), getPerimeter(), getArea());
    }
}
