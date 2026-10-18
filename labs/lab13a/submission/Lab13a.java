/*
 * name: beftu sultan
 * date: 31 october 2025
 * file name(s): Lab13a.java, Triangle.java, GeometricObject.java
 * description: created a Triangle class extending the GeometricObject abstract class. 
 *              user inputs the side lengths, color, and wether the triangle is filled.
 *              program calculates the perimeter and area of the triangle and prints the 
 *              properties of the triangle.
 */

import java.util.Scanner;

public class Lab13a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // take user input and return split input to sidesArr
        String[] sidesArr = sidesInput(scanner);

        // construct object with parsed input for triangle dimensions
        GeometricObject triangle1 = new Triangle(Double.parseDouble(sidesArr[0]), Double.parseDouble(sidesArr[1]),
                Double.parseDouble(sidesArr[2]));

        // prompt user input for remaining triangle properties
        System.out.print("Enter the color: ");
        triangle1.setColor(scanner.nextLine());

        System.out.print("Enter a boolean value for filled: ");
        triangle1.setFilled(scanner.nextBoolean());

        // display all properties of triangle object
        displayTriangleObject(triangle1);

        scanner.close();
    }

    // prompt user input and split it
    public static String[] sidesInput(Scanner scanner) {
        System.out.print("Enter 3 sides: ");
        String sides = scanner.nextLine();

        return sides.split(" ");
    }

    // display results
    public static void displayTriangleObject(GeometricObject triangle) {
        System.out.println("Triangle: " + triangle.toString());
    }
}
