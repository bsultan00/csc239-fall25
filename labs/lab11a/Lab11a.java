/*
 * name: beftu sultan
 * date: 21 oct 2025
 * file name: Lab11.java
 * description: program containing 3 classes. main class will create objects of a subclass. coordinate points for each object are input by the user.
 *              distance between both objects are calculated using 2 different methods (pass by value vs pass by reference). also tracks number of objects created.
 *              runs until user enters 'q'. 
 */

import java.util.Scanner;

public class Lab11a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = " ";

        while (!userInput.equalsIgnoreCase("q")) {

            NewPoint p1 = new NewPoint();
            NewPoint p2 = new NewPoint();

            String regex = "[,\\s\\.:;?]"; // ch. 10 lecture

            // set coordinates for p1, split, then parse
            System.out.print("\nEnter x and y coordinates for p1 (or q to exit): ");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("q")) {
                break;
            }
            String[] p1Coord = userInput.split(regex);
            p1.x = Double.parseDouble(p1Coord[0]);
            p1.y = Double.parseDouble(p1Coord[1]);

            // set coordinates for p2, split, then and parse
            System.out.print("Enter x and y coordinates for p2 (or q to exit): ");
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("q")) {
                break;
            }
            String[] p2Coord = userInput.split(regex);
            p2.x = Double.parseDouble(p2Coord[0]);
            p2.y = Double.parseDouble(p2Coord[1]);

            // display x and y values, display current value of numberOfObjects
            System.out.printf("p1 = (%8.5f, %8.5f), p2 = (%8.5f, %8.5f), numberOfObjects = %d", p1.x, p1.y, p2.x, p2.y,
                    NewPoint.getNumberOfObjects());

            // display distance from p1 to p2 (obj to obj) with printf
            System.out.printf("\nDistance (object to object)          = %8.5f", p1.distance(p2));

            // display distance from object to x,y xoordinates
            System.out.printf("\nDistance (object to x,y coordinates) = %8.5f\n", p1.distance(p2.getX(), p2.getY()));

        }

        scanner.close();
    }
}