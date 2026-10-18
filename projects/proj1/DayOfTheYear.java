/*
 * CSC_239 Project 1: Day of the Year
 * Name: Beftu Sultan
 * Date: 2025 Oct 08
 * File name: DayOfTheYear.java
 * Description: This program prompts the user input a string date in format MM DD YYYY to represent a numeric date
 *              and will calculate number of days lapsed from Jan. 1 of the same year to the date inputted by the user.
 */

import java.util.Scanner;

public class DayOfTheYear {

    // REQUEST USER INPUT
    public static String userInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter numeric date (or q to exit program): ");
        String userInput = scanner.nextLine();

        return userInput;
    }

    // IF USER WANTS TO QUIT
    public static boolean userQuit(String userInput) {
        boolean userQuit = true;

        // check if user entered "Q" or "q" to quit
        return (userInput.equalsIgnoreCase("q")) ? userQuit : !userQuit;
    }

    // MAKE SURE USER ENTERED PROPER FORMAT MM DD YYYY
    public static boolean isValidFormat(String userInput) {
        boolean isValid = true;

        // make sure input is 10 characters only, nothing more nothing less!
        if (userInput.length() != 10) {
            return !isValid;

            // now check for space after month (index 2) and year (index 5)
        } else if ((userInput.charAt(2) != ' ') || (userInput.charAt(5) != ' ')) {
            return !isValid;
        }

        return isValid;
    }

    // SPLIT INPUT INTO ARRAY
    public static String[] splitInput(String userInput) {
        // remove spaces from input and put separated data into string array
        String[] stringDate = userInput.split(" ");

        return stringDate;
    }

    // CONVERT STRING TO INT WITH PARSEINT METHOD AND PUT IN A NEW ARRAY
    public static int[] parseDate(String[] stringDate) {

        // create an int array ready to hold 3 elements [MM, DD, YYYY]
        int[] intDate = new int[3];

        // loop through each string element and parse it before copying to int array
        for (int i = 0; i < intDate.length; i++) {
            intDate[i] = Integer.parseInt(stringDate[i]);
        }

        return intDate;
    }

    // CHECK IF USER ENTERED VALID DATE
    public static boolean isValidDate(int[] intDate) {

        boolean dateIsValid = true;

        int m = intDate[0];
        int d = intDate[1];
        int y = intDate[2];

        // check if its leap year to help us know how many days in february to consider
        // in validation
        boolean isLeapYear = (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
        int[] daysInMonth = { 31, (isLeapYear ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        // make sure month is in bounds
        if (m < 1 || m > 12) {
            System.out.println("Invalid numeric month: " + m + ". Month must be between 1 and 12.\n");
            return !dateIsValid;
        }
        // make sure day is in bounds
        if (d < 1 || d > daysInMonth[m - 1]) {
            System.out.println("Invalid numeric day: " + d + " for month: " + m + ", year: " + y
                    + ". Day for that month must be between 1 and " + daysInMonth[m - 1] + ".\n");
            return !dateIsValid;
        }
        // make sure year is in bounds
        if (y < 1900 || y > 2100) {
            System.out.println("Invalid numeric year: " + y + ". Year must be between 1900 and 2100.\n");
            return !dateIsValid;
        }

        return dateIsValid;
    }

    // CALCULATE DAYS LAPSED
    public static int calculateDaysLapsed(int[] intDate) {
        // this method doesn't do the full calculation, it passes the return value to
        // printResult to complete the rest of the calculation
        // only calculates full months
        int y = intDate[2];

        // check if its leap year to help us know how many days in february to consider
        // in calculation
        boolean isLeapYear = (y % 4 == 0 && y % 100 != 0) || (y %
                400 == 0);

        int[] daysInEachMonth = { 31, ((isLeapYear) ? 29 : 28), 31, 30, 31, 30, 31,
                31, 30, 31, 30, 31 };

        int daysElapsed = 1; // represents jan 1 to start

        // intDate[0] -1 helps prevent going out of bounds in the daysInEachMonth array
        for (int i = 0; i < (intDate[0] - 1); i++) {
            daysElapsed += daysInEachMonth[i];
        }

        return daysElapsed;
    }

    // PRINT CALCULATION RESULTS
    public static void printResult(int daysElapsed, int[] intDate) {

        // to print the month by name rather than number
        String[] monthNames = { "Jan", "Feb", "Mar", "Apr", "May", "June", "Jul",
                "Aug", "Sept", "Oct", "Nov", "Dec" };

        int intMonth = intDate[0];

        daysElapsed += intDate[1]; // adds the partial days of the month to the calculation from
                                   // calculateDaysLapsed()

        // intMonth - 1 is to access accurate index in monthNames array (avoid going out
        // of bounds)
        System.out.println((daysElapsed - 1) + " day(s) have elapsed from Jan 01, " + intDate[2] + " to "
                + monthNames[intMonth - 1] + " " + intDate[1] + ", " + intDate[2] + ".\n");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("This program accepts numeric dates in the format MM DD YYYY.\n");

        // get user input
        String fullDate = userInput();

        // run program until user enters q to quit
        while (!userQuit(fullDate)) {

            // check if user entered in MM DD YYYY format
            while (!isValidFormat(fullDate)) {
                System.out.println("Invalid format. Must be MM DD YYYY.\n");
                // prompt reinput
                fullDate = userInput();
                // allow user to quit instead of putting new date after failed input
                if (userQuit(fullDate)) {
                    break;
                }
            }

            // allow user to quit before splitting and parsing date
            if (userQuit(fullDate)) {
                break;
            }

            // save split input into array
            String[] stringDate = splitInput(fullDate);

            // parse from string to int then save into new array
            int[] intDate = parseDate(stringDate);

            // check for valid numeric date
            while (!isValidDate(intDate)) {
                fullDate = userInput();
                // again allow user to quit
                if (userQuit(fullDate)) {
                    break;
                }
                // split and parse the date to check for validity again
                stringDate = splitInput(fullDate);
                intDate = parseDate(stringDate);
            }

            // allow user to quit once again
            if (userQuit(fullDate)) {
                break;
            }

            // output results
            printResult(calculateDaysLapsed(intDate), intDate);

            fullDate = userInput();
        }

        System.out.println("\nExit program.");

        scanner.close();
    }
}
