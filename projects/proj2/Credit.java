/*
* CSC-239 Project 2: Credit Card Verification Application
* Name: Beftu Sultan
* Due Date: 19 November 2025
* Description: This program processes credit card transactions, saving the results in a text file.
*/


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Credit {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws java.io.IOException {

        // text file to track account records between transactions
        File file = new File("dataFile.txt");

        // array list to hold as many credit accounts objects created during each session
        ArrayList<CreditAccount> creditAccount = new ArrayList<>();

        String userInput;

        // displays program guide at the beginning of each program session as well as whenever user enters the help command
        helpGuide();

        // starting prompt for command for each session
        userInput = requestUserInput();

        // run program on a loop until user decides to quit
        while (!userInput.equalsIgnoreCase("q")) {
            // every program session the array will start fresh since accounts created from past sessions will be loaded from the file
            creditAccount.clear();
            // as long as a file exists account data will be loaded from previous transactions (Read)
            loadData(file, creditAccount);

            // only displays records read starting from the second transaction of each session (once the list has data)
            if (!creditAccount.isEmpty()) {
                System.out.println(creditAccount.size() + " record(s) read from input file");
            }

            // split up user command to get command type and/or parameters
            String[] splitUserInput = userInput.split(" ");

            // handles requested command (Process)
            runCommand(splitUserInput, creditAccount);

            // runs archival process on existing file after each transaction is completed
            renameWithTimestamp(file);

            // writes updated data to a new dataFile.txt which will then be archived in the next transaction (Write)
            PrintWriter output = new PrintWriter(file);
            writeData(output, creditAccount);
            output.close();

            System.out.println(creditAccount.size() + " record(s) written to output file.");

            // reprompt user
            userInput = requestUserInput();

            System.out.println("userInput = " + userInput);
        }

        System.out.println("Exit program");
    }

    // prompts user and returns command
    public static String requestUserInput() {
        System.out.print("\nEnter a command (help, create, verify, or q): ");
        return scanner.nextLine().trim();
    }

    // takes command keyword and triggers appropriate method to process it
    public static void runCommand(String[] splitUserInput, ArrayList<CreditAccount> accounts) {

        switch (splitUserInput[0].toLowerCase()) {
            case "create":
                accounts.add(createAccount(splitUserInput));
                printAccountCreation(accounts);
                break;
            case "verify":
                runVerification(splitUserInput, accounts);
                break;
            case "help":
                helpGuide();
                break;
            case "q":
                break;
        }
    }

    // if user entered verify command, first check if account to be verified exists
    public static void runVerification(String[] splitUserInput, ArrayList<CreditAccount> accounts) {
        // once account existence is verified process desired user transaction (purchase or return)
        if (verifyAccountExists(splitUserInput, accounts)) {
            // retrieve specified account from list
            CreditAccount userAccount = fetchAccount(splitUserInput[1], accounts);
            double requestedAmount = Double.parseDouble(splitUserInput[2]);

            // requested amount is positive then it is a a purchase attempt, negative requested aomunt is a return attempt
            if (requestedAmount >= 0) {
                runPurchase(requestedAmount, userAccount);
            } else {
                runReturn(requestedAmount, userAccount);
            }
        } else {
            System.out.print("ACCOUNT NOT ON FILE (account num = " + splitUserInput[1] + ")");
        }
    }

    // process if user wants to make a purchase
    public static void runPurchase(Double requestedAmount, CreditAccount account) {
        // before approving purhcase check if user has enough credit
        if (account.hasSufficientFunds(requestedAmount)) {
            // reduce available credit by the purchase amount
            account.updateLimit(requestedAmount);
            // display authorization approved
            System.out.printf(
                    "AUTHORIZATION GRANTED (account num = %s, transaction amount = $%.2f, available credit = $%.2f)",
                    account.getAccountNum(), requestedAmount, account.getAvailableCredit());

        } else { // user has insufficient balance! abort purchase process
            System.out.printf(
                    "AUTHORIZATION DENIED (account num = %s, transaction amount = $%.2f, available credit = $%.2f)",
                    account.getAccountNum(), requestedAmount, account.getAvailableCredit());
        }

    }

    // if user entered negative value then start return process
    public static void runReturn(Double requestedAmount, CreditAccount account) {
        // check if adding the return value raises available credit ABOVE max limit, if so, provide warning but process return
        if (Math.abs(requestedAmount) + account.getAvailableCredit() > account.getMaxLimit()) {
            account.updateLimit(requestedAmount);
            System.out.printf(
                    "AUTHORIZATION GRANTED with CAUTION -- current transaction attempted to raise available credit above MAX = $%.2f,\n(accountNum = %s, transactionAmount = $%.2f, NEW available credit = $%.2f)",
                    account.getMaxLimit(), account.getAccountNum(), requestedAmount, account.getAvailableCredit());
        } else {
            // process return without warning if return doesn't raise avail. credit above max limit
            account.updateLimit(requestedAmount);
            System.out.printf(
                    "AUTHORIZATION GRANTED (account num = %s, transaction amount = $%.2f available credit = $%.2f)",
                    account.getAccountNum(), requestedAmount, account.getAvailableCredit());
        }
    }

    // print instructions for user on how to input command and parameters and supported card issuers
    public static void helpGuide() {
        System.out.println("\nThis program accepts the following inputs and performs the corresponding actions:\n");

        System.out.printf("%-12s %-25s %-40s%n", "COMMAND", "PARAMETER(S)", "DESCRIPTION");

        System.out.printf("%-12s %-25s %-40s%n", "help", " ", "Output help text.");
        System.out.printf("%-12s %-25s %-40s%n", "create", "issuerSymbol", "CREATE new account.");
        System.out.printf("%-12s %-25s %-40s%n", "verify", "accountNum amount", "VERIFY a purchase or credit.");
        System.out.printf("%-12s %-25s %-40s%n", "q", " ", "exit the program.");

        System.out.println("\nThe command and issuerSymbol values are NOT case sensitive.");

        System.out.println("The following credit cards are supported:");
        System.out.printf("%-20s %-10s%n", "CARD", "SYMBOL");
        System.out.printf("%-20s %-10s%n", "American Express", "AE");
        System.out.printf("%-20s %-10s%n", "Visa", "V");
        System.out.printf("%-20s %-10s%n", "MasterCard", "MC");
        System.out.printf("%-20s %-10s%n", "Discover", "DIS");
        System.out.printf("%-20s %-10s%n", "Diners Club", "DINE");

    }

    // print summary of account info after creation
    public static void printAccountCreation(ArrayList<CreditAccount> account) {
        CreditAccount last = account.get(account.size() - 1);
        System.out.printf("New account created for credit symbol %s    account number: %s    credit limit: $%.2f", last.getIssuerSymbol().toUpperCase(), last.getAccountNum(), last.getMaxLimit());
    }

    // create credit account object for each create transaction
    public static CreditAccount createAccount(String[] splitUserInput) {
        String symb = splitUserInput[1];

        CreditAccount account = new CreditAccount(symb);

        account.setAccountNum(createAccountNumber(splitUserInput));
        assignMaxLimit(account);

        return account;
    }

    // generates random 15 digits number (part of creating credit acconut number)
    public static long generateNumber() {
        Random random = new Random();

        long min = 100000000000000L; // 100,000,000,000,000
        long max = 999999999999L; // 999,999,999,999

        return random.nextLong(max) + min;
    }

    // finalizes credit account number by appending randomly generated 15 dig number to credit issuer code
    public static String createAccountNumber(String[] splitUserInput) {
        String accountNumber = "";

        switch (splitUserInput[1].toLowerCase()) {
            case "ae":
                accountNumber = Long.toString(CreditAccount.ISSUER_CODE_AE) + generateNumber();
                break;
            case "v":
                accountNumber = Long.toString(CreditAccount.ISSUER_CODE_V) + generateNumber();
                break;
            case "mc":
                accountNumber = Long.toString(CreditAccount.ISSUER_CODE_MC) + generateNumber();
                break;
            case "dis":
                accountNumber = Long.toString(CreditAccount.ISSUER_CODE_DIS) + generateNumber();
                break;
            case "dine":
                accountNumber = Long.toString(CreditAccount.ISSUER_CODE_DINE) + generateNumber();
                break;
        }

        return accountNumber;
    }

    // assign credit max limit based on last digit of account number
    public static void assignMaxLimit(CreditAccount account) {
        double lowerLimit = 500;
        double higherLimit = 1000;

        String number = account.getAccountNum();
        char[] chars = number.toCharArray();

        int last = Character.getNumericValue(chars[15]);

        if (last <= 4) {
            account.setMaxLimit(higherLimit);
        } else {
            account.setMaxLimit(lowerLimit);
        }
    }

    // checks for account existence by searching for account number from list and returns true if found
    public static boolean verifyAccountExists(String[] splitUserInput, ArrayList<CreditAccount> accounts) {
        boolean accountExists = false;
        // loop through array list (fetch account number of each credit account object and check for matching account number to the user's input)
        for (int i = 0; i < accounts.size(); i++) {
            CreditAccount account = accounts.get(i);
            if (splitUserInput[1].equals(account.getAccountNum())) {
                accountExists = true;
            }
        }
        return accountExists;
    }

    // retrieves an existing account matching user provided account number
    public static CreditAccount fetchAccount(String accountNum, ArrayList<CreditAccount> accounts) {

        for (CreditAccount creditAcct : accounts) {
            if (accountNum.equalsIgnoreCase(creditAcct.getAccountNum())) {
                return creditAcct;
            }
        }
        return null;
    }

    // load data from file into list
    public static ArrayList<CreditAccount> loadData(File file, ArrayList<CreditAccount> accounts)
            throws FileNotFoundException {

        // only process loading if file exists otherwise there is no data
        if (file.exists()) {
            Scanner fileInput = new Scanner(file);

            // line by line read data into array list
            while (fileInput.hasNextLine()) {
                String record = fileInput.nextLine();
                // split the record by pipe delimeter
                String accountDetails[] = record.split("\\|");

                // parse to corresponding data type to successfully pass to CreditAccount constructor
                String accountNum = accountDetails[0].trim();
                double available = Double.parseDouble(accountDetails[1].trim());
                double maxLimit = Double.parseDouble(accountDetails[2].trim());

                CreditAccount loadedAccount = new CreditAccount(accountNum, available, maxLimit);
                accounts.add(loadedAccount);
            }
            fileInput.close();
        }

        // returns list of existing accounts or empty list
        return accounts;
    }

    // write all existing accounts in memory to file
    public static void writeData(PrintWriter file, ArrayList<CreditAccount> accounts) throws FileNotFoundException {
        System.out.println();

        // formats each account information by rows
        for (CreditAccount creditAcct : accounts) {
            file.printf("%-10s | %8.2f | %8.2f%n", creditAcct.getAccountNum(), creditAcct.getAvailableCredit(),
                    creditAcct.getMaxLimit());
        }

        file.close();
    }

    // archives current file with renaming it with timestamp
    public static void renameWithTimestamp(File file) {
        // get current time and format it
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String newDateTime = dt.format(formatter);

        // rename only if a file exists
        if (file.exists()) {
            File archivedFile = new File("dataFile_" + newDateTime + ".txt");
            file.renameTo(archivedFile);
        } else {
            System.out.println("no existing file to rename.");
        }
    }
}
