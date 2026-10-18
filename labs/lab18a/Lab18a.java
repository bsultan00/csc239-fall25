
/*
 * name : beftu sultan
 * due date : 1 dec. 2025
 * description : using a recursive method to find the GCD using Euclid's algorithm. user inputs two integers
 *               program will display their GCD.
 */
import java.util.Scanner;

public class Lab18a {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = userInput(scanner);

        // run until user wants to quit
        while (!userInput.equalsIgnoreCase("q")) {
            // hold 2 user provided int values
            int[] posInt = parseInput(userInput);

            // output final result
            System.out.println(
                "The GCD of " + posInt[0] + " and " + posInt[1] + " is " + gcd(posInt[0], posInt[1]) + ".\n");

            // reprompt user input
            userInput = userInput(scanner);
        }

        scanner.close();
    }

    // prompt user input
    public static String userInput(Scanner input) {
        System.out.print("Enter two integers (or 'q' to exit): ");

        return input.nextLine();
    }

    // calculate gcd recursively
    public static int gcd(int m, int n) {
        System.out.println("Entering 'gcd' method: m = " + m + ", n = " + n);

        // gcd is found when remainder is 0, which the numbers form our base case
        if (m % n == 0) {
            System.out.println("Returning 'gcd' value = " + n + " (BASE CASE:   m = " + m + ", n = " + n + ")");

            return n;
        } else {
            int gcd = gcd(n, m % n);
            System.out.println("Returning 'gcd' value = " + gcd + " (recursive case: m = " + m + ", n = " + n + ")");

            // run it back
            return gcd;
        }
    }

    // parsing string input to int
    public static int[] parseInput(String input) {
        int[] values = new int[2];

        String[] userString = input.split(" ");
        values[0] = Integer.parseInt(userString[0]);
        values[1] = Integer.parseInt(userString[1]);

        return values;
    }
}