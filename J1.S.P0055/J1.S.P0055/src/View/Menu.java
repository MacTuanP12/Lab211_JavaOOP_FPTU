package View;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private ArrayList<String> options;
    private String title;

    public Menu(String title) {
        this.title = title;
        this.options = new ArrayList<>();
    }

    public void addOption(String option) {
        options.add(option);
    }

    public void display() {
        System.out.println("\n========== " + title + " ==========");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    public int getChoice() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine().trim());
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

