package View;

import java.util.Scanner;

public class Validation {
    private static Scanner scanner = new Scanner(System.in);


    public static String getString(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }


    public static int getInt(String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }


    public static double getPositiveDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                String input = scanner.nextLine().trim();
                double value = Double.parseDouble(input);
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Value must be greater than 0. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }


    public static boolean getYesNo(String message) {
        while (true) {
            System.out.print(message + " (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("YES")) {
                return true;
            } else if (input.equals("N") || input.equals("NO")) {
                return false;
            } else {
                System.out.println("Please enter Y or N.");
            }
        }
    }
}

