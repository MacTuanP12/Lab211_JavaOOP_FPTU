import java.util.Scanner;


public class InputValidator {
    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer checkIn(String inputVal) {
        if (inputVal == null || inputVal.trim().isEmpty()) {
            return null;
        }

        try {
            return Integer.parseInt(inputVal.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }


    public int getPositiveInteger(String prompt, String errorMessage) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        Integer value = checkIn(input);

        while (value == null || value <= 0) {
            System.out.println(errorMessage);
            System.out.print("Enter Number: ");
            input = scanner.nextLine();
            value = checkIn(input);
        }

        return value;
    }


    public int getInteger(String prompt, String errorMessage) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        Integer value = checkIn(input);

        while (value == null) {
            System.out.println(errorMessage);
            System.out.print("Enter Number: ");
            input = scanner.nextLine();
            value = checkIn(input);
        }

        return value;
    }


    public int getMenuChoice() {
        String input = scanner.nextLine();
        Integer choice = checkIn(input);

        while (choice == null || choice < 1 || choice > 4) {
            System.out.print("Invalid choice! Please enter a number between 1 and 4: ");
            input = scanner.nextLine();
            choice = checkIn(input);
        }

        return choice;
    }
}

