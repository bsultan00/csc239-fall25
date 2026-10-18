/*
 * name: beftu sultan
 * date: 2025 sep 18
 * file name: lab05a.java
 * description: a simple program to calculate and approximate value for the mathematical constant PI with formatting and additional data regarding calculation using infinite series.
 */

import java.util.Scanner;

public class Lab05a {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // prompt user for MAX i val
        System.out.print("\nEnter maximum loop count: ");
        int loopMax = scanner.nextInt();

        System.out.println("Maximum loop count = " + loopMax);

        // format header titles
        System.out.printf("%6s %17s %8s %23s %11s\n",
                "Count", "Approximation", "Error", "Variance (%)", "termI");

        double termI = 0;
        double piOver4 = 0;
        double pi = 0;
        double error = 0;
        double variance = 0;
        int sign = -1;

        for (int i = 1; i <= loopMax; i++) {

            // change sign depending on even or odd iteration
            sign = (i % 2 == 0) ? (sign = -1) : (sign = 1);
            termI = (sign) * (1.0 / ((2.0 * i) - 1.0));

            piOver4 += termI;
            pi = piOver4 * 4;
            error = pi - Math.PI;
            variance = (error / Math.PI) * 100;

            /*
             * for any iter over 50.. if i is not a multiple of 100, not a number ending in
             * 01,
             * and not the last iteration THEN skip over printing the iteration, just
             * calculate.
             */

            if ((i > 50) && (i % 100 != 0) && (i % 100 != 1) && (i != loopMax)) {
                continue; // dont execute the following print statement
            }

            System.out.printf("%6s: %15.10f    %+014.10f   %+014.10f %%  %+15.10f\n", i, pi, error, variance,
                    termI);
        }
        System.out.print("Math.PI = ");
        System.out.printf("%13.10f\n", Math.PI);

        scanner.close();
    }

}
