import java.util.Scanner;

public class DayOne {
    public static void main(String[] args) {
        System.out.println("Day one Java class");

        // create Scanner object
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your age: ");
        int age = input.nextInt();

        System.out.print("The users age is " + age);

        input.close();
    }
}