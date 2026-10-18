import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int CLASS_DAY = 2; // we use const in cpp, but final in java

        System.out.print("Today is day " + CLASS_DAY + " of class.");

        // suppose today is Saturday and you and your friends plan to meet in 10 days.
        // what day is in 10 days?
        // Sunday is 0 and and each day goes up by one number. Saturday is 6.

        int today = 6;
        int tenDays = (today + 10) % 7;

        System.out.print("In 10 days it will be: " + tenDays); // 2 for Tuesday

        /*
         * Data types: byte, short, int, long, float, double
         */

        // casting b/w char and numeric types

        int i = 'a'; // OR int i = (int)'a';
        char c = 97; // OR char c = (char)97;

        /*
         * using parseInt (String to int)
         */

        double d = (9.0 / 2 + 9 / 2) * 3;

        System.out.println(d);

        String someValue = "-123";
        String anotherValue = "+125";

        int someValueA = Integer.parseInt(someValue);
        int anotherValueB = Integer.parseInt(anotherValue);

        System.out.println(someValueA);
        System.out.println(anotherValueB);

    }
}