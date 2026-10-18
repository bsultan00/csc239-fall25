/*
 * name: beftu sultan
 * date: 09/10/2025
 * file name: lab03a.java
 * description: a simple program to calculate the number of days in a given month. a switch case is used to display output depending on the month entered.
 *              if month 2 (February) is entered, the switch case will then check if the year entered is a leap year before displaying number of days.
 */

import java.util.Scanner;

public class lab03a {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // prompt the user to enter the month and year
        System.out.println("Enter a month in the year (e.g., 1 for Jan): ");
        int month = scanner.nextInt();

        System.out.println("Enter a year: ");
        int year = scanner.nextInt();

        // check if year is a leap year or not
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        switch (month) {
            case 1:
                System.out.println("January " + year + " has 31 days.");
                break;
            case 2:
                // display number of days depending on if february is in the leap year or not
                if (isLeapYear) {
                    System.out.println("February " + year + " has 29 days.");
                } else {
                    System.out.println("February " + year + " has 28 days.");
                }
                break;
            case 3:
                System.out.println("March " + year + " has 31 days.");
                break;
            case 4:
                System.out.println("April " + year + " has 30 days.");
                break;
            case 5:
                System.out.println("May " + year + " has 30 days.");
                break;
            case 6:
                System.out.println("June " + year + " has 30 days.");
                break;
            case 7:
                System.out.println("July " + year + " has 31 days.");
                break;
            case 8:
                System.out.println("August " + year + " has 31 days.");
                break;
            case 9:
                System.out.println("September " + year + " has 30 days.");
                break;
            case 10:
                System.out.println("October " + year + " has 31 days.");
                break;
            case 11:
                System.out.println("November " + year + " has 30 days.");
                break;
            case 12:
                System.out.println("December " + year + " has 31 days.");
                break;
            default:
                System.out.println("You have entered an invalid date. Please start again.");

        }

        scanner.close();
    }
}
