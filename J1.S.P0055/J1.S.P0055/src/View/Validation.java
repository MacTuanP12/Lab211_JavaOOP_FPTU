package View;

import java.util.Scanner;

public class Validation {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Get a non-empty string from user
     */
    public static String getString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    /**
     * Get a string that can be empty (for update operations)
     */
    public static String getStringAllowEmpty(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Get a positive integer from user
     */
    public static int getPositiveInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                int number = Integer.parseInt(input);
                if (number >= 0) {
                    return number;
                }
                System.out.println("Availability must be greater than or equal to 0. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Get a positive integer that can be empty (for update operations)
     */
    public static Integer getPositiveIntAllowEmpty(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    return null;
                }
                int number = Integer.parseInt(input);
                if (number >= 0) {
                    return number;
                }
                System.out.println("Availability must be greater than or equal to 0. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Get confirmation from user (Y/N)
     */
    public static boolean confirm(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.equals("Y") || input.equals("YES")) {
                return true;
            } else if (input.equals("N") || input.equals("NO")) {
                return false;
            }
            System.out.println("Invalid input! Please enter Y or N.");
        }
    }
}

