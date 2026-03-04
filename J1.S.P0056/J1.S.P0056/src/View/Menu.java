package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private String title;
    private List<String> options;
    private Scanner scanner;

    public Menu(String title) {
        this.title = title;
        this.options = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addOption(String option) {
        options.add(option);
    }

    public void display() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println(title);
        System.out.println("=".repeat(50));
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        System.out.println("=".repeat(50));
    }

    public int getChoice() {
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.size()) {
                    return choice;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 1 and " + options.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}

