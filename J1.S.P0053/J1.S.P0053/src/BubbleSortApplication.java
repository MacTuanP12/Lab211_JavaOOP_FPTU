
public class BubbleSortApplication {
    private final ArrayManager arrayManager;
    private final SortAlgorithm sortAlgorithm;
    private final InputValidator validator;

    public BubbleSortApplication(ArrayManager arrayManager, SortAlgorithm sortAlgorithm, InputValidator validator) {
        this.arrayManager = arrayManager;
        this.sortAlgorithm = sortAlgorithm;
        this.validator = validator;
    }


    public void run() {
        while (true) {
            displayMenu();
            int choice = validator.getMenuChoice();

            if (choice == 4) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

            performFunction(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\n========= Bubble Sort program =========");
        System.out.println("1. Input Element");
        System.out.println("2. Sort Ascending");
        System.out.println("3. Sort Descending");
        System.out.println("4. Exit");
        System.out.print("Please choice one option: ");
    }

    private void performFunction(int choice) {
        switch (choice) {
            case 1:
                arrayManager.inputElements();
                break;
            case 2:
                sortAndDisplay(true);
                break;
            case 3:
                sortAndDisplay(false);
                break;
        }
    }


    private void sortAndDisplay(boolean ascending) {
        if (arrayManager.isEmpty()) {
            System.out.println("\nArray is empty! Please input elements first.");
            return;
        }

        int[] sortedArray = sortAlgorithm.sort(arrayManager.getArray(), ascending);

        String orderType = ascending ? "Ascending" : "Descending";
        System.out.println("\n----- " + orderType + " -----");
        arrayManager.displayArray(sortedArray);
    }
}

