import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        // Dependency Injection: Create dependencies
        Scanner scanner = new Scanner(System.in);
        InputValidator validator = new InputValidator(scanner);
        ArrayManager arrayManager = new ArrayManager(validator);
        SortAlgorithm sortAlgorithm = new BubbleSortAlgorithm(); // Can easily replace with QuickSort, MergeSort, etc.

        // Create and run application
        BubbleSortApplication app = new BubbleSortApplication(arrayManager, sortAlgorithm, validator);
        app.run();

        // Clean up resources
        scanner.close();
    }
}