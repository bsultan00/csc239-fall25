/*
 * Name: Beftu Sultan
 * Date: 15 October 2025
 * File name: Lab9a.java
 * Description: This program has the user enter details for a stock (symbol, company name, company's previous closing price and current price).
 *              The percentage representing how much the stock price has changed is calculated and presented along with the rest of the data. 
 *              User is allowed to quit program by entered "q" in place of a stock symbol. (Key topics: Classes, methods, getters/setters, loops,)
 * 
 */

import java.util.Scanner;

public class Lab9a {

    // request stock symbol and allow user to quit
    public static String userInputSymbol(Scanner scanner) {
        System.out.print("Enter stock symbol (or 'q' to exit): ");

        String userSymbol = scanner.nextLine();

        return userSymbol;
    }

    // request associated company name
    public static String userInputName(Scanner scanner) {

        System.out.print("Enter company name: ");

        String userName = scanner.nextLine();

        return userName;
    }

    // request previous closing price
    public static double userInputPreviousClosingPrice(Scanner scanner) {

        System.out.print("Enter previous closing price: ");

        double previousClosingPrice = scanner.nextDouble();

        return previousClosingPrice;
    }

    // request current price
    public static double userInputCurrentPrice(Scanner scanner) {

        System.out.print("Enter current price: ");

        double currentPrice = scanner.nextDouble();

        return currentPrice;
    }

    // to avoid calculating a 0 in the denominator for the percentage calculation
    // later
    public static double invalidPrice(Scanner scanner, Stock myStock, double previousPrice) {

        while (previousPrice <= 0) {
            System.out.println("Please enter an amount greater than 0.\n");
            myStock.setPreviousClosingPrice(userInputPreviousClosingPrice(scanner));
            previousPrice = myStock.getPreviousClosingPrice();
        }

        return previousPrice;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // creating our stock object
        Stock myStock = new Stock();

        // prompt user for stock symbol
        myStock.setSymbol(userInputSymbol(scanner));

        // check if user wants to quit
        while (!myStock.getSymbol().equalsIgnoreCase("Q")) {

            // prompt user for company name associated with stock symbol
            myStock.setName(userInputName(scanner));

            // prompt user for previous closing price
            myStock.setPreviousClosingPrice(userInputPreviousClosingPrice(scanner));
            // check if previous closing price is 0 or less, if so, reprompt for valid input
            invalidPrice(scanner, myStock, myStock.getPreviousClosingPrice());

            // prompt user for current price
            myStock.setCurrentPrice(userInputCurrentPrice(scanner));

            // display users input
            System.out.print("Symbol = " + myStock.getSymbol() + " | Name = " +
                    myStock.getName());

            // System.out.print(" | Previous Price = ");
            System.out.printf(" | Previous Price = %4.2f", myStock.getPreviousClosingPrice());

            // System.out.print(" | Current Price = ");
            System.out.printf(" | Current Price = %4.2f", myStock.getCurrentPrice());

            // get the percent changed from prev. closing price to current price, display
            // rest of data
            System.out.printf(" | Percent Change = %+4.2f",
                    myStock.getChangePercent(myStock.getPreviousClosingPrice(), myStock.getCurrentPrice()));
            System.out.print(" %\n\n");

            // prompt user for stock symbol
            myStock.setSymbol(userInputSymbol(scanner));
        }

        System.out.println("Exiting program. Happy trading...");

        scanner.close();
    }
}
